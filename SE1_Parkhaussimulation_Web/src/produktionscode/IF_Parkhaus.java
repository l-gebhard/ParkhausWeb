package produktionscode;

public interface IF_Parkhaus {

	Car[] cars();

	void add(Car c);

	Car remove(String ID);

	int size();

	Statistik getStatistik();
	
	public void undo();
}
