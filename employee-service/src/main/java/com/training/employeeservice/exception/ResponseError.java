package com.training.employeeservice.exception;

import java.time.LocalDate;

public class ResponseError {
private String Message;
private int Code;
private LocalDate timestamp;
public String getMessage() {
	return Message;
}
public void setMessage(String message) {
	Message = message;
}
public int getCode() {
	return Code;
}
public void setCode(int code) {
	Code = code;
}
public LocalDate getTimestamp() {
	return timestamp;
}
public void setTimestamp(LocalDate timestamp) {
	this.timestamp = timestamp;
}
public ResponseError() {
	super();
}
public ResponseError(String message, int code, LocalDate timestamp) {
	super();
	Message = message;
	Code = code;
	this.timestamp = timestamp;
}
@Override
public String toString() {
	return "ResponseError [Message=" + Message + ", Code=" + Code + ", timestamp=" + timestamp + "]";
}

}
