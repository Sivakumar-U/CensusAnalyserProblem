package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	private int count;

	public int loadCensusData(String path) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader).withType(CSVStateCensus.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			Iterator<CSVStateCensus> csvUserIterator = csvToBean.iterator();
			while (csvUserIterator.hasNext()) {
				count++;
				csvUserIterator.next();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return count;
	}

}
