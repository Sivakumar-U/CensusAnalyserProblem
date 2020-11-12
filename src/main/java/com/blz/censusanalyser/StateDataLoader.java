package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;

public class StateDataLoader {
	List<CSVStateCensus> csvUserList = null;

	public int loadCensusData(String path) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvBuilderInterface csvBuilder = CSVBuilderFactory.getCSVBuilder();
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
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvBuilderInterface csvBuilder = CSVBuilderFactory.getCSVBuilder();
			csvUserList = csvBuilder.getCSVFileList(reader, IndianStateCode.class);

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

	public String getSortedCensusData() throws CensusAnalyserException {
		if (csvUserList == null || csvUserList.size() == 0)
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.NO_CENSUS_DATA, "no census data");
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(censusComparator);
		String sortedStateCensusJson = new Gson().toJson(csvUserList);
		return sortedStateCensusJson;
	}

	private void sort(Comparator<CSVStateCensus> censusComparator) {
		for (int index1 = 0; index1 < csvUserList.size() - 1; index1++) {
			for (int index2 = 0; index2 < csvUserList.size() - index1 - 1; index2++) {
				CSVStateCensus census1 = csvUserList.get(index2);
				CSVStateCensus census2 = csvUserList.get(index2 + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					csvUserList.set(index2, census2);
					csvUserList.set(index2 + 1, census1);
				}
			}
		}
	}

}
