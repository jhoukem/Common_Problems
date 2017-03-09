
import java.util.ArrayList;
import java.util.Collections;


public class Main {

	private static boolean VERBOSE = false;


	public static void main(String[] args) {

		int size = 100;
		int iteration = 1000;
		int freeSpace = 50;
		Book books[] = new Book[size];
		ArrayList<Result> results = new ArrayList<Result>();

		for (int i = 0; i < size; i++) {	
			int quality = (int) (Math.random() * 10) + 1;
			int weight = (int) (Math.random() * 10) + 1;

			Book book = new Book(quality, weight);
			books[i] = book;
		}

		if(VERBOSE){
			for (int i = 0; i < size; i++) {
				System.out.println("Book number " + i + " weight " + books[i].getWeight() + " and a "
						+ books[i].getQuality() + " QA value");
			}
		}

		// Calculate x iteration.
		for(int i = 0; i < iteration; i++){
			results.add(calculResult(books, size, freeSpace));
			for (int j = 0; j < size; j++) {
				books[j].setTaken(false);
			}
		}
		// Sort the result and keep the best one.
		Collections.sort(results);
		System.out.println("The best result of the " + iteration + " iterations is :\n"
				+ results.get(0).toString());

	}


	private static Result calculResult(Book[] books, int size, int freeSpace) {

		Result result = new Result(freeSpace);
		int selection;

		while (hasSpace(result.getFreeSpace(), books, size)) {

			do {
				selection = (int) (Math.random() * size);

			} while (books[selection].isTaken());

			int bookWeight = books[selection].getWeight();

			// If the book can be added to the selection.
			if (result.getFreeSpace() - bookWeight >= 0) {
				result.setFreeSpace(result.getFreeSpace() - bookWeight);
				result.setUsedSpace(result.getUsedSpace() + bookWeight);
				books[selection].setTaken(true);
				result.setNbBook(result.getNbBook() + 1);
			}

		}
		return result;

	}

	private static boolean hasSpace(int freeSpace, Book[] books, int size) {

		for(int i = 0; i < size; i++){
			if(!books[i].isTaken() && (freeSpace - books[i].getWeight() >= 0)){
				return true;
			}
		}
		return false;
	}
}
