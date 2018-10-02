package edu.cornell.ncrn.ced2ar.site.controller

import edu.cornell.ncrn.ced2ar.site.Ced2arConfig
import edu.cornell.ncrn.ced2ar.service.UploadService
import edu.cornell.ncrn.ced2ar.site.view.UploadView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation._
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream


/**
  * Controller class to handle the upload interface for new codebooks.
  *
  * @author kylebrumsted
  * @author Brandon Barker
  *
  */
@Controller class UploadController {
  @Autowired private var uploadService: UploadService = _
  @Autowired private var ced2arConfig: Ced2arConfig = _
  @Autowired private[controller] var view: UploadView = _
  final private val thisPath = "/upload"

  //GET page
  @ResponseBody
  @RequestMapping(
    method = Array(RequestMethod.GET),
    value = Array(thisPath),
    produces = Array(MediaType.TEXT_HTML_VALUE)
  )
  def provideUploadInfo: String = {
    val uploadDir = new File(ced2arConfig.getUploadDir)
    val folderFilesMaybe = Option(uploadDir.listFiles)
    //TODO: change to log message:
    System.out.println(uploadDir.getAbsolutePath)
    //TODO: file list for developer-mode only (add or use a developer/debug-mode flag)
    folderFilesMaybe match {
      case Some(files) =>
        def listFiles(files: Array[File]): String = {
          val uploadedFiles = files.sortWith(_.lastModified() > _.lastModified())
            .map((f: File) => f.getName)
          view.uploadForm(uploadedFiles)
        }

        listFiles(files)
      case None => view.noUploadDir(uploadDir.getAbsolutePath)
    }
  }

  //POST page
  @RequestMapping(method = Array(RequestMethod.POST), value = Array(thisPath))
  def handleFileUpload(
    @RequestParam("name") name: String,
    @RequestParam("file") file: MultipartFile,
    redirectAttributes: RedirectAttributes
  ): String = {
    if (name.contains("/")) {
      redirectAttributes.addFlashAttribute("message", "Folder separators not allowed")
      return "redirect:" + thisPath
    }
    if (name.contains("/")) {
      redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed")
      return "redirect:" + thisPath
    }
    if (!file.isEmpty) try {
      val newXmlFile = new File(ced2arConfig.getUploadDir + "/" + name)
      val stream = new BufferedOutputStream(new FileOutputStream(newXmlFile))
      FileCopyUtils.copy(file.getInputStream, stream)
      stream.close()
      //Process file
      uploadService.newUpload(newXmlFile)
      if (uploadService.importSucceded)
        redirectAttributes.addFlashAttribute("success", "You successfully uploaded " + name + "!")
      else if (!uploadService.uploadIsValid)
        redirectAttributes.addFlashAttribute("error", "There was a problem parsing XML for " + name + "!")
      else redirectAttributes.addFlashAttribute(
        "error", "There was an unknown problem with importing " + name + "!"
      )
    } catch {
      case e: Exception =>
        redirectAttributes.addFlashAttribute(
          "error", "You failed to upload " + name + " => " + e.getMessage
        )
    }
    else
      redirectAttributes.addFlashAttribute(
        "error", "You failed to upload " + name + " because the file was empty"
      )
    view.setAttributes(redirectAttributes)
    "redirect:" + thisPath
  }
}