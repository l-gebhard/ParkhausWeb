import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.json.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Parkhaus")
public class ParkhausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		String[] requestParamString = request.getQueryString().split("=");
		String command = requestParamString[0];
		String param = requestParamString[1];
		DecimalFormat f = new DecimalFormat("#0.00");
		DecimalFormat f2 = new DecimalFormat("#0.000");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Parkhaus p = getPersistentParkhaus();
		
		System.out.println( "Command = " + command );
		System.out.println( "Param = " + param );

		if("cmd".equals(command)) {
			
			switch(param) {
				
			case("sum"):{
				out.println( f.format(p.getSumme() / 100) + " Euro");
				System.out.println( "Sum = " + f.format(p.getSumme() / 100) + " Euro");
				break;
			}
			
			case("avg"):{
				Float avg = p.getSumme() / (p.getGesamtBesucher()-p.getCurrentBesucher());
				out.println( f.format(avg / 100) + " Euro " + "Durchschnittsdauer: " + f2.format((p.getGesamtDauer() / (p.getGesamtBesucher() - p.getCurrentBesucher() )/1000))+ " Sekunden");
				System.out.println( "avg = " + f.format(avg / 100) + " Euro" + "Durchschnittsdauer : " + f2.format((p.getGesamtDauer() / (p.getGesamtBesucher() - p.getCurrentBesucher()) /1000)) + " Sekunden");
				break;
			}
			
			case("Besucheranzahl"):{
				out.println(p.getGesamtBesucher() + " Besucher");
				System.out.println(p.getGesamtBesucher() + " Besucher");
				break;
				
			}
			
			case("chart"):{
				response.setContentType("text/plain");
				out = response.getWriter();
				
				Car c = (Car) getApplication().getAttribute("Carlist");
				
				 String json = "{" + " \"data\": [" + " {" + " \"x\": [" + "\"" + 0 + "\"";
				 json += "," + "\"" + 1 + "\"";
				 json += "],\"y\":[" + "\"" + 9 + "\"";
				 json += "],\"type\":\"bar\"}]}";
				 System.out.println();
				 out.println(json);
				System.out.println(json);
				
				
				//TODO
				break;
				
			}
			
			case("familyChart"):{
				response.setContentType("text/plain");
				out = response.getWriter();
				
				//TODO
				break;
			}
			
			default: System.out.println("Fehler parameter kann nicht verarbeitet werden GetMethode");
			
			}
			
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = getBody( request );
		System.out.println( body );
		String[] params = body.split(",");
		String event = params[0];
		Parkhaus p = getPersistentParkhaus();
		
		switch(event){
			
		case("enter"):{
			p.add(new Car(params[1], params[8]));
			break;
		}
			
		case("leave"):{
			String priceString = params[4];
			float dauer = Float.parseFloat(params[3]);
			p.remove(params[1]);
			
			if(! "_".equals(priceString)) {
				float price = Float.parseFloat(priceString);
				p.setSumme(p.getSumme() + price);
				p.setGesamtDauer(p.getGesamtDauer() + dauer);
				
			}
			break;
		}
		
		default: System.out.println("Event im Post nicht gefunden");
		
		
		}
		
		setPersistentParkhaus(p);
		
		
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
	
	private ServletContext getApplication(){
		return getServletConfig().getServletContext();
		}
	
	private Parkhaus getPersistentParkhaus(){
		ServletContext application = getApplication();
		Parkhaus p = (Parkhaus)application.getAttribute("Parkhaus");
		if(p == null) {
			p = new Parkhaus(0);
		}
		return p;
		}
	
	private void setPersistentParkhaus(Parkhaus p){
		ServletContext application = getApplication();
		application.setAttribute("Parkhaus", p);

		}
}