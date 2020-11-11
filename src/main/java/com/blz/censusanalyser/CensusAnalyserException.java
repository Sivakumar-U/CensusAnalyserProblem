package com.blz.censusanalyser;

public class CensusAnalyserException extends Exception {

	private final exceptionType type;

	public enum exceptionType {
		FILE_NOT_FOUND
	};

	CensusAnalyserException(exceptionType type, String message) {
		super(message);
		this.type = type;
	}

}
