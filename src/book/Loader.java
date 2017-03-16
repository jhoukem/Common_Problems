package book;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loader {

	public ArrayList<Book> getBookData(String path) throws IOException{

		ArrayList<Book> data = new ArrayList<Book>();

		FileInputStream fstream = new FileInputStream(path);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;

		//First line specify the number of line.
		br.readLine();

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
			data.add(new Book(strLine.split(" ")));
		}
		return data;
	}

	public int getFreeSpace(String path) throws IOException {

		FileInputStream fstream = new FileInputStream(path);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;

		//First line specify the number of line.
		strLine = br.readLine();
		
		return Integer.parseInt(strLine.split(" ")[1]);
	}

}
