package com.blz.censusanalyser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

public class StateDataLoader {
	Map<String, IndianCensusDao> csvStateCensusMap = new HashMap<String, IndianCensusDao>();

	public int loadCensusData(String path) throws CensusAnalyserException {
		return new LoadStateClass().loadStateData(path, CSVStateCensus.class, csvStateCensusMap);
	}

	public int loadStateCodeData(String path) throws CensusAnalyserException {
		return new LoadStateClass().loadStateData(path, IndianStateCode.class, csvStateCensusMap);
	}

	public String getSortedCensusData() throws CensusAnalyserException {
		if (csvStateCensusMap == null || csvStateCensusMap.size() == 0)
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.NO_CENSUS_DATA, "no census data");
		Comparator<IndianCensusDao> censusComparator = Comparator.comparing(census -> census.state);
		List<IndianCensusDao> sortedList = this.sort(censusComparator, new ArrayList<>(csvStateCensusMap.values()));
		return new Gson().toJson(sortedList);
	}

	public String getSortedStateCodeData() throws CensusAnalyserException {
		if (csvStateCensusMap == null || csvStateCensusMap.size() == 0)
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.NO_CENSUS_DATA, "no census data");
		Comparator<IndianCensusDao> stateCodeComparator = Comparator.comparing(census -> census.stateCode);
		List<IndianCensusDao> sortedList = this.sort(stateCodeComparator, new ArrayList<>(csvStateCensusMap.values()));
		return new Gson().toJson(sortedList);
	}

	public String getSortedCensusByPopulation() throws CensusAnalyserException {
		if (csvStateCensusMap == null || csvStateCensusMap.size() == 0)
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.NO_CENSUS_DATA, "no census data");
		Comparator<IndianCensusDao> stateCodeComparator = Comparator.comparing(census -> census.population);
		List<IndianCensusDao> sortedList = this.sort(stateCodeComparator, new ArrayList<>(csvStateCensusMap.values()));
		return new Gson().toJson(sortedList);
	}

	public String getSortedCensusByPopulationDensity() throws CensusAnalyserException {
		if (csvStateCensusMap == null || csvStateCensusMap.size() == 0)
			throw new CensusAnalyserException(CensusAnalyserException.exceptionType.NO_CENSUS_DATA, "no census data");
		Comparator<IndianCensusDao> stateCodeComparator = Comparator.comparing(census -> census.densityPerSqKm);
		List<IndianCensusDao> sortedList = this.sort(stateCodeComparator, new ArrayList<>(csvStateCensusMap.values()));
		return new Gson().toJson(sortedList);
	}

	private <E> List<E> sort(Comparator<E> comparator, List<E> censusList) {
		for (int index1 = 0; index1 < censusList.size() - 1; index1++) {
			for (int index2 = 0; index2 < censusList.size() - index1 - 1; index2++) {
				E census1 = (E) censusList.get(index2);
				E census2 = (E) censusList.get(index2 + 1);
				if (comparator.compare(census1, census2) > 0) {
					censusList.set(index2, census2);
					censusList.set(index2 + 1, census1);
				}
			}

		}
		return censusList;
	}

}
