package citigraph;

public class ShortestPath {

	Cities sourceCity; 
	Cities targetCity;
	Long shortestPath;
	
	
	public ShortestPath(Cities sourceCity, Cities targetCity, Long shortestPath) {
		super();
		this.sourceCity = sourceCity;
		this.targetCity = targetCity;
		this.shortestPath = shortestPath;
	}
	
	public Cities getSourceCity() {
		return sourceCity;
	}
	public void setSourceCity(Cities sourceCity) {
		this.sourceCity = sourceCity;
	}
	public Cities getTargetCity() {
		return targetCity;
	}
	public void setTargetCity(Cities targetCity) {
		this.targetCity = targetCity;
	}
	public Long getShortestPath() {
		return shortestPath;
	}
	public void setShortestPath(Long shortestPath) {
		this.shortestPath = shortestPath;
	}
}
