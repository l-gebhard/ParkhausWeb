package produktionscode;

public class BesucherAnzahlView extends View{
	Double view;
	
	public Double getView() {
		return view;
	}
	
	@Override
	public void update() {	
		view = model.getState().get(3);
	}
}
