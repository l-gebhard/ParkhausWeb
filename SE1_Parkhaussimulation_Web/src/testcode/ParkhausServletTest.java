package testcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produktionscode.Car;
import produktionscode.Parkhaus;

class ParkhausServletTest {

	Parkhaus p;
	
	@BeforeEach
	void setup() {
		
		Double[] states = new Double[7];
		
		p = new Parkhaus(0, new ArrayList<Double>(), new ArrayList<Double>(), new ArrayList<Car>(), states);
	}
	
	@Test
	@DisplayName("Einnahmen pro Kategorie Json String wird korrekt erzeugt")
	void ChartTest() {

		
		 JsonObject actual = Json.createObjectBuilder()
				 .add("data", Json.createArrayBuilder()
						 .add(Json.createObjectBuilder()
								 .add("x", Json.createArrayBuilder()
										 .add("Frauen")
										 .add("Any")
										 .add("Behinderte")
										 .add("Familien")
										 )
								 .add("y", Json.createArrayBuilder()
										 .add(5)
										 .add(20)
										 .add(3)
										 .add(10)
										 )
								 .add("type", "bar")
								 .add("name", "Einnahmen pro Kategorie")
								 )
				).build();
				
		String expected = "{\"data\":[{\"x\":[\"Frauen\",\"Any\",\"Behinderte\",\"Familien\"],\"y\":[5,20,3,10],\"type\":\"bar\",\"name\":\"Einnahmen pro Kategorie\"}]}";
		
		assertEquals(expected, actual.toString());
	}
	
	@Test
	@DisplayName("Anteil Besucher Json String wird korrekt erzeugt")
	void FamilyChartTest() {

		p.add(new Car("0", "Frau"));
		p.add(new Car("1", "Behinderte"));
		
		int[] besuchergesamt = p.getGesamtBesucherArray();
		
		 JsonObject actual = Json.createObjectBuilder()
				 .add("data", Json.createArrayBuilder()
						 .add(Json.createObjectBuilder()
								 .add("values", Json.createArrayBuilder()
										 .add(besuchergesamt[0])
										 .add(besuchergesamt[1])
										 .add(besuchergesamt[2])
										 .add(besuchergesamt[3])
										 )
								 .add("labels", Json.createArrayBuilder()
										 .add("Frauen")
										 .add("Any")
										 .add("Behinderte")
										 .add("Familien")
										 )
								 .add("type", "pie")
						)
				).build();
		String expected = "{\"data\":[{\"values\":[1,0,1,0],\"labels\":[\"Frauen\",\"Any\",\"Behinderte\",\"Familien\"],\"type\":\"pie\"}]}";
		
		assertEquals(expected, actual.toString());
		
		
	}

}
