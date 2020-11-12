package com.blz.censusanalyser;

public class CSVBuilderFactory {
	public static CsvBuilderInterface getCSVBuilder() {
		return new OpenCSVBuilder();
	}

}
