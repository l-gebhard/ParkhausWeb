package testcode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import produktionscode.Car;
import produktionscode.IF_Parkhaus;
import produktionscode.Parkhaus;
import produktionscode.Statistik;

class UndoTest {

	IF_Parkhaus p;
	
	@BeforeEach
	void setup() {
		p = new Parkhaus("0", new ArrayList<Car>(),
				new Statistik(new ArrayList<Double>(), new ArrayList<Double>()));
		
	}
	
	@Test
	void undoTest() {
		
		p.add(new Car("1", "any"));
		
	}

}
