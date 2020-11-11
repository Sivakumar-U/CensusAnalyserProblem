package com.blz.censusanalyser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StateCensusAnalyserTest {

	public final String CSV_FILE_PATH = "C:\\Users\\Siva Reddy\\StateCensusData.csv";
	public final String WRONG_CSV_FILE_PATH = "C:\\Users\\Siva Reddy\\StateCensusD.csv";

	private static StateCensusAnalyser censusAnalyser;

	@BeforeClass
	public static void createCensusAnalyserObj() {
		censusAnalyser = new StateCensusAnalyser();
	}

	@Test
	public void givenStateCsvFile_CheckNumberOfRecords_ShouldReturnNumber() {
		try {
			int numberOfStates = censusAnalyser.loadCensusData(CSV_FILE_PATH);
			Assert.assertEquals(29, numberOfStates);
		} catch (Exception ex) {
			ex.getMessage();
		}
	}

	@Test
	public void givenWrongStateCsvFile_CheckPresentOrNot_ShouldReturnException() {
		try {
			int numberOfStates = censusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("file is not found", exception.getMessage());
		}
	}
}
