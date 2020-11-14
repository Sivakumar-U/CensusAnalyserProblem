package com.blz.censusanalyser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class StateDataLoaderTest {

	private static final String CSV_FILE_PATH = "C:\\Users\\Siva Reddy\\StateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "C:\\Users\\Siva Reddy\\StateCensusD.csv";
	private static final String WRONG_TYPE_FILE_PATH = "C:\\Users\\Siva Reddy\\StateCensusData.txt";
	private static final String STATE_CODE_FILE_PATH = "C:\\Users\\Siva Reddy\\StateCode.csv";
	private static final String WRONG_TYPE_STATE_CODE_FILE_PATH = "C:\\Users\\Siva Reddy\\StateCode.txt";

	private static StateDataLoader stateDataLoader;

	@BeforeClass
	public static void createStateDataLoaderObj() {
		stateDataLoader = new StateDataLoader();
	}

	// TC-1.1
	@Test
	public void givenStateCsvFile_CheckNumberOfRecords_ShouldReturnNumber() {
		try {
			int numberOfStates = stateDataLoader.loadCensusData(CSV_FILE_PATH);
			Assert.assertEquals(29, numberOfStates);
		} catch (Exception exception) {
			exception.getMessage();
		}
	}

	// TC-1.2
	@Test
	public void givenWrongStateCsvFile_CheckPresentOrNot_ShouldReturnException() {
		try {
			stateDataLoader.loadCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("file is not found", exception.getMessage());
		}
	}

	// TC-1.3
	@Test
	public void givenWrongTypeFile_CheckCsvOrNot_ShouldReturnCustomException() {
		try {
			stateDataLoader.loadCensusData(WRONG_TYPE_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("file is not found", exception.getMessage());
		}
	}

	// TC-1.4
	@Test
	public void givenDelimiterErrorStateCsvFile_CheckPresentOrNot_ShouldReturnCustomException() {
		try {
			stateDataLoader.loadCensusData(CSV_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("delimiter or header is improper", exception.getMessage());
		}
	}

	// TC-1.5
	@Test
	public void givenIndianCensusDataProper_WithImproperHeader_ShouldReturnCustomException() {
		try {
			stateDataLoader.loadCensusData(CSV_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("delimiter or header is improper", exception.getMessage());
		}
	}

	// TC-2.1
	@Test
	public void givenStateCodeCsvFile_CheckNumberOfRecords_ShouldReturnNumber() {
		try {
			int numberOfStates = stateDataLoader.loadStateCodeData(STATE_CODE_FILE_PATH);
			Assert.assertEquals(29, numberOfStates);
		} catch (Exception exception) {
			exception.getMessage();
		}
	}

	// TC-2.2
	@Test
	public void givenWrongStateCodeCsvFile_CheckPresentOrNot_ShouldReturnException() {
		try {
			stateDataLoader.loadStateCodeData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("file is not found", exception.getMessage());
		}
	}

	// TC-2.3
	@Test
	public void givenWrongTypeStateCodeCsvFile_CheckCsvOrNot_ShouldReturnException() {
		try {
			stateDataLoader.loadStateCodeData(WRONG_TYPE_STATE_CODE_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("file is not found", exception.getMessage());
		}
	}

	// TC-2.4
	@Test
	public void givenDelimiterErrorStateCodeFile_CheckPresentOrNot_ShouldReturnCustomException() {
		try {
			stateDataLoader.loadStateCodeData(STATE_CODE_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("delimiter or header is improper", exception.getMessage());
		}
	}

	// TC-2.5
	@Test
	public void givenHeaderErrorStateCodeFile_CheckPresentOrNot_ShouldReturnCustomException() {
		try {

			stateDataLoader.loadStateCodeData(STATE_CODE_FILE_PATH);
		} catch (CensusAnalyserException exception) {
			Assert.assertEquals("delimiter or header is improper", exception.getMessage());
		}
	}

	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnResult() {
		try {
			stateDataLoader.loadCensusData(CSV_FILE_PATH);
			String sortedCensusData = stateDataLoader.getSortedCensusData();
			IndianCensusDao[] csvStateCensuses = new Gson().fromJson(sortedCensusData, IndianCensusDao[].class);
			Assert.assertEquals("Andhra Pradesh", csvStateCensuses[0].state);
		} catch (CensusAnalyserException exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void givenStateCodeData_WhenSortedOnStateCode_ShouldReturnResult() {
		try {
			stateDataLoader.loadCensusData(STATE_CODE_FILE_PATH);
			String sortedCensusData = stateDataLoader.getSortedStateCodeData();
			IndianCensusDao[] indianStateCode = new Gson().fromJson(sortedCensusData, IndianCensusDao[].class);
			Assert.assertEquals("AP", indianStateCode[0].stateCode);
		} catch (CensusAnalyserException exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void givenStateCodeData_WhenSortedOnPopulation_ShouldReturnResult() {
		try {
			stateDataLoader.loadCensusData(CSV_FILE_PATH);
			String sortedCensusData = stateDataLoader.getSortedCensusByPopulation();
			IndianCensusDao[] csvStateCensus = new Gson().fromJson(sortedCensusData, IndianCensusDao[].class);
			Assert.assertEquals(199812341, csvStateCensus[csvStateCensus.length - 1].population);
		} catch (CensusAnalyserException exception) {
			exception.printStackTrace();
		}
	}

}
