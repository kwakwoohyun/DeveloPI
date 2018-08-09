package com.pi.developi.freeBoard.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pi.developi.category.service.CategoryService;
import com.pi.developi.freeBoard.domain.FreeArticleDTO;
import com.pi.developi.freeBoard.domain.FreeReplyDTO;
import com.pi.developi.freeBoard.service.FreeBoardService;

@Controller
@RequestMapping(value = "/board/free")
public class FreeBoardController {
	private static final Logger logger = LoggerFactory.getLogger(FreeBoardController.class);
	
	@Inject
	FreeBoardService freeBoardService;
	
	@Inject
	CategoryService categoryService;
	
	@RequestMapping(value= "")
	public ModelAndView list() throws Exception{
		logger.info("자유게시판 list");
		List<FreeArticleDTO> list=freeBoardService.listAll();
		List<String> user=freeBoardService.userId();
		List<Integer> replyNum=freeBoardService.replyNum();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("board/free/freeList");
		mav.addObject("list",list);
		mav.addObject("user",user);
		mav.addObject("reply",replyNum);
		return mav;
	}
	
	@RequestMapping(value= "/freeDetail",method = RequestMethod.GET)
	public ModelAndView Detail(@RequestParam int article_no,HttpSession session) throws Exception{
		logger.info("자유게시판 Detail");
		freeBoardService.upHit(article_no, session);
		FreeArticleDTO Detail=freeBoardService.detail(article_no);
		String category=categoryService.getName(Detail.getCategory_no());
		String user=freeBoardService.detailId(article_no);
		List<FreeReplyDTO> reply=freeBoardService.replyList(article_no);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("board/free/freeDetail");
		mav.addObject("detail",Detail);
		mav.addObject("user",user);
		mav.addObject("reply",reply);
		mav.addObject("category",category);
		return mav;
	}
	
	@RequestMapping(value= "/freeForm",method = RequestMethod.GET)
	public ModelAndView WriteForm(@RequestParam int board_no) throws Exception{
		logger.info("자유게시판 Form");
		List<String> Category=freeBoardService.listCategory(board_no);
		logger.info(Category.get(0));
		ModelAndView mav=new ModelAndView();
		mav.setViewName("board/free/freeForm");
		mav.addObject("detail",Category);
		return mav;
	}
	
	@RequestMapping(value= "/write",method = RequestMethod.POST)
	public String Write(@ModelAttribute FreeArticleDTO dto) throws Exception{
		
		logger.info(dto.getCategory_no()+"");
		freeBoardService.write(dto);
		logger.info("작성완료");
		return "redirect: /board/free";
	}
	
	@RequestMapping(value= "/updateForm",method = RequestMethod.GET)
	public ModelAndView Update(@RequestParam int no,@RequestParam String title,@RequestParam String content)  throws Exception{
		logger.info("업데이트 Form");
		FreeArticleDTO dto=new FreeArticleDTO();
		dto.setArticle_no(no);
		dto.setTitle(title);
		dto.setContent(content);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("board/free/freeUpdate");
		mav.addObject("dto",dto);
		return mav;
	}
	
	@RequestMapping(value= "/update",method = RequestMethod.POST)
	public String Update(@ModelAttribute FreeArticleDTO dto) throws Exception{
		
		logger.info("게시글 수정");
		freeBoardService.update(dto);
		logger.info("수정완료");
		return "redirect: /board/free";
	}
	
	@RequestMapping(value= "/delete",method = RequestMethod.GET)
	public String Update(@RequestParam int no)  throws Exception{
		logger.info("게시글 삭제");
		freeBoardService.delete(no);
		
		return "redirect: /board/free";
	}
	
	@RequestMapping(value= "/search",method = RequestMethod.POST)
	public ModelAndView Search(HttpServletRequest req)  throws Exception{
		logger.info("검색");
		List<FreeArticleDTO> list=freeBoardService.search(req.getParameter("searchType"),req.getParameter("keyword"));
		ModelAndView mav=new ModelAndView();
		mav.setViewName("board/free/freeList");
		mav.addObject("list",list);
		return mav;
	}
	
	@RequestMapping(value= "/replyWrite",method = RequestMethod.POST)
	public String replyWrite(@ModelAttribute FreeReplyDTO dto) throws Exception{

		freeBoardService.replyWrite(dto);
		logger.info("작성완료");
		return "redirect: /board/free/freeDetail?article_no="+dto.getArticle_no();
	}
	@RequestMapping(value= "/replyArticle",method = RequestMethod.GET)
	public ModelAndView replyArticle(@RequestParam String title, @RequestParam String content,@RequestParam int category_no,
			@RequestParam int article_no,@RequestParam int group_no,@RequestParam int step,@RequestParam int indent) throws Exception{
		FreeArticleDTO dto=new FreeArticleDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setCategory_no(category_no);
		dto.setArticle_no(article_no);
		dto.setGroup_no(group_no);
		dto.setStep(step);
		dto.setIndent(indent);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("board/free/freeReplyForm");
		mav.addObject("article",dto);
		return mav;
	}
	
	@RequestMapping(value= "/replyArticleWrite",method = RequestMethod.POST)
	public String replyArticleWrite(@ModelAttribute FreeArticleDTO dto) throws Exception{
		freeBoardService.indentUp(dto);
		freeBoardService.replyArticle(dto);
		logger.info("작성완료");
		return "redirect:/board/free";
	}
}
