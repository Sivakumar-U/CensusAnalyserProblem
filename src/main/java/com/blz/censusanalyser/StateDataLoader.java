package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateDataLoader {
	int count;

	public int loadCensusData(String path) throws CensusAnalyserException {

		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			Iterator<CSVStateCensus> csvUserIterator = this.getCSVFileIterator(reader, CSVStateCensus.class);
			while (csvUserIterator.hasNext()) {
				count++;
				csvUserIterator.next();
			}
		} catch (NoSuchFileException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
					"file is not found");
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return count;
	}

	public int loadStateCodeData(String path) throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			Iterator<IndianStateCode> csvUserIterator = this.getCSVFileIterator(reader, IndianStateCode.class);
			while (csvUserIterator.hasNext()) {
				count++;
				csvUserIterator.next();
			}
		} catch (NoSuchFileException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
					"file is not found");
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return count;
	}

	private <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException {
		try {
			CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader).withType(csvClass).withIgnoreLeadingWhiteSpace(true)
					.build();
			return csvToBean.iterator();
		} catch (RuntimeException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.WRONG_FILE,
					"delimiter or header is improper");
		}
	}

}
