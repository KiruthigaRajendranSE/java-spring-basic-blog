package com.pluralsight.blog;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {

	@RequestMapping("/home")
	public String listPosts(Map ModelMap) {
		ModelMap.put("title", "Blog Post 1");
		return "home";
	}
}
