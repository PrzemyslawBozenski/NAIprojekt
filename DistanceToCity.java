package citigraph;

public class DistanceToCity implements Comparable<DistanceToCity> {
	
	private Cities city;
	private long distance;
	
	public DistanceToCity(Cities city, long distance) {
		this.city = city;
		this.distance = distance;
	}
	
	public Cities getCity() {
		return city;
	}
	public void setCity(Cities city) {
		this.city = city;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	
	@Override
	public int compareTo(DistanceToCity o) {
		int cmp = new Long(distance).compareTo(o.getDistance());

		if (cmp == 0) {
			return new Integer(city.getIndex()).compareTo(o.getCity().getIndex());
		}
		return 0;
	}

	
}
