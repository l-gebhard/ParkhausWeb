import java.util.stream.DoubleStream;

public interface ParkhausIF {
		
		Car[] cars();
		void add(Car c);
		void remove(String ID);
		int size();
		int[] getGesamtBesucherArray();
		DoubleStream getEinnahmeStream();
		void addEinnahme(double f);
		
	    DoubleStream getParkdauerStream();
	    void addParkdauer(double f);

}
