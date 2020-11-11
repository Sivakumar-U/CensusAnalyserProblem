package com.blz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {

	@CsvBindByName(column = "State", required = true)
	public String state;

	@CsvBindByName(column = "Population", required = true)
	public int population;

	@CsvBindByName(column = "AreaInSqKm")
	public int areaInSqKm;

	@CsvBindByName(column = "DensityPerSqKm")
	public int densityPerSqKm;

	public CSVStateCensus() {

	}

	public int getAreaInSqKm() {
		return areaInSqKm;
	}

	public void setAreaInSqKm(int areaInSqKm) {
		this.areaInSqKm = areaInSqKm;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getDensityPerSqKm() {
		return densityPerSqKm;
	}

	public void setDensityPerSqKm(int densityPerSqKm) {
		this.densityPerSqKm = densityPerSqKm;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "IndiaCensusCSV{" + "State='" + state + '\'' + ", Population='" + population + '\'' + ", AreaInSqKm='"
				+ areaInSqKm + '\'' + ", DensityPerSqKm='" + densityPerSqKm + '\'' + '}';
	}

}
