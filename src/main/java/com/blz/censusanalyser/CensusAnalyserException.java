package com.blz.censusanalyser;

public class CensusAnalyserException extends Exception {

	private final exceptionType type;

	public enum exceptionType {
		FILE_NOT_FOUND, WRONG_FILE, NO_CENSUS_DATA
	};

	CensusAnalyserException(String message, String name) {
		super(message);
		this.type = exceptionType.valueOf(name);
	}

	CensusAnalyserException(exceptionType type, String message) {
		super(message);
		this.type = type;
	}

}
