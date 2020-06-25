package produktionscode;

public class View_EinnahmenMax extends View{
	Double view;
	
	public Double getView() {
		return view;
	}
	
	@Override
	public void update() {	
		view = model.getState().get(6);
	}
}
