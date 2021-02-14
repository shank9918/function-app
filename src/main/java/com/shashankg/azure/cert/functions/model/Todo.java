package com.shashankg.azure.cert.functions.model;

public class Todo {

	private String id;
	private User user;
	private String todoItem;
	private Status status;
}

enum Status {
	SCHEDULED,
	IN_PROGRESS,
	COMPLETED
}

