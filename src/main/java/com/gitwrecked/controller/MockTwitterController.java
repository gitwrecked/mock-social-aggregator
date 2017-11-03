package com.gitwrecked.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gitwrecked.domain.TwitterDTO;
import com.gitwrecked.utils.Constants;

@RestController
public class MockTwitterController {

	@RequestMapping("/mock/stream")
	public List<TwitterDTO> mockStream(){
		
		return mockTwitterDTO();
	}

	
	
	private List<TwitterDTO> mockTwitterDTO() {
		
		List<TwitterDTO> twitterDTOList = new ArrayList <TwitterDTO>();
		int min = 1;
		int max = 5000;
		
		for(int i = 0 ; i < max; i ++){
			TwitterDTO temp = new TwitterDTO();
			temp.setViews((int)(Math.random() * max) + min);
			temp.setUser(createMockUser());
			twitterDTOList.add(temp);
		}
		
		return twitterDTOList;
	}

	private String createMockUser() {
		
		int min = 1;
		int max = 10;
		
		int stringSize = (int)(Math.random() * max) + min;
		
		char[] charArray = new char[stringSize];
		
	    for (int j = 0; j < stringSize; j++) {
	        charArray[j] = Constants.alphabet.charAt(new Random().nextInt(Constants.alphabetLenght));
	    }
	    
	    return String.valueOf(charArray);
	}
	
}
