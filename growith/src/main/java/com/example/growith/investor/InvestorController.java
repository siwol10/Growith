package com.example.growith.investor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/investor")
@Controller
public class InvestorController {
	@GetMapping("/details")
	public String details() {
		return "investor_info_details";
	}
	
	@GetMapping("/press")
	public String press() {
		return "investor_info_press";
	}
	
	@GetMapping("/financial")
	public String financial() {
		return "investor_info_financial";
	}
}
