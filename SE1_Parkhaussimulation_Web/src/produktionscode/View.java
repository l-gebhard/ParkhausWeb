package produktionscode;

public abstract class View {

		Statistik model;
		
		public void subscribe(Statistik model) {
			this.model = model;
			model.addView(this);
			update();
			
		}
		
		public abstract void update();
}
