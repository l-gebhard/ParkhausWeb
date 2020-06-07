import java.util.ArrayList;

public interface CarIF {
	ArrayList<String> cars();
	void add(String c);
	void remove(String c);
	int size();
}
