package citigraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class ShortestWay {
	// przechowuje sciezki (od pkt zrodlowego do koncowego najkrotsza)
	private Road[] roads;
	// przechowuje najkrotsze znalezione odleglosci do danego wierzcholka
	private Long[] distanceTo;
	// kolejka przechowujaca biezace krawedzie uszeregowane od najkrotszej do
	// najdluzszej
	private Queue<DistanceToCity> roadQueue;
	

	public ShortestWay(CityMap citiMap, Cities sourceCity) {
		// inicjacja wewnetrznych struktur
		roads = new Road[citiMap.getNumberOfCities()];
		distanceTo = new Long[citiMap.getNumberOfCities()];
		roadQueue = new PriorityQueue<DistanceToCity>(citiMap.getNumberOfCities());

		// dla kazdego miasta city najkrotsza droge inicjujemy najwieksza mozliwa wartoscia
		for (int city = 0; city < citiMap.getNumberOfCities(); city++) {
			distanceTo[city] = Long.MAX_VALUE;
		}
		// odleglosc do wierzcholka zrodlowego to 0
		distanceTo[sourceCity.getIndex()] = 0L;

		// wstawiamy wierzholek zrodlowy (miasto - start) do koejki, od niego rozpoczynamy
		// poszukiwania
		roadQueue.offer(new DistanceToCity(sourceCity, 0L));

		// przeprowadzamy relaksacje grafu dopoki kolejka nie jest pusta
		while (!roadQueue.isEmpty()) {
			relax(citiMap, roadQueue.poll().getCity());
	}
//		while (!roadQueue.isEmpty()) {
//			relax(citiMap, roadQueue.peek().getCity());
//		}

	}

	private void relax(CityMap cityMap, Cities city) {
		// przegladamy listy sasiedztwa (nastêpniki) wszystkich wierzcholkow (miast)
		for (Road road : cityMap.getNearCityList(city.getIndex())) {
			 Cities cityTo = road.getCityTo();

			// sprawdzamy czy zmiana wierzcholka skroci dystans z wierzcholka
			// zrodlowego
			if (distanceTo[cityTo.getIndex()] > distanceTo[city.getIndex()] + road.getDistance()) {
				distanceTo[cityTo.getIndex()] = distanceTo[city.getIndex()] + road.getDistance();
				roads[cityTo.getIndex()] = road;
//			 if (distanceTo[cityTo.getIndex()] == Long.MAX_VALUE) {
//					distanceTo[cityTo.getIndex()] = distanceTo[city.getIndex()] + road.getDistance();
//					roads[cityTo.getIndex()] = road;
				
				DistanceToCity dtc = new DistanceToCity(cityTo, distanceTo[cityTo.getIndex()]);
				// jesli jest juz taka odleglosc to ja usuwamy, bo znalezlismy lepsza droge
				roadQueue.remove(dtc);
				// wstawiamy nowa drogê z aktualna znaleziona odlegloscia
				roadQueue.offer(dtc);
				
			}
		}
	}
	
	// jesli zwrocona wartosc wynosi Long.MAX_VALUE to oznacza, ze dany
	// wierzcholek jest nieosiagalny ze zrodla
	public long getDistanceTo(Cities city) {
		return distanceTo[city.getIndex()];
	}

	// jesli istnieje droga do danego miasta to jest ona mniejsza od
	// Long.MAX_VALUE, ktore zostalo inicjalnie ustawione dla wszystkich miast
	public boolean hasPathTo(Cities city) {
		return distanceTo[city.getIndex()] < Long.MAX_VALUE;
	}

	// jesli nie istnieje droga do danego miasta zostanie zwrocona pusta kolekcja
	public Iterable<Road> getPathTo(Cities city) {
		Deque<Road> path = new ArrayDeque<Road>();
		// jesli nie istnieje sciezka zwroc pusta sciezke
		if (!hasPathTo(city)) {
			return path;
		}
		// dopoki istnieje krawedz dodawaj ja do stosu
		// krawedz zrodlowa jest oznaczona jako null
		for (Road e = roads[city.getIndex()]; e != null; e = roads[e.getCityFrom().getIndex()]) {
			path.push(e);
		}
		return path;
	}
	
	public static void main(String[] args) {
		CityMap cityMap = new CityMap();
		cityMap.addRoad(new Road(Cities.GDYNIA, Cities.SZCZECIN, 339));
		cityMap.addRoad(new Road(Cities.GDYNIA, Cities.GDANSK, 22));
		cityMap.addRoad(new Road(Cities.GDANSK, Cities.POZNAN, 309));
		cityMap.addRoad(new Road(Cities.GDANSK, Cities.OLSZTYN, 166));
		cityMap.addRoad(new Road(Cities.GDANSK, Cities.WARSZAWA, 339));
		cityMap.addRoad(new Road(Cities.GDANSK, Cities.LODZ, 324));
		cityMap.addRoad(new Road(Cities.POZNAN, Cities.SZCZECIN, 309));
		cityMap.addRoad(new Road(Cities.POZNAN, Cities.WROCLAW, 174));
		cityMap.addRoad(new Road(Cities.POZNAN, Cities.LODZ, 205));
		cityMap.addRoad(new Road(Cities.LODZ, Cities.WROCLAW, 216));
		cityMap.addRoad(new Road(Cities.LODZ, Cities.KATOWICE, 200));
		cityMap.addRoad(new Road(Cities.LODZ, Cities.KRAKOW, 267));
		cityMap.addRoad(new Road(Cities.LODZ, Cities.WARSZAWA, 140));
		cityMap.addRoad(new Road(Cities.WROCLAW, Cities.KATOWICE, 191));
		cityMap.addRoad(new Road(Cities.KATOWICE, Cities.KRAKOW, 81));
		cityMap.addRoad(new Road(Cities.KRAKOW, Cities.RZESZOW, 168));
		cityMap.addRoad(new Road(Cities.KRAKOW, Cities.LUBLIN, 306));
		cityMap.addRoad(new Road(Cities.KRAKOW, Cities.KIELCE, 121));
		cityMap.addRoad(new Road(Cities.KIELCE, Cities.WARSZAWA, 176));
		cityMap.addRoad(new Road(Cities.WARSZAWA, Cities.LUBLIN, 178));
		cityMap.addRoad(new Road(Cities.RZESZOW, Cities.LUBLIN, 177));
		cityMap.addRoad(new Road(Cities.BIALYSTOK, Cities.LUBLIN, 246));
		cityMap.addRoad(new Road(Cities.OLSZTYN, Cities.BIALYSTOK, 233));
		cityMap.addRoad(new Road(Cities.OLSZTYN, Cities.WARSZAWA, 214));

		cityMap.addRoad(new Road(Cities.SZCZECIN, Cities.GDYNIA, 339));
		cityMap.addRoad(new Road(Cities.GDANSK, Cities.GDYNIA, 22));
		cityMap.addRoad(new Road(Cities.POZNAN, Cities.GDANSK, 309));
		cityMap.addRoad(new Road(Cities.OLSZTYN, Cities.GDANSK, 166));
		cityMap.addRoad(new Road(Cities.WARSZAWA, Cities.GDANSK, 339));
		cityMap.addRoad(new Road(Cities.LODZ, Cities.GDANSK, 324));
		cityMap.addRoad(new Road(Cities.SZCZECIN, Cities.POZNAN, 309));
		cityMap.addRoad(new Road(Cities.WROCLAW, Cities.POZNAN, 174));
		cityMap.addRoad(new Road(Cities.LODZ, Cities.POZNAN, 205));
		cityMap.addRoad(new Road(Cities.WROCLAW, Cities.LODZ, 216));
		cityMap.addRoad(new Road(Cities.KATOWICE, Cities.LODZ, 200));
		cityMap.addRoad(new Road(Cities.KRAKOW, Cities.LODZ, 267));
		cityMap.addRoad(new Road(Cities.WARSZAWA, Cities.LODZ, 140));
		cityMap.addRoad(new Road(Cities.KATOWICE, Cities.WROCLAW, 191));
		cityMap.addRoad(new Road(Cities.KRAKOW, Cities.KATOWICE, 81));
		cityMap.addRoad(new Road(Cities.RZESZOW, Cities.KRAKOW, 168));
		cityMap.addRoad(new Road(Cities.LUBLIN, Cities.KRAKOW, 306));
		cityMap.addRoad(new Road(Cities.KIELCE, Cities.KRAKOW, 121));
		cityMap.addRoad(new Road(Cities.WARSZAWA, Cities.KIELCE, 176));
		cityMap.addRoad(new Road(Cities.LUBLIN, Cities.WARSZAWA, 178));
		cityMap.addRoad(new Road(Cities.LUBLIN, Cities.RZESZOW, 177));
		cityMap.addRoad(new Road(Cities.LUBLIN, Cities.BIALYSTOK, 246));
		cityMap.addRoad(new Road(Cities.BIALYSTOK, Cities.OLSZTYN, 233));
		cityMap.addRoad(new Road(Cities.WARSZAWA, Cities.OLSZTYN, 214));
		
		List<ShortestPath> allShortestPaths = new ArrayList<ShortestPath>();
		for (Cities sourceCity : Cities.values()) {
//			Cities sourceCity = Cities.GDANSK;
			ShortestWay shortestPath = new ShortestWay(cityMap, sourceCity);
	
			// Wyswietla najkrotsza odleglosc i sciezke, ktora te odleglosc zbudowala 
			for(Cities targetCity : Cities.values()) {
				if (shortestPath.hasPathTo(targetCity)) {
					System.out.printf("%s do %s (%d)  ", sourceCity.name(), targetCity.name(),
							shortestPath.getDistanceTo(targetCity));
					allShortestPaths.add(
							new ShortestPath(sourceCity, targetCity, shortestPath.getDistanceTo(targetCity)));
					for (Road road : shortestPath.getPathTo(targetCity)) {
						System.out.print(road);
					}
				} else {
					System.out.printf("%s do %s - brak sciezki  ", sourceCity.name(), targetCity.name());
				}
				System.out.println();
			}
		}
		// inicjalizacja zmiennej dowolnymi danymi (odleg³oœæ 0)
		ShortestPath maxShortestPath = new ShortestPath(Cities.BIALYSTOK, Cities.BIALYSTOK, 0L);
		for (ShortestPath path : allShortestPaths) {
			if (maxShortestPath.getShortestPath() < path.getShortestPath()) {
				maxShortestPath = path;
			}
		}
		System.out.printf("Najbardziej oddalone od siebie miasta: %s - %s (%d)", 
				maxShortestPath.getSourceCity(), maxShortestPath.getTargetCity(), maxShortestPath.getShortestPath());
	}

}
