package produktionscode;

public abstract class View {

		Parkhaus model;
		
		public void subscribe(Parkhaus model) {
			this.model = model;
			model.addView(this);
			update();
			
		}
		
		public abstract void update();
}
