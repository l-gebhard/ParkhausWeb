import java.util.ArrayList;

public class Parkhaus implements ParkhausIF{
	
	private int id;
	private int currentFrauen;
	private int currentAny;
	private int currentFamilie;
	private int currentBehinderte;
	private int currentBesucher;
	private int gesamtBesucher;
	private float summe;
	private float gesamtDauer;
	
	
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
		case "Frau": currentFrauen++; break;
		case "any": currentAny++; break;
		case "Familie": currentFamilie++; break;
		case "Behinderte": currentBehinderte++; break;
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

	public float getSumme() {
		return summe;
	}

	public void setSumme(float summe) {
		this.summe = summe;
	}

	public float getGesamtDauer() {
		return gesamtDauer;
	}

	public void setGesamtDauer(float gesamtDauer) {
		this.gesamtDauer = gesamtDauer;
	}

	public int getCurrentBesucher() {
		return currentBesucher;
	}


	
}
