package com.gitwrecked.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.gitwrecked.service.ProduceTwitterMock;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class ScheduledTasks {
	
	@Autowired
	ProduceTwitterMock produceTwitterMock;
	
	//push every n seconds
	@Scheduled(fixedRate = 2000)
	public void executePushToKafka(){
//		produceTwitterMock.mockTwitterDTO();
		
		log.info(produceTwitterMock.mockTwitterDTO().toString());
	}

}
