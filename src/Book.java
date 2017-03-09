
public class Book {

	private int quality, weight;
	private boolean isTaken;
	
	public Book(int quality, int weight){
		this.quality = quality;
		this.weight = weight;
		isTaken = false;
	}
	
	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}
	
}
