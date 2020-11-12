package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateDataLoader {

	public int loadCensusData(String path) throws CensusAnalyserException {
		Iterator<CSVStateCensus> csvUserIterator = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			csvUserIterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStateCensus.class);
		} catch (NoSuchFileException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
					"file is not found");
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return getCount(csvUserIterator);
	}

	public int loadStateCodeData(String path) throws CensusAnalyserException {
		Iterator<IndianStateCode> csvUserIterator = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			csvUserIterator = new OpenCSVBuilder().getCSVFileIterator(reader, IndianStateCode.class);
		} catch (NoSuchFileException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
					"file is not found");
		} catch (IOException exception) {
			exception.printStackTrace();
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
