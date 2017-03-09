package mariage;

import java.io.IOException;
import java.util.ArrayList;


public class Main {


	public static void main(String[] args) {

		Loader ld = new Loader();
		try {

			ArrayList<Convive> convives = ld.getData("example2.txt");
			/*for(int i = 0; i < convives.size(); i++){
				System.out.println("The convive " + convives.get(i).getID() 
						+ " doesn't support the convives : " + convives.get(i).printUnsupported());
			}*/


			ArrayList<ArrayList<Table>> result = new ArrayList<ArrayList<Table>>();


			for(int k = 0; k < 1000; k++){
				result.add(calculSet(convives));
				for(Convive c : convives){
					c.placed = false;
				}
			}

			ArrayList<Table> best = result.get(0);

			for(ArrayList<Table> tableList: result){
				if(tableList.size() < best.size()){
					best = tableList;
				}
			}

			System.out.println("le Nombre min de table trouvées est de "
					+ best.size());


			for(int i = 0; i < best.size(); i++){
				Table t = best.get(i);
				System.out.println("Convives table " + (i+1) + ":\n"
						+ t.displayConvive());
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<Table> calculSet(ArrayList<Convive> convives) {

		ArrayList<Table> tables = new ArrayList<Table>();
		tables.add(new Table());
		// On place le premier convive.
		int alea;// = (int) (Math.random() * convives.size());
		//tables.get(0).add(convives.get(alea));
		//convives.get(alea).placed = true;
		boolean allPlaced = false;
		boolean needNewTable = false;


		while(!allPlaced){
			Convive a;

			do {
				alea = (int) (Math.random() * convives.size());
				a = convives.get(alea);
			}
			while(a.placed || !tables.get(tables.size() - 1).support(a));

			tables.get(tables.size() - 1).add(a);
			a.placed = true;


			allPlaced = true;
			needNewTable = true;
			for(Convive c : convives){
				if(!c.placed){
					allPlaced = false;
				}
				// Si on peut placer un convive sur la table
				if(!c.placed && tables.get(tables.size() - 1).support(c)){
					needNewTable = false;
				}
			}

			if(needNewTable && !allPlaced){
				tables.add(new Table());
			}

		}


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