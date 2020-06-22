package testcode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produktionscode.Car;
import produktionscode.CarIF;

class CarIFTest {
	
	CarIF a;
	
	@BeforeEach
	void setup() {
		a = new Car("1", "any");
		
	}
	
	
	@Test
	@DisplayName("Erstellung eines Autos und Get Methoden funktionieren")
	void Autotest() {
		assertEquals("any", a.getArt());
		assertEquals("1", a.getID());
	}

}
