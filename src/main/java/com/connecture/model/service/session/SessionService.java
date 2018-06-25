package com.connecture.model.service.session;

import com.connecture.model.entity.IndependentObject1;
import com.connecture.model.entity.User;
import com.connecture.model.service.randomizers.IndependentObject1Randomizer;
import com.connecture.model.service.randomizers.UserRandomizer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessionService {

  private UserRandomizer userRandomizer;
  private SessionFactory sessionFactory;
  private IndependentObject1Randomizer object1Randomizer;
  private HibernateSessionUtils sessionUtils;

  @Autowired
  public SessionService(UserRandomizer userRandomizer,
                        SessionFactory sessionFactory,
                        IndependentObject1Randomizer object1Randomizer,
                        HibernateSessionUtils sessionUtils) {
    this.userRandomizer = userRandomizer;
    this.sessionFactory = sessionFactory;
    this.object1Randomizer = object1Randomizer;
    this.sessionUtils = sessionUtils;
  }

  @Transactional
  public void purePersist() {
    Session session = sessionFactory.getCurrentSession();
    for (int i = 0; i < 5; i++) {
      session.persist(userRandomizer.getOne());
      session.persist(object1Randomizer.getOne());
    }
    sessionUtils.flushAllObjects(session, User.class);
    throw new RuntimeException();
  }

  @Transactional
  public void pureSave() {
    Session session = sessionFactory.getCurrentSession();
    for (int i = 0; i < 5; i++) {
      session.save(userRandomizer.getOne());
      session.save(object1Randomizer.getOne());
    }
    //throw new RuntimeException();
  }

  @Transactional
  public void caseUsingFlushAllObjectsWithSave() {
    Session session = sessionFactory.getCurrentSession();
    for (int i = 0; i < 5; i++) {
      session.save(userRandomizer.getOne());
      session.save(object1Randomizer.getOne());
    }
    sessionUtils.flushAllObjects(session, User.class);
    throw new RuntimeException();
  }

  @Transactional
  public void caseUsingFlushWithPersist() {
    Session session = sessionFactory.getCurrentSession();
    for (int i = 0; i < 5; i++) {
      session.persist(userRandomizer.getOne());
      session.persist(object1Randomizer.getOne());
    }
    sessionUtils.flushAllObjects(session, User.class);
    session.flush();
  }

  @Transactional
  public void evictAllObjectsManual() {
    Session session = sessionFactory.getCurrentSession();
    for (int i = 0; i < 5; i++) {
      session.save(userRandomizer.getOne());
      session.save(object1Randomizer.getOne());
    }

    List<User> allSessionObjects = HibernateSessionUtils.getAllSessionObjects(session, User.class);

    HibernateSessionUtils.evictAllObjects(session, allSessionObjects);
  }

  @Transactional
  public void evictAllObjects() {
    Session session = sessionFactory.getCurrentSession();
    for (int i = 0; i < 5; i++) {
      session.persist(userRandomizer.getOne());
      session.persist(object1Randomizer.getOne());
    }
    HibernateSessionUtils.evictAllObjects(session, User.class);
    HibernateSessionUtils.evictAllObjects(session, IndependentObject1.class);
  }
}
