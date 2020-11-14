package com.blz.censusanalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface CsvBuilderInterface {
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;

	public <E> List<E> getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException;

}
