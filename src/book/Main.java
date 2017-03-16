package book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Main {

	private static boolean VERBOSE = true;


	public static void main(String[] args) {

		int iteration = 1000;
		int freeSpace;
		String path = "rsc/data_book2.txt";
		ArrayList<Result> randomResults = new ArrayList<Result>();
		ArrayList<Book> books;

		Loader loader = new Loader();
		try {
			books = loader.getBookData(path);
			freeSpace = loader.getFreeSpace(path);
			System.out.println("Free space = " + freeSpace);
			if(VERBOSE){
				for (int i = 0; i < books.size(); i++) {
					System.out.println(books.get(i).toString());
				}
			}

			// Calculate x iteration.
			for(int i = 0; i < iteration; i++){
				randomResults.add(calculResultRandom(books, freeSpace));
				for (int j = 0; j < books.size(); j++) {
					books.get(j).setTaken(false);
				}
			}
			// Sort the result and keep the best one.
			Collections.sort(randomResults);
			System.out.println("The best random result of the " + iteration + " iterations is :\n"
					+ randomResults.get(0).toString());
			System.out.println("The best glouton result of the book selection is :\n"
					+ calculResultGlouton(books, freeSpace).toString());
			System.out.println("The best suppression result of the book selection is :\n"
					+ calculResultSuppression(books, freeSpace).toString());


		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static Result calculResultSuppression(ArrayList<Book> books, int freeSpace) {

		Result result = new Result(freeSpace);

		// Add all the books.
		for(Book book : books){
			result.setFreeSpace(result.getFreeSpace() - book.getWeight());
			result.setUsedSpace(result.getUsedSpace() + book.getWeight());
			result.setNbBook(result.getNbBook() + 1);
			result.setQuality(result.getQuality() + book.getQuality());
			book.setTaken(true);
		}


		while (result.getFreeSpace() < 0){

			Book book = getNextBook(books, BOOK_STATE.NOT_FREE);

			// Try to remove a bad one.
			for(Book next : books){
				if(next.isTaken() && next.getScore() < book.getScore()){
					book = next;
				}
			}

			result.setFreeSpace(result.getFreeSpace() + book.getWeight());
			result.setUsedSpace(result.getUsedSpace() - book.getWeight());
			result.setNbBook(result.getNbBook() - 1);
			result.setQuality(result.getQuality() - book.getQuality());
			book.setTaken(false);

		}
		return result;
	}


	private static Result calculResultGlouton(ArrayList<Book> books, int freeSpace) {

		Result result = new Result(freeSpace);

		while (hasSpace(result.getFreeSpace(), books)) {
			Book book = getNextBook(books, BOOK_STATE.FREE);

			// Try to find a better one.
			for(Book next : books){
				if(!next.isTaken() && next.getScore() > book.getScore()
						&& result.getFreeSpace() - next.getWeight() >= 0){
					book = next;
				}
			}
			result.setFreeSpace(result.getFreeSpace() - book.getWeight());
			result.setUsedSpace(result.getUsedSpace() + book.getWeight());
			result.setNbBook(result.getNbBook() + 1);
			result.setQuality(result.getQuality() + book.getQuality());
			book.setTaken(true);
		}
		return result;
	}

	private static Result calculResultRandom(ArrayList<Book> books, int freeSpace) {

		Result result = new Result(freeSpace);
		Book book;
		while (hasSpace(result.getFreeSpace(), books)) {

			do {
				book = books.get((int) (Math.random() * books.size()));
			} while(book.isTaken());

			// If the book can be added to the selection.
			if (result.getFreeSpace() - book.getWeight() >= 0) {
				result.setFreeSpace(result.getFreeSpace() - book.getWeight());
				result.setUsedSpace(result.getUsedSpace() + book.getWeight());
				result.setQuality(result.getQuality() + book.getQuality());
				result.setNbBook(result.getNbBook() + 1);
				book.setTaken(true);
			}
		}
		return result;
	}

	private static Book getNextBook(ArrayList<Book> books, BOOK_STATE state) {

		for(Book b : books){
			if(!b.isTaken() && state == BOOK_STATE.FREE ||
					b.isTaken() && state == BOOK_STATE.NOT_FREE){
				return b;
			}
		}
		return null;
	}


	private static boolean hasSpace(int freeSpace, ArrayList<Book> books) {

		for(Book book : books){
			if(!book.isTaken() && (freeSpace - book.getWeight() >= 0)){
				return true;
			}
		}
		return false;
	}

	public enum BOOK_STATE {
		FREE, NOT_FREE;
	}

}
