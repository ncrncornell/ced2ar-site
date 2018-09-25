package edu.cornell.ncrn.site

import edu.cornell.ncrn.Ced2arApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer

class ServletInitializer extends SpringBootServletInitializer {
  override protected def configure(application: SpringApplicationBuilder): SpringApplicationBuilder =
    application.sources(classOf[Ced2arApplication])

}