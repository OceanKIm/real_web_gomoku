package com.ocean.realomuk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String main() {
		return null;
	}
	
	@GetMapping("/main/home")
	public void home() {
		System.out.println("----------------- ### home ### -----------------");
	}
	
	@GetMapping("/play/playHome")
	public void playHome() {
		
	}

	@GetMapping("/ground")
	public void playGround() {
	}
	
}
