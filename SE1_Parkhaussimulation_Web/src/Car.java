public class Car implements CarIF{
	
	String id;
	String art;
	
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
	
}
