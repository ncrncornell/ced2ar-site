package edu.cornell.ncrn.ced2ar.site.view

import scalatags.Text.all._

import edu.cornell.ncrn.ced2ar.site.view.common.Ced2arView
import edu.cornell.ncrn.ced2ar.service.api.CodebookNames
import org.springframework.stereotype.Component

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