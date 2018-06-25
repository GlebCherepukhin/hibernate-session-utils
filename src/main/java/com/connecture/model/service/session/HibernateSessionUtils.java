package com.connecture.model.service.session;

import org.hibernate.Session;
import org.hibernate.engine.internal.StatefulPersistenceContext;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@SuppressWarnings("unchecked")
@Component
public class HibernateSessionUtils {

  public static <E> List<E> getAllSessionObjects(Session session, Class<E> clazz) {
    SessionImplementor sessionImplementor = (SessionImplementor) session;
    PersistenceContext persistenceContext = sessionImplementor.getPersistenceContext();

    return (List<E>) persistenceContext.getEntitiesByKey()
        .values()
        .stream()
        .filter(clazz::isInstance)
        .collect(toList());
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public <E> void flushAllObjects(Session session, Class<E> clazz) {
    performEvictThenFlush(session, getAllSessionObjects(session, clazz));
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public <E> void flushAllObjects(Session session, Class<E> clazz, Predicate<E> filter) {
    List<E> objectsToFlush = getAllSessionObjects(session, clazz).stream()
        .filter(filter)
        .collect(toList());
    performEvictThenFlush(session, objectsToFlush);
  }

  private <E> void performEvictThenFlush(Session primarySession, List<E> objectsToFlush) {
    evictAllObjects(primarySession, objectsToFlush);
    Session temporarySession = primarySession.getSessionFactory().getCurrentSession();
    objectsToFlush.forEach(temporarySession::save);
  }

  public static <E> void evictAllObjects(Session session, Class<E> clazz) {
    evictAllObjects(session, getAllSessionObjects(session, clazz));
  }

  public static <E> void evictAllObjects(Session session, List<E> objectsToEvict) {
    objectsToEvict.forEach(session::evict);
  }

  @Deprecated
  public static <E> void evictAllObjectsInPersistentContext(Session session, Class<E> clazz) throws NoSuchFieldException {
    SessionImplementor sessionImplementor = (SessionImplementor) session;
    PersistenceContext persistenceContext = sessionImplementor.getPersistenceContext();

    Map<?, ?> filteredEntities = ((Set<Map.Entry<?, ?>>) persistenceContext.getEntitiesByKey().entrySet()).stream()
        .filter(entry -> clazz.isInstance(entry.getValue()))
        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

    Field entitiesByKey = StatefulPersistenceContext.class.getDeclaredField("entitiesByKey");
    entitiesByKey.setAccessible(true);
    ReflectionUtils.setField(entitiesByKey, persistenceContext, filteredEntities);
  }
}
