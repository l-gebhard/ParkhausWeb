package produktionscode;

public interface IF_Statistik {

	public void addBesucher(String id);

	public void removeBesucher(String id);

	public int[] getGesamtBesucherArray();

	public void addParkdauer(double dauer);

	public double[] getEinnahmenKategorieArray();

	public void addEinnahme(double einnahme, String art);

	
	public double getParkdauerAvg();

	public double getParkdauerMin();

	public double getParkdauerMax();
	
	public double getGesamtEinnahmen();

	public double getEinnahmenAvg();

	public double getEinnahmenMin();

	public double getEinnahmenMax();

	public double getCurrentFrauen();

	public double getCurrentAny();

	public double getCurrentBehinderte();

	public double getCurrentFamilie();

	public double getCurrentBesucher();
	
	public void undo();
}
