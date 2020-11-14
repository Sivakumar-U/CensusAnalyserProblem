package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class LoadStateClass {
	public <E> int loadStateData(String path, Class<E> csvClass, Map<String, IndianCensusDao> csvStateCensusMap)
			throws CensusAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvBuilderInterface csvBuilder = CSVBuilderFactory.getCSVBuilder();
			Iterator<E> iteratorCSVCensus = csvBuilder.getCSVFileIterator(reader, csvClass);
			Iterable<E> csvStateCensusIterable = () -> iteratorCSVCensus;
			if (csvClass.getName().equals("com.blz.censusanalyser.CSVStateCensus")) {
				StreamSupport.stream(csvStateCensusIterable.spliterator(), false).map(CSVStateCensus.class::cast)
						.forEach(censusCSV -> csvStateCensusMap.put(censusCSV.state, new IndianCensusDao(censusCSV)));
				return csvStateCensusMap.size();
			}
			if (csvClass.getName().equals("com.blz.censusanalyser.IndianStateCode")) {
				StreamSupport.stream(csvStateCensusIterable.spliterator(), false).map(IndianStateCode.class::cast)
						.filter(censusCSV -> csvStateCensusMap.get(censusCSV.stateName) != null)
						.forEach(censusCSV -> csvStateCensusMap
								.get(censusCSV.stateName).stateCode = censusCSV.stateCode);
				return csvStateCensusMap.size();
			}
		} catch (IOException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.FILE_NOT_FOUND,
					"file is not found");
		} catch (CSVBuilderException exception) {
			throw new CensusAnalyserException(exception.getMessage(), exception.type.name());
		} catch (RuntimeException exception) {
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.WRONG_FILE,
					"delimiter or header is improper");
		}
		return 0;
	}

}
