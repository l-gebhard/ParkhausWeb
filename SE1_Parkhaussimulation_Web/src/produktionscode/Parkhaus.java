package produktionscode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Parkhaus implements IF_Parkhaus{
	
	private String id;
	private List<Car> carlist;
	private Statistik s;
	private boolean[] parkplaetze;
	
	
	public Parkhaus(String id, int parkplaetze, List<Car>carlist, Statistik s ) {

		this.id = id;	
		this.carlist = carlist;
		this.s = s;
		this.parkplaetze = new boolean[parkplaetze];
	}
	
	@Override
	public int add(Car c) {
		
		carlist.add(c);
		
		for(int i = 0; i < parkplaetze.length; i++) {
			if(!parkplaetze[i]) {
				parkplaetze[i] = true;
				c.setParkplatz(i + 1);
				break;
			}
		}
		
		
		s.addBesucher(c.getArt());
		return c.getParkplatz();
	}

	@Override
	public Car remove(Car c) {
		
		carlist.remove(c);
		s.removeBesucher(c.getArt());
		setParkplatzBelegt(c.getParkplatz(), false);
		c.setParkplatz(-1);	
		return c;
		
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
	public int size() {
		return carlist.size();
	}
	
	public String getId() {
		return id;
	}
	
	public Statistik getStatistik() {
		return s;
	}
	
	
	//TODO auf event reagieren
	public void setMaxParkplaetze(int maxParkplaetze) {
		
		boolean[] tmp = new boolean[maxParkplaetze];
		for(int i = 0; i < parkplaetze.length; i++) {
			tmp[i] = parkplaetze[i];
		}
		parkplaetze = tmp;
	}

	public void setParkplatzBelegt(int parkplatz, boolean belegt) {
		
		parkplaetze[parkplatz - 1] = belegt;
		
	}
	
	public int getMaxParkplaetze() {
		return parkplaetze.length;
		
	}


	
}
