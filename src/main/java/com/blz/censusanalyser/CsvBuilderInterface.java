package com.blz.censusanalyser;

import java.io.Reader;
import java.util.List;

public interface CsvBuilderInterface {
	public <E> List<E> getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException;

}
