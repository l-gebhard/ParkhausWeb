package produktionscode;

public class Controller {
	
	ParkhausServlet servlet;
	Parkhaus p;
	String[] buttons;
	
	public Controller(ParkhausServlet servlet, Parkhaus p, String[] buttons) {
		this.servlet = servlet;
		this.p = p;
	}

}
