package com.panchoriz.myapp.dbconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.panchoriz.myapp.repositories.RepositoryPackage;

@Configuration
@EnableMongoRepositories(basePackageClasses=RepositoryPackage.class)
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "spring-data";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		MongoClient client = new MongoClient("localhost");
		client.setWriteConcern(WriteConcern.SAFE);
		return client;
	}
	
	@Override
	protected String getMappingBasePackage() {
		return "com.panchoriz.myapp.domain";
	}
	
}
