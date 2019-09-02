package citigraph;

import java.util.ArrayList;
import java.util.List;

public class CityMap {
	// liczba miast
	private int numberOfCities;
	// liczba dróg
	private int numberOfRoads;
	// listy sasiedztwa
	private List<Road>[] nearCityList;
	
	public CityMap() {
		this.numberOfCities = Cities.values().length;
		this.numberOfRoads = 0;
		this.nearCityList = (List<Road>[]) new List[numberOfCities];
		for (int i = 0; i < numberOfCities; i++) {
			nearCityList[i] = new ArrayList<Road>();
		}
	}

	public int getNumberOfCities() {
		return numberOfCities;
	}

	public int getNumberOfRoads() {
		return numberOfRoads;
	}

	/**
	 * Dodaje droge do odpowiedniej listy najblizszych miast, listy
	 * wierzcholka (miasta), z ktorej zaczyna sie krawedz (droga).
	 * 
	 * @param road
	 *            krawedz, ktora ma zostac dodana do grafu
	 */
	public void addRoad(Road road) {
		nearCityList[road.getCityFrom().getIndex()].add(road);
		numberOfRoads++;
	}

	/**
	 * Zwraca liste nastepnikow danego miasta.
	 * 
	 * @param city
	 *            indeks miasta
	 * @return zwraca iterowalna kolekcje krawedzi (drog)
	 */
	public Iterable<Road> getNearCityList(int city) {
		return nearCityList[city];
	}
}

