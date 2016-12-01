package com.ferdisonmezay.degiro.dto;

public class ResponseDto {
	
	public ResponseDto() {
		
	}
	
	public ResponseDto(String response) {
		this.response = response;
	}
	
	private String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}
