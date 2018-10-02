package edu.cornell.ncrn.ced2ar.site.controller.util

import javax.servlet.ServletContext

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.context.ServletContextAware

/**
  *  @author Brandon Barker
  *  8/9/2016
  */
class HandyServletContextAware extends ServletContextAware {

  // Get the context so we can build site-local URLs easily:
  var servletContext: Option[ServletContext] = {
    @Autowired
    val sc = null
    Option(sc)
  }
  //
  def servletPath = servletContext match {
    case Some(sc) => sc.getContextPath
    case None => ""
  }
  //
  def setServletContext(sc: ServletContext) = {
    servletContext = Option(sc)
  }

}
