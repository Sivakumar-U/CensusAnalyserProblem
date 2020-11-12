package com.blz.censusanalyser;

public class CSVBuilderException extends Exception {
	public final exceptionType type;

	public enum exceptionType {
		FILE_NOT_FOUND, WRONG_FILE
	};

	public CSVBuilderException(exceptionType type, String message) {
		super(message);
		this.type = type;
	}

}
