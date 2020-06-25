package produktionscode;

import java.util.Iterator;
import java.util.List;

public class Parkhaus implements IF_Parkhaus {
	
	private String id;
	private List<Car> carlist;
	private Statistik s;
	
	
	public Parkhaus(String id, List<Car>carlist, Statistik s) {

		this.id = id;	
		this.carlist = carlist;
		this.s = s;
	}
	
	@Override
	public void add(Car c) {

		carlist.add(c);
		s.addBesucher(c.getArt());
	}

	@Override
	public Car remove(String id) {

		
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
			s.removeBesucher(c.getArt());
		} else {
			
			System.out.println("Auto nicht im Parkhaus vorhanden (Parkhaus.remove())");
		}
		
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
	
	public void undo() {
		if(cars().length > 0) {
		Car letztesAuto = this.cars()[this.cars().length-1];
		this.remove(letztesAuto.getID());
		s.undo(letztesAuto.art);
		}
	}


	
}
