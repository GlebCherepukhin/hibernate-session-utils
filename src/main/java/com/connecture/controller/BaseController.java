package com.connecture.controller;

import com.connecture.model.service.session.SessionService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//http://localhost:8000/case/
@Controller
@RequestMapping("/case")
public class BaseController {

  private static final Logger LOG = Logger.getLogger(BaseController.class);

  @Autowired
  private SessionService sessionService;

  @RequestMapping("/pureSave")
  public ModelAndView pureSave() {
    LOG.info("Case flushAllSave");
    sessionService.pureSave();
    return new ModelAndView("home");
  }

  @RequestMapping("/purePersist")
  public ModelAndView purePersist() {
    LOG.info("Case flushAllPersist");
    sessionService.purePersist();
    return new ModelAndView("home");
  }

  @RequestMapping("/flushAllSave")
  public ModelAndView flushAllPersist() {
    LOG.info("Case flushAllSave");
    sessionService.caseUsingFlushAllObjectsWithSave();
    return new ModelAndView("home");
  }

  @RequestMapping("/flushPersist")
  public ModelAndView flushPersist() {
    LOG.info("Case flushPersist");
    sessionService.caseUsingFlushWithPersist();
    return new ModelAndView("home");
  }

  @RequestMapping("/evictAll")
  public ModelAndView evictAll() {
    LOG.info("Case evictAll");
    sessionService.evictAllObjects();
    return new ModelAndView("home");
  }

  @RequestMapping("/evictAllManual")
  public ModelAndView evictAllManual() {
    LOG.info("Case evictAllManual");
    sessionService.evictAllObjectsManual();
    return new ModelAndView("home");
  }
}
