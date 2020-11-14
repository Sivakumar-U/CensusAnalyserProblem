package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;

public class StateDataLoader {
	List<CSVStateCensus> csvCensusList = null;
	List<IndianStateCode> csvStateList = null;

	public int loadCensusData(String path) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvBuilderInterface csvBuilder = CSVBuilderFactory.getCSVBuilder();
			csvCensusList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
		} catch (IOException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
					"file is not found");
		} catch (CSVBuilderException exception) {
			throw new CensusAnalyserException(exception.getMessage(), exception.type.name());
		} catch (RuntimeException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.WRONG_FILE,
					"delimiter or header is improper");
		}
		return csvCensusList.size();
	}

	public int loadStateCodeData(String path) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvBuilderInterface csvBuilder = CSVBuilderFactory.getCSVBuilder();
			csvStateList = csvBuilder.getCSVFileList(reader, IndianStateCode.class);

		} catch (IOException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
					"file is not found");
		} catch (CSVBuilderException exception) {
			throw new CensusAnalyserException(exception.getMessage(), exception.type.name());
		} catch (RuntimeException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.WRONG_FILE,
					"delimiter or header is improper");
		}
		return csvStateList.size();
	}

	public String getSortedCensusData() throws CensusAnalyserException {
		if (csvCensusList == null || csvCensusList.size() == 0)
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.NO_CENSUS_DATA, "no census data");
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(censusComparator, csvCensusList);
		String sortedStateCensusJson = new Gson().toJson(csvCensusList);
		return sortedStateCensusJson;
	}

	public String getSortedStateCodeData() throws CensusAnalyserException {
		if (csvStateList == null || csvStateList.size() == 0)
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.NO_CENSUS_DATA, "no census data");
		Comparator<IndianStateCode> indianStateCodeComparator = Comparator.comparing(census -> census.stateCode);
		this.sort(indianStateCodeComparator, csvStateList);
		String sortedStateCodeJson = new Gson().toJson(csvStateList);
		return sortedStateCodeJson;
	}

	private <E> void sort(Comparator<E> comparator, List<E> censusList) {
		for (int index1 = 0; index1 < censusList.size() - 1; index1++) {
			for (int index2 = 0; index2 < censusList.size() - index1 - 1; index2++) {
				E census1 = (E) censusList.get(index2);
				E census2 = (E) censusList.get(index2 + 1);
				if (comparator.compare(census1, census2) > 0) {
					censusList.set(index2, census2);
					censusList.set(index2 + 1, census1);
				}
			}

		}
	}

}
