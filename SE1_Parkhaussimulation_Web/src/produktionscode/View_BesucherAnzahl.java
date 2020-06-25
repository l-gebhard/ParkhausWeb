package produktionscode;

public class View_BesucherAnzahl extends View{
	Double view;
	
	public Double getView() {
		return view;
	}
	
	@Override
	public void update() {	
		view = model.getState().get(3);
	}
}
