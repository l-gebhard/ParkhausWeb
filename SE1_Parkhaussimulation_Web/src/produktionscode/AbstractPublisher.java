package produktionscode;

import java.util.ArrayList;
import java.util.List;

public class AbstractPublisher {
	
	private List<View> views = new ArrayList<View>();
	
	public void addView(View view) {
		this.views.add(view);
	}
	
	public void update() {
		for(View view: views) {
			view.update();
		}
		
	}
}
