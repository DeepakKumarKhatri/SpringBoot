package com.deepakLearning.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	// MongoDatabaseFactory helps us to establish connection between app and mongodb
	// (Overall whatever we are doing in our app we are doing it with the help of its instance)
	@Bean
	public PlatformTransactionManager transactionManager(MongoDatabaseFactory mongoDbFactory){
		return new MongoTransactionManager(mongoDbFactory);
	};
}
