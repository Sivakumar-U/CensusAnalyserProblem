package com.blz.censusanalyser;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder implements CsvBuilderInterface {
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {
		try {
			CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader).withType(csvClass).withIgnoreLeadingWhiteSpace(true)
					.build();
			return csvToBean.iterator();
		} catch (RuntimeException exception) {
			throw new CSVBuilderException(CSVBuilderException.exceptionType.WRONG_FILE,
					"delimiter or header is improper");
		}
	}

}
