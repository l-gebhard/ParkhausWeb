package produktionscode;

import java.util.List;

public class AvgReduce extends ReduceTemplate{

	public AvgReduce(List<Double> list) {
		super(list);
	}


	@Override
	protected double reduce(List<Double> list) {
		
		
		double nenner = list.size();
		double zaehler = 0;
		
		for(double i : list) {
			zaehler += i;
		}
		
		return zaehler / nenner;
	}
	

}
