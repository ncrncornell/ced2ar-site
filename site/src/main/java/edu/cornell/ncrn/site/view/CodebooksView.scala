package edu.cornell.ncrn.site.view

import edu.cornell.ncrn.service.api.CodebookNames
import edu.cornell.ncrn.site.view.common.Ced2arView
import org.springframework.stereotype.Component

import scalatags.Text.all._

@Component
class CodebooksView extends Ced2arView{
  
  def codebooksList(
    codebooks : CodebookNames
  ) : String = {
    val typedHtml = html(
        head(
        defaultMetaTags,
        defaultStyleSheetsAndScripts//,
      ),
      body(masterDiv(
        topBanner,
        navBar,
        ol(cls:="breadcrumb")(
          li(cls:="active")("Codebooks")
        ),
        indentDiv(h1("Codebooks"),
        masterDiv(
          codebooks.map{ case (cbHandle, cbName) =>
            p(
              a(href:=s"$servletPath/codebook/"+cbHandle)(
                cbName
              )
             )
          }.toSeq
        )
      )))
    )
    typedHtml.toString()
  }
  
  
}