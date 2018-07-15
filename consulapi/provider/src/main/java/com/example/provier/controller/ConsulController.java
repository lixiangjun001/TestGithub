package com.example.provier.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsulController {
	
	@RequestMapping("/consul/show")
	public String show() {

		return "哈哈1234";


	}

}
