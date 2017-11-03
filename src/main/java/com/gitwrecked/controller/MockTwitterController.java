package com.gitwrecked.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gitwrecked.domain.TwitterDTO;

@RestController
public class MockTwitterController {

	@RequestMapping("/mock/stream")
	public List<TwitterDTO> mockStream(){
		
		List<TwitterDTO> twitterDTOList = new ArrayList <TwitterDTO>();
		
		Random random = new Random(1234567);
		
		int min = 1;
		int max = 20000;
		
		for(int i = 0 ; i < 1000; i ++){
			TwitterDTO temp = new TwitterDTO();
			temp.setViews((int)(Math.random() * max) + min);
			temp.setUser("paul");
			twitterDTOList.add(temp);
		}
		
		
		return twitterDTOList;
	}
	
}
