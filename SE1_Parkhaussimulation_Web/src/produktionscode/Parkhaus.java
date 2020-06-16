package produktionscode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

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
	
	private ArrayList<Double> parkdauerList = new ArrayList<>();
	private ArrayList<Double> einnahmen = new ArrayList<>();
	private ArrayList<Car> carlist = new ArrayList<>();
	
	
	public Parkhaus(int id) {
		this.id = id;	
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
		for(int i = 0; i < carlist.size(); i++) {
			if(carlist.get(i).getID().equals(id)) {
				 c = carlist.get(i);
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
		
		for(int i = 0; i < carlist.size(); i++) {
			carray[i] = carlist.get(i);
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
		
		for(int i = 0; i < einnahmen.size(); i++) {
			array[i] = einnahmen.get(i);
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
		
		for(int i = 0; i < parkdauerList.size(); i++) {
			array[i] = parkdauerList.get(i);
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
