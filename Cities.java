package citigraph;

public enum Cities {
	GDYNIA(0),
	GDANSK(1),
	LODZ(2),
	KRAKOW(3),
	SZCZECIN(4),
	POZNAN(5),
	WROCLAW(6),
	KATOWICE(7),
	OLSZTYN(8),
	BIALYSTOK(9),
	LUBLIN(10),
	RZESZOW(11),
	WARSZAWA(12),
	KIELCE(13);
	
	private int index;
	
	
	Cities(int idx) {
		this.index = idx; 
	}
	
	public int getIndex() {
		return index;
	}
}
