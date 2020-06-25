package produktionscode;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Parkhaus")
public class ParkhausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] requestParamString = request.getQueryString().split("=");
		String command = requestParamString[0];
		String param = requestParamString[1];

		Controller c = getPersistentController();

		System.out.println("Command = " + command);
		System.out.println("Param = " + param);

		if ("cmd".equals(command)) {
			PrintWriter out = response.getWriter();
			String ausgabe = c.doGet(command, param, response);
			out.println(ausgabe);
			System.out.println(ausgabe);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String body = getBody(request);
		System.out.println("body= " + body);
		String[] params = body.split(",");
		String event = params[0];
		Controller c = getPersistentController();
		
		c.doPost(event, params);

		setPersistentController(c);

	}

	private static String getBody(HttpServletRequest request) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		return stringBuilder.toString();
	}

	private ServletContext getApplication() {
		return getServletConfig().getServletContext();
	}

	private Controller getPersistentController() {
		ServletContext application = getApplication();
		Controller c = (Controller) application.getAttribute("Controller");
		if (c == null) {
			c = new Controller();
		}
		return c;
	}

	private void setPersistentController(Controller c) {
		ServletContext application = getApplication();
		application.setAttribute("Controller", c);

	}
}