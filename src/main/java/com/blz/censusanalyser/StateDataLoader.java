package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StateDataLoader {

	public int loadCensusData(String path) throws CensusAnalyserException {
		List<CSVStateCensus> csvUserList;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvBuilderInterface csvBuilder = new CSVBuilderFactory().getCSVBuilder();
			csvUserList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
		} catch (IOException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
					"file is not found");
		} catch (CSVBuilderException exception) {
			throw new CensusAnalyserException(exception.getMessage(), exception.type.name());
		} catch (RuntimeException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.WRONG_FILE,
					"delimiter or header is improper");
		}
		return csvUserList.size();
	}

	public int loadStateCodeData(String path) throws CensusAnalyserException {
		List<IndianStateCode> csvUserList;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvBuilderInterface csvBuilderFactory = new CSVBuilderFactory().getCSVBuilder();
			csvUserList = csvBuilderFactory.getCSVFileList(reader, IndianStateCode.class);

		} catch (IOException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
					"file is not found");
		} catch (CSVBuilderException exception) {
			throw new CensusAnalyserException(exception.getMessage(), exception.type.name());
		} catch (RuntimeException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.WRONG_FILE,
					"delimiter or header is improper");
		}
		return csvUserList.size();
	}

}
