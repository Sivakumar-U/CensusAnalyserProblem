package com.blz.censusanalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder implements CsvBuilderInterface {
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {
		return (Iterator<E>) this.getCsvBean(reader, csvClass).iterator();
	}

	@Override
	public <E> List<E> getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException {
		return (List<E>) this.getCsvBean(reader, csvClass).parse();
	}

	private <E> CsvToBean<E> getCsvBean(Reader reader, Class csvClass) throws CSVBuilderException {
		try {
			CsvToBeanBuilder csvToBeanBulider = new CsvToBeanBuilder(reader).withType(csvClass)
					.withIgnoreLeadingWhiteSpace(true);
			return csvToBeanBulider.build();
		} catch (RuntimeException exception) {
			throw new CSVBuilderException(CSVBuilderException.exceptionType.WRONG_FILE,
					"delimiter or header is improper");
		}
	}

}
