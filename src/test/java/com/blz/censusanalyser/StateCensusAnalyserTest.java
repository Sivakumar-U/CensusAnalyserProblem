package com.blz.censusanalyser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StateCensusAnalyserTest {

	public final String CSV_FILE_PATH = "C:\\Users\\Siva Reddy\\StateCensusData.csv";
	public final String WRONG_CSV_FILE_PATH = "C:\\Users\\Siva Reddy\\StateCensusD.csv";
	public final String WRONG_TYPE_FILE_PATH = "C:\\Users\\Siva Reddy\\StateCensusData.txt";

	public final String STATE_CODE_FILE_PATH = "C:\\Users\\Siva Reddy\\StateCode.csv";

	private static StateCensusAnalyser censusAnalyser;

	@BeforeClass
	public static void createCensusAnalyserObj() {
		censusAnalyser = new StateCensusAnalyser();
	}

	private static StateCodeHandler codeHandler;

	@BeforeClass
	public static void createCodeHandlerObj() {
		codeHandler = new StateCodeHandler();
	}

	// TC-1.1
	@Test
	public void givenStateCsvFile_CheckNumberOfRecords_ShouldReturnNumber() {
		try {
			int numberOfStates = censusAnalyser.loadCensusData(CSV_FILE_PATH);
			Assert.assertEquals(29, numberOfStates);
		} catch (Exception exception) {
			exception.getMessage();
		}
	}

	// TC-1.2
	@Test
	public void givenWrongStateCsvFile_CheckPresentOrNot_ShouldReturnException() {
		try {
			censusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("file is not found", exception.getMessage());
		}
	}

	// TC-1.3
	@Test
	public void givenWrongTypeFile_CheckCsvOrNot_ShouldReturnCustomException() {
		try {
			censusAnalyser.loadCensusData(WRONG_TYPE_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("file is not found", exception.getMessage());
		}
	}

	// TC-1.4
	@Test
	public void givenDelimiterErrorStateCsvFile_CheckPresentOrNot_ShouldReturnCustomException() {
		try {
			censusAnalyser.loadCensusData(CSV_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("delimiter or header is improper", exception.getMessage());
		}
	}

	// TC-1.5
	@Test
	public void givenIndianCensusDataProper_WithImproperHeader_ShouldReturnCustomException() {
		try {
			censusAnalyser.loadCensusData(CSV_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("delimiter or header is improper", exception.getMessage());
		}
	}

	// TC-2.1
	@Test
	public void givenStateCodeCsvFile_CheckNumberOfRecords_ShouldReturnNumber() {
		try {
			int numberOfStates = codeHandler.loadStateCodeData(STATE_CODE_FILE_PATH);
			Assert.assertEquals(37, numberOfStates);
		} catch (Exception exception) {
			exception.getMessage();
		}
	}

}
