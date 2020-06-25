package testcode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produktionscode.*;

class StatistikTest {

	Statistik s;
	
	@BeforeEach
	void setup() {
		s =	new Statistik(new ArrayList<Double>(), new ArrayList<Double>());
		
	}
	
	@Test
	@DisplayName("Einnahme wird korrekt gespeichert")
	void addEinnahmeTest() {
		s.addEinnahme(100, "any");
		
		//Einnahme wird durch 100 geteilt wg. Euro
		assertEquals(1, s.getGesamtEinnahmen());
		
	}
	
	@Test
	@DisplayName("Parkdauer wird korrekt gespeichert")
	void addParkdauerest() {
		s.addParkdauer(1000);
		
		//Parkdauer wird aufgrund von Sekunden durch 1000 geteilt
		assertEquals(1, s.getParkdauerMin());
		
		
	}
	
	@Test
	@DisplayName("Statistik ueber Besucher beim Hinzufuegen wird korrekt aktualisiert")
	void addBesucherTest() {
		assertEquals(0, s.getGesamtBesucher());
		s.addBesucher("any");
		assertEquals(1, s.getGesamtBesucher());
		
	}

	@Test
	@DisplayName("Statistik ueber Besucher beim Entfernen wird korrekt aktualisiert")
	void removeBesucherTest() {
		assertEquals(0, s.getGesamtBesucher());
		s.removeBesucher("any");
		assertEquals(0, s.getGesamtBesucher());
		s.addBesucher("any");
		assertEquals(1, s.getGesamtBesucher());
		
	}
	
	@Test
	@DisplayName("BesucherArray wird korrekt ausgegeben")
	void getGesamtBesucherArrayTest() {
		
		int[] BesucherArray = {1,1,1,1};
		
		s.addBesucher("any");
		s.addBesucher("Familie");
		s.addBesucher("Frau");
		s.addBesucher("Behinderte");
		assertArrayEquals(BesucherArray, s.getGesamtBesucherArray());
		
	}
	
	@Test
	@DisplayName("EinnahmeArray wird korrekt ausgegeben")
	void getEinnahmeKategorieArrayTest() {
		
		double[] einnahmeKategorieArray = {1, 1, 1, 1};
		
		s.addEinnahme(100 , "any");
		s.addEinnahme(100, "Familie");
		s.addEinnahme(100, "Frau");
		s.addEinnahme(100, "Behinderte");
		assertArrayEquals(einnahmeKategorieArray, s.getEinnahmenKategorieArray());
		
	}
	

}
