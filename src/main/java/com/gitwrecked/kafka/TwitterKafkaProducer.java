package com.gitwrecked.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gitwrecked.domain.TwitterDTO;
import com.gitwrecked.utils.Constants;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class TwitterKafkaProducer {

	private final static String TOPIC = "twitter-stream";
	private final static String BOOTSTRAP_SERVERS = "localhost:9092";

	private static Producer<Long, String> createProducer() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return new KafkaProducer<>(props);
	}

	static void runProducer(final int sendMessageCount) throws Exception {
		final Producer<Long, String> producer = createProducer();
		long time = System.currentTimeMillis();

		try {
			for (long index = time; index < time + sendMessageCount; index++) {
				final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, index,
						"twitter stream demo " + index);

				RecordMetadata metadata = producer.send(record).get();

				long elapsedTime = System.currentTimeMillis() - time;
				System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d) time=%d\n",
						record.key(), record.value(), metadata.partition(), metadata.offset(), elapsedTime);

			}
		} finally {
			producer.flush();
			producer.close();
		}
	}

	@Scheduled(fixedRate = 5000)
	static void runProducerAsync() throws InterruptedException, JsonProcessingException {
		
		final int sendMessageCount = 5;
		final Producer<Long, String> producer = createProducer();
		long time = System.currentTimeMillis();
		final CountDownLatch countDownLatch = new CountDownLatch(sendMessageCount);
		int min = 1;
		int max = 5000;

		try {
			for (long index = time; index < time + sendMessageCount; index++) {
				TwitterDTO temp = new TwitterDTO();
				temp.setViews((int) (Math.random() * max) + min);
				temp.setUser(createMockUser());
				
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(temp);
				
				final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, index, json);
				producer.send(record, (metadata, exception) -> {
					long elapsedTime = System.currentTimeMillis() - time;
					if (metadata != null) {
						System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d) time=%d\n",
								record.key(), record.value(), metadata.partition(), metadata.offset(), elapsedTime);
					} else {
						exception.printStackTrace();
					}
					countDownLatch.countDown();
				});
			}
			countDownLatch.await(25, TimeUnit.SECONDS);
		} finally {
			producer.flush();
			producer.close();
		}
	}

	public static String createMockUser() {

		int min = 1;
		int max = 10;

		int stringSize = (int) (Math.random() * max) + min;

		char[] charArray = new char[stringSize];

		for (int j = 0; j < stringSize; j++) {
			charArray[j] = Constants.alphabet.charAt(new Random().nextInt(Constants.alphabetLenght));
		}

		return String.valueOf(charArray);
	}

//	public static void main(String... args) throws Exception {
//		if (args.length == 0) {
//			runProducerAsync(5);
//		} else {
//			runProducerAsync(Integer.parseInt(args[0]));
//		}
//	}

}
