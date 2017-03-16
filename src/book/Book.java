package book;

public class Book {

	private int id, weight;
	private float quality;
	private boolean isTaken;
	
	public Book(int quality, int weight){
		this.quality = quality;
		this.weight = weight;
		isTaken = false;
	}
	
	public Book(String[] strings){
		this.setId(Integer.parseInt(strings[0]));
		this.quality = Float.parseFloat(strings[1]);
		this.weight = Integer.parseInt(strings[2]);
		isTaken = false;
	}
	
	public float getQuality() {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getScore() {
		return quality/weight;
	}
	
	public String toString(){
		return "Book number " + id + " weight="+ weight +" QA="+quality;
	}
	
}
