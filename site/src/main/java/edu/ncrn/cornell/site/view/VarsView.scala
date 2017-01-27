package edu.ncrn.cornell.site.view

import edu.ncrn.cornell.site.view.common.Ced2arView
import org.springframework.stereotype.Component

import scalatags.Text.all._
import scala.collection.Map

//TODO: Combine this page with the AllVarsView page; need to add filter. See V2


@Component
class VarsView extends Ced2arView{
  
  def varsList(
        variables : Map[String, String],
        handle : String
        ) : String = {
    val typedHtml = html(
        head(
        defaultMetaTags,
        defaultStyleSheetsAndScripts
      ),
        body(masterDiv(
        topBanner,
        navBar,
        ol(cls:="breadcrumb")(
            li(a(href:= s"$servletPath/codebooks")("Codebooks")),
            li(a(href:= s"$servletPath/codebooks/"+handle)(handle)),
            li(cls:="active")("Variables")
        ),
        h2("Variables"),
        masterDiv(
          masterTable(
            variables.map{ case (varname, varlabl) =>
              tr(
                td(
                  a(href:=s"$servletPath/codebooks/"+handle+"/vars/"+varname)
                  (varname)
                ),
                td(varlabl)
              )
            }.toSeq
          )
          )
        )
      )
    )
    typedHtml.toString()
  }
  
  
}