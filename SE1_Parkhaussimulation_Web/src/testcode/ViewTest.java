package testcode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produktionscode.*;

class ViewTest {
	
	Double[] states = new Double[8];
	Parkhaus p;
	
	@BeforeEach
	void setup() {
		p = new Parkhaus(0, new ArrayList<Double>(), new ArrayList<Double>(), new ArrayList<Car>(), states);
		
	}
	@Test
	@DisplayName("Gesamt Einnahmen werden upgedatet")
	void GesamtEinnahmenViewTest() {
		
		GesamtEinnahmenView view = new GesamtEinnahmenView();
		view.subscribe(p);
		assertSame(null, view.getView());
		p.addEinnahme(100, "any");
		assertEquals(100.0, view.getView());
		
	}
	
	@Test
	@DisplayName("Einnahmen AVG wird upgedatet")
	void EinnahmenAvgViewTest() {
		
		EinnahmenAvgView view = new EinnahmenAvgView();
		view.subscribe(p);
		assertEquals(null, view.getView());
		p.addEinnahme(100, "any");
		assertEquals(100.0, view.getView());
		p.addEinnahme(300, "any");
		assertEquals(200.0, view.getView());
		
	}
	
	@Test
	@DisplayName("Parkdauer AVG wird upgedatet")
	void ParkdauerAvgViewTest() {
		
		ParkdauerAvgView view = new ParkdauerAvgView();
		view.subscribe(p);
		assertEquals(null, view.getView());
		p.addParkdauer(100);
		assertEquals(100.0, view.getView());
		p.addParkdauer(300);
		assertEquals(200.0, view.getView());
		
	}
	
	@Test
	@DisplayName("Besucheranzahl wird upgedatet")
	void BesucherAnzahlViewTest() {
		
		BesucherAnzahlView view = new BesucherAnzahlView();
		view.subscribe(p);
		assertEquals(null, view.getView());
		p.add(new Car("1", "any"));
		assertEquals((double) 1, view.getView());
		
	}
	
	@Test
	@DisplayName("Einnahmen MIN wird upgedatet")
	void EinnahmenMinViewTest() {
		
		EinnahmenMinView view = new EinnahmenMinView();
		view.subscribe(p);
		assertEquals(null, view.getView());
		p.addEinnahme(1, "any");
		assertEquals(1.0, view.getView());
		p.addEinnahme(0.5, "any");
		assertEquals(0.5, view.getView());
		
	}
	
	@Test
	@DisplayName("Einnahmen Max wird upgedatet")
	void EinnahmenMaxViewTest() {
		
		EinnahmenMaxView view = new EinnahmenMaxView();
		view.subscribe(p);
		assertEquals(null, view.getView());
		p.addEinnahme(1, "any");
		assertEquals(1.0, view.getView());
		p.addEinnahme(2, "any");
		assertEquals(2.0, view.getView());
		
	}
	
	@Test
	@DisplayName("Parkdauer Min wird upgedatet")
	void ParkdauerMinViewTest() {
		
		ParkdauerMinView view = new ParkdauerMinView();
		view.subscribe(p);
		assertEquals(null, view.getView());
		p.addParkdauer(100);
		assertEquals(100.0, view.getView());
		p.addParkdauer(50);
		assertEquals(50.0, view.getView());
		
	}
	
	@Test
	@DisplayName("Parkdauer Max wird upgedatet")
	void ParkdauerMaxViewTest() {
		
		ParkdauerMaxView view = new ParkdauerMaxView();
		view.subscribe(p);
		assertEquals(null, view.getView());
		p.addParkdauer(100);
		assertEquals(100.0, view.getView());
		p.addParkdauer(200);
		assertEquals(200.0, view.getView());
		
	}
	
	
	

}
