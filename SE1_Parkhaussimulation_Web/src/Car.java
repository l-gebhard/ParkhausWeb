import java.util.ArrayList;

public class Car implements CarIF{
	
	ArrayList<String> c = new ArrayList<>();
	

	
	@Override
	public ArrayList<String> cars() {
		return c;
		
	}

	@Override
	public void add(String c) {
		this.c.add(c);
		
	}

	@Override
	public void remove(String c) {
		this.c.remove(c);
		
	}

	@Override
	public int size() {
		return c.size();
	}

	
	
}
