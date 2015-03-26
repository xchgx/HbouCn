package com.xchgx.controller.image;

import com.xchgx.service.image.IdentifyingCodeService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IdentifyingCodeController {

	@Autowired
	private IdentifyingCodeService identifyingCodeService;
	
	@RequestMapping("/identifyingCodeImageSrc")
	public ModelAndView identifyingCodeImageSrc(HttpServletRequest request){
		System.out.println("in identifyingCodeImageSrc()... ");
		String imageFileSrc = request.getSession().getServletContext().getRealPath("/identifyingCode.jpg");
		identifyingCodeService.makeIdentifyingCodeImage(imageFileSrc);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("identifyingCodeImageSrc");
		mav.addObject("bufferedImage", identifyingCodeService.makeIdentifyingCodeBufferedImage());
		mav.addObject("src","/identifyingCode.jpg");
		mav.addObject("realPath",request.getSession().getServletContext().getRealPath("/identifyingCode.jpg"));
		return mav;
	}
}
