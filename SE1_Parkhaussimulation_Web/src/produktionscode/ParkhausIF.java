package produktionscode;
import java.util.stream.DoubleStream;

public interface ParkhausIF {
		
		Car[] cars();
		void add(Car c);
		Car remove(String ID);
		int size();
		int[] getGesamtBesucherArray();
		void addEinnahme(double f, String art);
		DoubleStream getEinnahmeStream();
	    DoubleStream getParkdauerStream();
	    void addParkdauer(double f);

}
