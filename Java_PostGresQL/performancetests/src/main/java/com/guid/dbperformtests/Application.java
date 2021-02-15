package com.guid.dbperformtests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		// JDBCTest test = new JDBCTest();
		// test.createTable();
		// test.oneMillionBatchRecordInsertTest();
		// test.disconnect();
	}
}
