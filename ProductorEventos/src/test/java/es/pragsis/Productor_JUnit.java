package es.pragsis;

import org.junit.Test;

public class Productor_JUnit {
	
	@Test
	public void readEventsFromFile() {
		Productor productor = new Productor();
		productor.main(null);
	}

}
