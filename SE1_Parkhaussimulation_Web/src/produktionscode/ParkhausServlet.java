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
       
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("request = " + request.toString());
		System.out.println("response = " + response.toString());
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
				
			
			case("Gesamteinnahmen"):{
				out.println("Gesamteinnahmen: " + f.format(p.getGesamtEinnahmen()) + " Euro");
				System.out.println( "Gesamteinnahmen = " + f.format(p.getGesamtEinnahmen()) + " Euro");
				break;
			}
			
			
			case("avg"):{
				out.println("Durchschnittspreis: " + f.format(p.getEinnahmenAvg()) + " Euro | " + "Durchschnittsdauer: " + f2.format(p.getParkdauerAvg()) + " Sekunden");
				System.out.println( "Durchschnittspreis: " + f.format(p.getEinnahmenAvg()) + " Euro | " + "Durchschnittsdauer: " + f2.format(p.getParkdauerAvg()) + " Sekunden");
				break;
			}
			
			case("Besucheranzahl"):{
				out.println(p.getGesamtBesucher() + " Besucher");
				System.out.println(p.getGesamtBesucher() + " Besucher");
				break;
				
			}
			case("min"):{
				out.println("Min Parkgebuehr: " + f.format(p.getEinnahmenMin()) + " Euro bei " + f2.format(p.getParkdauerMin()) +  " Sekunden Parkdauer");
				System.out.println("Min Parkgebuehr: " + f.format(p.getEinnahmenMin()) + " Euro bei " + f2.format(p.getParkdauerMin()) +  " Sekunden Parkdauer");
				break;
				
			}
			
			case("max"):{
				out.println("max Parkgebuehr: " + f.format(p.getEinnahmenMax()) + " Euro bei " + f2.format(p.getParkdauerMax()) +  " Sekunden Parkdauer");
				System.out.println("max Parkgebuehr: " + f.format(p.getEinnahmenMax()) + " Euro bei " + f2.format(p.getParkdauerMax()) +  " Sekunden Parkdauer");
				break;
				
			}
			
			case("Einnahmen_pro_Kategorie"):{
				
				 JsonObject root = Json.createObjectBuilder()
						 .add("data", Json.createArrayBuilder()
								 .add(Json.createObjectBuilder()
										 .add("x", Json.createArrayBuilder()
												 .add("Frauen")
												 .add("Any")
												 .add("Behinderte")
												 .add("Familien")
												 )
										 .add("y", Json.createArrayBuilder()
												 .add(p.getEinnahmenFrauen() / 100)
												 .add(p.getEinnahmenAny() / 100)
												 .add(p.getEinnahmenBehinderte() / 100)
												 .add(p.getEinnahmenFamilie() / 100)
												 )
										 .add("type", "bar")
										 .add("name", "Einnahmen pro Kategorie")
										 )
						).build();
				
				out.println(root);
				System.out.println(root);
				break;
			}
			
			case("Anteil_Besucher"):{
				response.setContentType("text/plain");
				out = response.getWriter();
				 int[] besuchergesamt = p.getGesamtBesucherArray();
				 
				 JsonObject root = Json.createObjectBuilder()
						 .add("data", Json.createArrayBuilder()
								 .add(Json.createObjectBuilder()
										 .add("values", Json.createArrayBuilder()
												 .add(besuchergesamt[0])
												 .add(besuchergesamt[1])
												 .add(besuchergesamt[2])
												 .add(besuchergesamt[3])
												 )
										 .add("labels", Json.createArrayBuilder()
												 .add("Frauen")
												 .add("Any")
												 .add("Behinderte")
												 .add("Familien")
												 )
										 .add("type", "pie")
								)
						).build();
				 
				 out.println(root);			 
				 System.out.println(root);
				 
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
				p.addEinnahme(price, params[8]);
				p.addParkdauer(dauer);
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
			Double[] states = new Double[8];
			p = new Parkhaus(0, new ArrayList<Double>(), new ArrayList<Double>(), new ArrayList<Car>(), states );
		}
		return p;
		}
	
	private void setPersistentParkhaus(Parkhaus p){
		ServletContext application = getApplication();
		application.setAttribute("Parkhaus", p);

		}
}