package edu.cornell.ncrn.ced2ar.site

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer

class ServletInitializer extends SpringBootServletInitializer {
  override protected def configure(application: SpringApplicationBuilder): SpringApplicationBuilder =
    application.sources(classOf[Ced2arApplication])

}