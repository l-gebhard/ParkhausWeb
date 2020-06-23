package produktionscode;
import java.util.stream.DoubleStream;

public interface ParkhausIF {
		
		Car[] cars();
		void add(Car c);
		Car remove(String ID);
		int size();
		int[] getGesamtBesucherArray();
		void addEinnahme(double f, String art);
		
		public double getGesamtEinnahmen();
		public double getEinnahmenAvg();
		public double getEinnahmenMax();
		public double getEinnahmenMin();
		
		public double getParkdauerAvg();
		public double getParkdauerMax();
		public double getParkdauerMin();
		
		
	    void addParkdauer(double f);

}
