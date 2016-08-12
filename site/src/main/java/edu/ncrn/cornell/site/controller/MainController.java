package edu.ncrn.cornell.site.controller;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ncrn.cornell.site.view.AboutView;

@Controller
public class MainController {
	
	@Autowired
	private AboutView aboutView;
	
	@ResponseBody
	@RequestMapping(value = "/about",
					method = RequestMethod.GET,
					produces = MediaType.TEXT_HTML_VALUE
					)
	public String welcome(Model model) {
		
		
		return aboutView.aboutPage();
	}
	
	@RequestMapping(value = "/",
					method = RequestMethod.GET
					)
	public String home(Model model){
		return "redirect:codebooks";
	}

}
