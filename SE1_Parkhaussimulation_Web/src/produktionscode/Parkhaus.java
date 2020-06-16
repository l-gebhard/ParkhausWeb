package produktionscode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.DoubleStream;

public class Parkhaus implements ParkhausIF{
	
	private int id;
	private int currentFrauen;
	private int currentAny;
	private int currentFamilie;
	private int currentBehinderte;
	private int currentBesucher;
	
	private int gesamtBesucher;
	private int gesamtFrauen;
	private int gesamtAny;
	private int gesamtFamilie;
	private int gesamtBehinderte;
	
	private double einnahmenFrauen;
	private double einnahmenAny;
	private double einnahmenFamilie;
	private double einnahmenBehinderte;
	
	private List<Double> parkdauerList;
	private List<Double> einnahmen;
	private List<Car> carlist;
	
	
	public Parkhaus(int id, List<Double> parkdauerliste, List<Double> einnahmen, List<Car>carlist) {
		this.id = id;	
		this.parkdauerList = parkdauerliste;
		this.einnahmen = einnahmen;
		this.carlist = carlist;
		
	}
	
	@Override
	public void add(Car c) {
		carlist.add(c);
		gesamtBesucher++;
		currentBesucher++;
		
		switch(c.getArt()){
		case "Frau": {
			currentFrauen++;
			gesamtFrauen++;
			break;
		}
		case "any": {
			currentAny++;
			gesamtAny++;
			break;
		}
		case "Familie": {
			currentFamilie++;
			gesamtFamilie++;
			break;
		}
		case "Behinderte": 
			currentBehinderte++; 
			gesamtBehinderte++;
			break;
		default: System.out.println("Fehler car add");
		}
	}

	@Override
	public void remove(String id) {
		
		Car c = null;
		Iterator<Car> it = carlist.iterator();
		
		while(it.hasNext()) {
			Car tmp = it.next();
			if(tmp.getID().equals(id)) {
				c = tmp;
				break;
				
			}
			
		}

		carlist.remove(c);
		
		if(c != null) {
			currentBesucher--;
			switch(c.getArt()){
			case "Frau": currentFrauen--; break;
			case "any": currentAny--; break;
			case "Familie": currentFamilie--; break;
			case "Behinderte": currentBehinderte--; break;
			default: System.out.println("Fehler car remove");
			}
		} else {
			
			System.out.println("Auto nicht im Parkhaus vorhanden (Parkhaus.remove())");
		}
		
	}

	@Override
	public Car[] cars() {
		Car[] carray = new Car[carlist.size()];
		int pointer = 0;
		
		Iterator<Car> it = carlist.iterator();
		
		while(it.hasNext()) {
			carray[pointer++] = it.next();		
		}
		
		return carray;
	}
	
	@Override
	public int[] getGesamtBesucherArray() {
		int[] s = new int[4];
		s[0] = gesamtFrauen;
		s[1] = gesamtAny;
		s[2] = gesamtBehinderte;
		s[3] = gesamtFamilie;
		return s;
	}
	
	@Override
	public DoubleStream getEinnahmeStream() {
		double[] array = new double[einnahmen.size()];
		int pointer = 0;
		
		Iterator<Double> it = einnahmen.iterator();
		
		while(it.hasNext()) {
			array[pointer++] = it.next();		
		}
		
		return Arrays.stream(array);
		
	}
	
	@Override
	public void addEinnahme(double x, String art) {
		einnahmen.add(x);
		
		switch(art) {
		case "Frau": einnahmenFrauen += x; break;
		case "any" : einnahmenAny += x; break;
		case "Behinderte": einnahmenBehinderte += x; break;
		case "Familie": einnahmenFamilie += x; break;
		
		default: System.out.println("Fehler Parkhaus.addEinnahme() Art nicht gefunden");
		
		}
		
	}
	
	@Override
	public DoubleStream getParkdauerStream() {
		double[] array = new double[parkdauerList.size()];
		int pointer = 0;
		
		Iterator<Double> it = parkdauerList.iterator();
		
		while(it.hasNext()) {
			array[pointer++] = it.next();	
		}
		return Arrays.stream(array);
		
	}
	
	@Override
	public void addParkdauer(double f) {
		parkdauerList.add(f);
		
	}
	
	
	
	
	@Override
	public int size() {
		return carlist.size();
	}
	
	public int getCurrentBehinderte() {
		return currentBehinderte;
	}

	public int getCurrentFamilie() {
		return currentFamilie;
	}

	public int getCurrentAny() {
		return currentAny;
	}

	public int getCurrentFrauen() {
		return currentFrauen;
	}

	public int getId() {
		return id;
	}

	public int getGesamtBesucher() {
		return gesamtBesucher;
	}

	public int getCurrentBesucher() {
		return currentBesucher;
	}

	public int getGesamtFamilie() {
		return gesamtFamilie;
	}

	public int getGesamtFrauen() {
		return gesamtFrauen;
	}

	public int getGesamtAny() {
		return gesamtAny;
	}

	public int getGesamtBehinderte() {
		return gesamtBehinderte;
	}

	public double getEinnahmenBehinderte() {
		return einnahmenBehinderte;
	}

	public double getEinnahmenFamilie() {
		return einnahmenFamilie;
	}

	public double getEinnahmenFrauen() {
		return einnahmenFrauen;
	}

	public double getEinnahmenAny() {
		return einnahmenAny;
	}



	
}
