package book;

public class Result implements Comparable<Result>{

	private int nbBook;
	private int freeSpace;
	private int usedSpace;
	private float quality;
	
	public Result(int freeSpace){
		nbBook = 0;
		usedSpace = 0;
		this.freeSpace = freeSpace;
	}
	
	public String toString() {
		return "The selection contains " + nbBook + " books with total of "
				+ quality + " quality point and " + freeSpace + " unused space";
	}
	
	public String toString2() {
		return "nbBook=" + nbBook
				+ "used space="+ usedSpace
				+ "free space="+ freeSpace;
	}

	public int getNbBook() {
		return nbBook;
	}
	public void setNbBook(int nbBook) {
		this.nbBook = nbBook;
	}
	public void setUsedSpace(int usedSpace) {
		this.usedSpace = usedSpace;
	}
	public int getUsedSpace() {
		return usedSpace;
	}
	public int getFreeSpace() {
		return freeSpace;
	}
	public void setFreeSpace(int freeSpace) {
		this.freeSpace = freeSpace;
	}

	@Override
	public int compareTo(Result o) {
		return getScore() - o.getScore();
	}

	/**
	 * Calculate a score based on the variable of the result (number of book + average quality - unused space).
	 * 
	 * @return the score of the result.
	 */
	private int getScore() {
		return (int) (nbBook + quality - freeSpace);
	}

	public float getQuality() {
		return quality;
	}

	public void setQuality(float quality) {
		this.quality = quality;
	}
	
}
