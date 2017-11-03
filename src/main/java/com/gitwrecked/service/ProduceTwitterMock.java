package com.gitwrecked.service;

import java.util.List;

import com.gitwrecked.domain.TwitterDTO;

public interface ProduceTwitterMock {
	
	List<TwitterDTO> mockTwitterDTO();
	
	String createMockUser();
}
