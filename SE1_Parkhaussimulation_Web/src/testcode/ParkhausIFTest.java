package testcode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produktionscode.Car;
import produktionscode.Parkhaus;
import produktionscode.Statistik;
import produktionscode.IF_Parkhaus;

class ParkhausIFTest {

	IF_Parkhaus p;
	
	@BeforeEach
	void setup() {
		p = new Parkhaus("0", new ArrayList<Car>(),
				new Statistik(new ArrayList<Double>(), new ArrayList<Double>()));
		
	}
	
	@Test
	@DisplayName("Autos werden korrekt hinzugefuegt")
	void addtest() {
		Car a = new Car("a" , "any");
		p.add(a);
		assertSame(a , p.cars()[0]);
		
	}
	
	@Test
	@DisplayName("Autos werden korrekt entfernt")
	void removetest() {
		Car a = new Car("a" , "any");
		p.add(a);
		assertSame(a, p.remove("a"));
		
	}
	
	@Test
	@DisplayName("Anzahl geparkter Autos wird korrekt ausgegeben")
	void sizeTest() {
		
		for(int i = 0; i < 10; i ++) {
			p.add(new Car(String.valueOf(i) , "any"));
			
		}
		assertSame(10, p.size());
		
	}
	
	@Test
	@DisplayName("Array mit allen geparkten Autos wird korrekt ausgegeben")
	void ArrayTest() {
		
		Car[] carArray = new Car[10];
		for(int i = 0; i < 10; i ++) {
			Car tmp = new Car(String.valueOf(i), "any");
			p.add(tmp);
			carArray[i] = tmp;
		}
		
		assertArrayEquals(carArray, p.cars());
		
		
	}
	
	

}
