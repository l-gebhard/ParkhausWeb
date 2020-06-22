package produktionscode;

import java.util.List;

public class SumReduce extends ReduceTemplate{
	
	

	public SumReduce(List<Double> list) {
		super(list);
	}


	@Override
	protected double reduce(List<Double> list) {
		
		double sum = 0;
		
		for(double i : list) {
			sum += i;
		}
		return sum;
	}


}
