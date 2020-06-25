package produktionscode;

import javax.servlet.http.HttpServletResponse;

public interface IF_Controller {
	
	public String doGet(String cmd, String param, HttpServletResponse response);
	
	public void doPost(String event, String[] params);
}
