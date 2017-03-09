package mariage;

import java.io.IOException;
import java.util.ArrayList;


public class Main {

	private static boolean VERBOSE = false;

	public static void main(String[] args) {

		Loader ld = new Loader();
		try {

			ArrayList<Convive> convives = ld.getData("example2.txt");

			if(VERBOSE){
				for(int i = 0; i < convives.size(); i++){
					System.out.println("The convive " + convives.get(i).getID() 
							+ " doesn't support the convive(s) : " + convives.get(i).printUnsupported());
				}
			}

			// Array of result set.
			ArrayList<ArrayList<Table>> result = new ArrayList<ArrayList<Table>>();

			// Calculate 1000 results.
			for(int k = 0; k < 1000; k++){
				result.add(calculSet(convives));
				// Reset the data between each calculation.
				for(Convive c : convives){
					c.hasSeat = false;
				}
			}

			// Keep the best one.
			ArrayList<Table> best = result.get(0);
			for(ArrayList<Table> tableList: result){
				if(tableList.size() < best.size()){
					best = tableList;
				}
			}

			
			// Display result.
			System.out.println("Minimum number of table found is: "
					+ best.size());

			// Display details.
			for(int i = 0; i < best.size(); i++){
				Table t = best.get(i);
				System.out.println("Convives on table " + (i+1) + ":\n"
						+ t.displayConvive());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<Table> calculSet(ArrayList<Convive> convives) {

		ArrayList<Table> tables = new ArrayList<Table>();
		// Add the first table.
		tables.add(new Table());
		
		int alea;
		boolean allPlaced = false;
		boolean needNewTable = false;


		while(!allPlaced){
			Convive a;

			do {
				alea = (int) (Math.random() * convives.size());
				a = convives.get(alea);
			}
			while(a.hasSeat || !tables.get(tables.size() - 1).support(a));

			// Add the convive to the table.
			tables.get(tables.size() - 1).add(a);
			a.hasSeat = true;

			// Check for adding new table and stopping the calculation.
			allPlaced = true;
			needNewTable = true;
			for(Convive c : convives){
				if(!c.hasSeat){
					allPlaced = false;
				}
				// If the table has a seat for the convive.
				if(!c.hasSeat && tables.get(tables.size() - 1).support(c)){
					needNewTable = false;
				}
			}
			// Create a new table if necessary.
			if(needNewTable && !allPlaced){
				tables.add(new Table());
			}

		}


		/**
		 * This version is more predictable because it try to place the convive
		 * as they appears ordered in the list. 
		 **/
		/*
		  while(!allPlaced){

			for(int i = 0; i < convives.size(); i++){
				Convive a = convives.get(i);
				if(a.placed || !tables.get(tables.size() - 1).support(a))
					continue;
				tables.get(tables.size() - 1).add(a);
				a.placed = true;
			}

			allPlaced = true;
			for(Convive c : convives){
				if(!c.placed){
					allPlaced = false;
					tables.add(new Table());
					break;
				}
			}
		}
		 */

		/*

		System.out.println("Nb tables = " + tables.size());

		for(int i = 0; i < tables.size(); i++){
			Table t = tables.get(i);
			System.out.println("Convives table " + (i+1) + ":\n"
					+ t.displayConvive());
		}
		 */

		return tables;
	}
}