package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import mariage.Convive;
import mariage.Loader;
import mariage.Main;
import mariage.Table;

import org.junit.Test;

public class MariageTest {

	private static final String RSC_DATA_WEDDING1 = "rsc/data_wedding1.txt";
	private static final String RSC_DATA_WEDDING2 = "rsc/data_wedding2.txt";

	@Test
	public void testCalcul() {


		Loader ld = new Loader();
		try {

			ArrayList<Convive> convives = ld.getConviveData(RSC_DATA_WEDDING2);

			// Array of result set.
			ArrayList<ArrayList<Table>> result = new ArrayList<ArrayList<Table>>();

			// Calculate 1000 results.
			for(int k = 0; k < 100; k++){
				result.add(Main.calculSet(convives));
				// Reset the data between each calculation.
				for(Convive c : convives){
					c.setHasSeat(false);
				}
			}

			//Check the 1000 results.
			for(int k = 0; k < 100; k++){
				ArrayList<Table> tableList = result.get(k);

				// On each table
				for(Table table : tableList){
					// Verify that each convive support everyone else on his table.
					for(Convive a : table.getConvives()){
						for(Convive b : table.getConvives()){
							assertTrue(a.support(b));
							assertTrue(b.support(a));
						}
					}
				}
			}

		} catch (IOException e) {
			fail("IOException");
		}
	}

	@Test
	public void testSupportFunction() {

		Loader ld = new Loader();
		try {
			ArrayList<Convive> convives = ld.getConviveData(RSC_DATA_WEDDING1);
			
			// Assert that the convive 1 doesn't support the 2 and 5.
			Convive c = convives.get(0);
			assertFalse(c.support(2));
			assertFalse(c.support(5));
			// But does support the others.
			assertTrue(c.support(0));
			assertTrue(c.support(1));
			assertTrue(c.support(3));
			assertTrue(c.support(4));
			assertTrue(c.support(6));
			assertTrue(c.support(7));
			
			
		} catch (IOException e) {
			fail("IOException");
		}
	}

}
