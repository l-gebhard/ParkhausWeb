package produktionscode;

import java.util.List;

public abstract class ReduceTemplate{
	
	List<Double> list;
	
	public ReduceTemplate(List<Double> list) {
		this.list = list;
	}
	
	public double reduzieren() {
		return reduce(list);
	}
	
	protected abstract double reduce(List<Double> list);
}
