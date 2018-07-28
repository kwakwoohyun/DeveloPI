package com.pi.developi;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	@RequestMapping(value = "/about/about", method = RequestMethod.GET)
	public String about(Model model) {
		logger.info("어바웃");
		
		return "about/about";
	}
	@RequestMapping(value = "/board/qna", method = RequestMethod.GET)
	public String qna(Model model) {
		logger.info("Q&A게시판");
		
		return "board/qna/qna";
	}	
	@RequestMapping(value = "/board/notice", method = RequestMethod.GET)
	public String notice(Model model) {
		logger.info("공지게시판");
		
		return "board/notice/notice";
	}
	@RequestMapping(value = "/board/free", method = RequestMethod.GET)
	public String free(Model model) {
		logger.info("자유게시판");
		
		return "board/free/free";
	}
	@RequestMapping(value = "/board/qnaForm", method = RequestMethod.GET)
	public String qnaForm(Model model) {
		logger.info("자유게시판");
		
		return "board/qna/qnaForm";
	}
	@RequestMapping(value = "/board/noticeForm", method = RequestMethod.GET)
	public String noticeForm(Model model) {
		logger.info("자유게시판");
		
		return "board/notice/noticeForm";
	}
	@RequestMapping(value = "/board/freeForm", method = RequestMethod.GET)
	public String freeForm(Model model) {
		logger.info("자유게시판");
		
		return "board/free/freeForm";
	}
}