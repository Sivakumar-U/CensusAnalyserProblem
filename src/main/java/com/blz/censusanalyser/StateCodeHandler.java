package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCodeHandler {
	private int count;

	public int loadStateCodeData(String path) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			CsvToBean<IndianStateCode> csvToBean = new CsvToBeanBuilder(reader).withType(IndianStateCode.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			Iterator<IndianStateCode> csvUserIterator = csvToBean.iterator();
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
