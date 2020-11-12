package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateDataLoader {

	public int loadCensusData(String path) throws CensusAnalyserException {
		Iterator<CSVStateCensus> csvUserIterator = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvBuilderInterface csvBuilder = new CSVBuilderFactory().getCSVBuilder();
			csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
		} catch (IOException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
					"file is not found");
		} catch (CSVBuilderException exception) {
			throw new CensusAnalyserException(exception.getMessage(), exception.type.name());
		}
		return getCount(csvUserIterator);
	}

	public int loadStateCodeData(String path) throws CensusAnalyserException {
		Iterator<IndianStateCode> csvUserIterator = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvBuilderInterface csvBuilderFactory = new CSVBuilderFactory().getCSVBuilder();
			csvBuilderFactory.getCSVFileIterator(reader, IndianStateCode.class);

		} catch (IOException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
					"file is not found");
		} catch (CSVBuilderException exception) {
			throw new CensusAnalyserException(exception.getMessage(), exception.type.name());
		}
		return getCount(csvUserIterator);
	}

	private <E> int getCount(Iterator<E> csvUserIterator) {
		int count = 0;
		while (csvUserIterator.hasNext()) {
			count++;
			csvUserIterator.next();
		}
		return count;
	}

}
