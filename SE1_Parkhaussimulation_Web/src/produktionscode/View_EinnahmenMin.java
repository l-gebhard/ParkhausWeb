package produktionscode;

public class View_EinnahmenMin extends View{
	Double view;
	
	public Double getView() {
		return view;
	}
	
	@Override
	public void update() {	
		view = model.getState().get(4);
	}
}
