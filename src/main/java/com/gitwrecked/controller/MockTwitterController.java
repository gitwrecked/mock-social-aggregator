package com.gitwrecked.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gitwrecked.domain.TwitterDTO;
import com.gitwrecked.service.ProduceTwitterMock;

@RestController
public class MockTwitterController {
	
	@Autowired
	ProduceTwitterMock produceTwitterMock;

	@CrossOrigin()
	@RequestMapping("/mock/stream")
	public List<TwitterDTO> mockStream(){
		
		return produceTwitterMock.mockTwitterDTO();
	}
	
}
