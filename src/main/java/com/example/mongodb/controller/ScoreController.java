package com.example.mongodb.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.mongodb.domain.ScoreDTO;
import com.example.mongodb.service.ScoreMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScoreController {
	ScoreMongoService service;
	@Autowired
	public ScoreController(ScoreMongoService service) {
		super();
		this.service = service;
	}
	//insert페이지보기
	@RequestMapping(value = "/score/insert" , method = RequestMethod.GET)
	public String insertPage() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		return "mongo_insert";
	}
	//mongodb에 insert하기
	@RequestMapping(value = "/score/insert" , method = RequestMethod.POST)
	public String insert(ScoreDTO document) {
		System.out.println("컨트롤러:"+document);
		service.insertDocument(document);
		return "redirect:/score/list";
	}
	
	//search
	@RequestMapping(value="/score/search",method = RequestMethod.GET)
	public String searchPage() {
		return "search";
	}
	@RequestMapping(value="/score/search",method = RequestMethod.POST)
	public ModelAndView search(String field,String criteria, String value) {
		List<ScoreDTO> docs = service.findCriteria(field+","+criteria, value);
		System.out.println(field+","+criteria+value);
		return new ModelAndView("list","mongolist",docs);
	}
	@RequestMapping("/score/list")
	public String mongolist(Model model) {
		List<ScoreDTO> mongolist =  service.findAll();
		model.addAttribute("mongolist", mongolist);
		return "list";
	}
	
	@RequestMapping("/score/detail")
	public ModelAndView findById(@RequestParam("key")String key,@RequestParam("value")String value
			, @RequestParam("action")String action) {
		ScoreDTO document = service.findById(key, value);
		String view="";
		if(action.equals("read")) {
			view= "mongo_detail";
		}else {
			view = "mongo_update";
		}
		return new ModelAndView(view, "document", document);
	}
	@RequestMapping("/score/update")
	public String update(ScoreDTO document) {
		service.update(document);
		return "redirect:/score/paginglist?pageNo=0";
	}
	@RequestMapping("score/multiInsert")
	public String multiInsert() {
		List<ScoreDTO> docs = new ArrayList<ScoreDTO>();
		ScoreDTO dto = null;
		for(int i=1;i<=10;i++) {
			dto = new ScoreDTO(null,"multi"+i, "multi"+i, "기획실","서울특별시",  100,100,100,100);
			docs.add(dto);
		}
		service.insertAllDocument(docs);
		return "redirect:/score/paginglist?pageNo=0";
	}
	@RequestMapping("/score/paginglist")
	public ModelAndView pagemongolist(@RequestParam("pageNo") String pageNo) {
		List<ScoreDTO> mongolist = service.findAll(Integer.parseInt(pageNo));
		return new ModelAndView("list", "mongolist", mongolist);
	}
}
















