package com.tistory.heowc.component;

import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.tistory.heowc.config.RabbitMQConfig;
import com.tistory.heowc.domain.Base;

@Component
public class SendSchedler {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private static final Logger logger = Logger.getLogger(SendSchedler.class);
	
	@Scheduled(cron = "0/3 * * * * *")
	public void onSend() {
		logger.info("Sending message... Start");
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		IntStream.range(1, 15000)
					.parallel()
					.forEach(val -> {
						rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, new Base(val, "Hello, RabbitMQ! 1"));
					});
		stopWatch.stop();
		logger.info(stopWatch.toString());
		logger.info("Sending message... End");
	}
}
