package produktionscode;

public interface IF_Parkhaus {

	Car[] cars();

	int add(Car car);

	Car remove(Car car);

	int size();

	Statistik getStatistik();
	
	public void setParkplatzBelegt(int parkplatz, boolean belegt);
	
	public void setMaxParkplaetze(int maxParkplaetze);
	
	public int getMaxParkplaetze();
}
