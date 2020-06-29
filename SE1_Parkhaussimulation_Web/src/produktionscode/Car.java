package produktionscode;

public class Car implements IF_Car{
	
	private String id;
	private String art;
	private int parkplatz;
	
	public Car(String id, String art) {
		this.id = id;
		this.art = art;
		
	}


	@Override
	public String getArt() {
		return art;
	}


	@Override
	public String getID() {
		return id;
	}
	
	public void setParkplatz(int parkplatz) {
		this.parkplatz = parkplatz;
	}
	
	public int getParkplatz() {
		return parkplatz;
	}
	
}
