package com.blz.censusanalyser;

public class IndianCensusDao {
	public String state;
	public String stateCode;
	public int population;
	public int areaInSqKm;
	public int densityPerSqKm;

	public IndianCensusDao(CSVStateCensus csvStateCensus) {
		state = csvStateCensus.state;
		population = csvStateCensus.population;
		areaInSqKm = csvStateCensus.areaInSqKm;
		densityPerSqKm = csvStateCensus.densityPerSqKm;
	}

}
