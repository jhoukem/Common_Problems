package mariage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loader {



	public ArrayList<Convive> getData(String path) throws IOException{

		ArrayList<Convive> data = new ArrayList<Convive>();

		FileInputStream fstream = new FileInputStream(path);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;

		//First line specify the number of line.
		br.readLine();

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
			data.add(new Convive(strLine.split(" ")));
		}

		return data;
	}

}
