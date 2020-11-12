package com.blz.censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface CsvBuilderInterface {
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;

}
