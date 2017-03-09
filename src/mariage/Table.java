package mariage;

import java.util.ArrayList;

public class Table {

	private ArrayList<Convive> convives = new ArrayList<Convive>();

	public void add(Convive a) {
		convives.add(a);
	}

	public boolean support(Convive a) {
		for(Convive c : convives){
			if(!c.support(a))
				return false;
		}
		return true;
	}

	public String displayConvive() {
		String out = new String();

		for(Convive c : convives){
			out += c.getID() + " ";
		}
		return out;
	}

	public ArrayList<Convive> getConvives() {
		return convives;
	}

}
