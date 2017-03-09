package mariage;

import java.util.ArrayList;


public class Convive {

	private int data[];
	private boolean hasSeat = false;

	public Convive(String line[]){	

		data = new int[line.length];
		for(int i = 0; i< line.length; i++){
			data[i] = Integer.parseInt(line[i]);
		}
	}

	public int getID(){
		return data[0];
	}

	public ArrayList<Integer> getUnsupported(){
		ArrayList<Integer> unsupported = new ArrayList<Integer>();

		for(int i = 1; i < data.length; i++){
			unsupported.add(data[i]);
		}
		return unsupported;
	}

	public String printUnsupported() {
		String out = new String();

		if(data.length > 1){	
			for(int i = 1; i < data.length; i++){

				out += data[i];
				if(i+1 < data.length){
					out += ",";
				}
			}
		} else {
			out = "none";
		}
		out += ".";
		return out;
	}

	public boolean support(Convive b) {

		for(int i = 1; i < data.length; i++){
			if(data[i] == b.getID())
				return false;
		}
		return true;
	}
	
	public boolean support(int idConvive) {
		for(int i = 1; i < data.length; i++){
			if(data[i] == idConvive)
				return false;
		}
		return true;
	}

	public boolean isHasSeat() {
		return hasSeat;
	}

	public void setHasSeat(boolean hasSeat) {
		this.hasSeat = hasSeat;
	}

}
