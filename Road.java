package citigraph;

public class Road {
	//miasto z (enum)
	private Cities cityFrom;
	//miasto do (enum)
	private Cities cityTo;
	//odleglosc miedzy miastami
	private long distance;

	public Road(Cities cityFrom, Cities cityTo, long distance) {
		this.cityFrom = cityFrom;
		this.cityTo = cityTo;
		this.distance = distance;
	}

	public Cities getCityFrom() {
		return cityFrom;
	}

	public void setCityFrom(Cities cityFrom) {
		this.cityFrom = cityFrom;
	}

	public Cities getCityTo() {
		return cityTo;
	}

	public void setCityTo(Cities cityTo) {
		this.cityTo = cityTo;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return String.format("%s->%s (%d) ", 
				this.cityFrom.name(), this.cityTo.name(), this.distance);
	}
}
