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
		System.out.println( "Command = " + command );
		System.out.println( "Param = " + param );

		
		if ( "cmd".equals( command ) && "sum".equals( param ) ){
		Float sum = getPersistentSum();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println( f.format(sum / 100) + " Euro");
		System.out.println( "sum = " + f.format(sum / 100) + " €");
		}
		if ( "cmd".equals( command ) && "avg".equals( param ) ){
			Float sum = getPersistentSum();
			Integer anzahl = getPersistentAnzahl();
			Float gesamtdauer = getPersistentGesamtDauer();
			Float avg = sum / anzahl;
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println( f.format(avg / 100) + " Euro " + "Durchschnittsdauer: " + f2.format((gesamtdauer / anzahl)/100) + " Sekunden");
			System.out.println( "anz = " + anzahl );
			System.out.println( "avg = " + f.format(anzahl / 100) + " € " + "Durchschnittsdauer : " + f2.format((gesamtdauer / anzahl)/100) + " Sekunden");
			
		}
		
		if ( "cmd".equals( command ) && "Besucheranzahl".equals( param ) ){
			Integer anzahl = getPersistentAnzahl();
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(anzahl + " Besucher");
			System.out.println( anzahl + " Besucher");
		}
		
		
		
		if ( "cmd".equals( command ) && "chart".equals( param )){
			Car c = (Car) getApplication().getAttribute("Carlist");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			JsonObject root = Json.createObjectBuilder()
				.add( "data" , Json.createArrayBuilder()
						.add( Json.createObjectBuilder()
								.add("x",c.size())
								.add("type", "bar")
								.add("name","Auslastung")
						)
						
						
					).build();
			out.println(root.toString());
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = getBody( request );
		System.out.println( body );
		String[] params = body.split(",");
		String event = params[0];
		if("enter".equals(event)) {
			if(getApplication().getAttribute("Carlist")== null) {
				Car c = new Car();
				c.add(params[1]);
				getApplication().setAttribute("Carlist", c);
			}
			else {
				Car c = (Car) getApplication().getAttribute("Carlist");
				c.add(params[1]);
				getApplication().setAttribute("Carlist",c);
			}
			
		}
		
		
		if("leave".equals(event)) {
			
			Float sum = getPersistentSum();
			Float gesamtdauer = getPersistentGesamtDauer();
			String priceString = params[4];
			float dauer = Float.parseFloat(params[3]);
			Integer anzahl = getPersistentAnzahl();
			
			Car c = (Car) getApplication().getAttribute("Carlist");
			c.remove(params[1]);
			getApplication().setAttribute("Carlist",c);
			
			if ( ! "_".equals( priceString ) ){
				// parse the number in string
				float price = Float.parseFloat( priceString );
				sum += price;
				gesamtdauer += dauer;
				
				// store sum persistently in ServletContext
				getApplication().setAttribute("sum", sum );
				getApplication().setAttribute("anzahl", ++anzahl );
				getApplication().setAttribute("gesamtdauer" ,gesamtdauer);
			}
			
			
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println( sum ); // return sum in HTTP response, if needed
		}
		
		
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
	
	private Float getPersistentSum(){
		Float sum;
		ServletContext application = getApplication();
		sum = (Float)application.getAttribute("sum");
		if ( sum == null ) {
			sum = 0.0f;
		}
		return sum;
		}
	
	private Integer getPersistentAnzahl(){
		Integer anzahl;
		ServletContext application = getApplication();
		anzahl = (Integer)application.getAttribute("anzahl");
		if ( anzahl == null ) {
			anzahl = 0;
		}
		return anzahl;
		}
	
	private Float getPersistentGesamtDauer(){
		Float gesamtDauer;
		ServletContext application = getApplication();
		gesamtDauer = (Float)application.getAttribute("sum");
		if ( gesamtDauer == null ) {
			gesamtDauer = 0.0f;
		}
		return gesamtDauer;
		
	}
	
	private Car getPersistentCarList() {
		ServletContext application = getApplication();
		return (Car) application.getAttribute("Carlist");
	}
	
	private void setPersistentCarList(Car c) {
		ServletContext application = getApplication();
		application.setAttribute("Carlist", c);
	}
}