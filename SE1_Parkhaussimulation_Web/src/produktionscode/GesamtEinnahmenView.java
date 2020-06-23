package produktionscode;

public class GesamtEinnahmenView extends View{
	
	Double view;
	
	public Double getView() {
		return view;
	}
	
	@Override
	public void update() {
		for(Double i : model.getState()) {
			System.out.println(i);
		}
		
		view = model.getState().get(0);
		//view = model.getState().get(0);
	}

}
