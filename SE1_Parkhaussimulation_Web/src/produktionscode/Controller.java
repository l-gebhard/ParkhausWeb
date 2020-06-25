package produktionscode;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

public class Controller {

	private IF_Parkhaus p = new Parkhaus("0", new ArrayList<Car>(),
			new Statistik(new ArrayList<Double>(), new ArrayList<Double>()));

	public String doGet(String cmd, String param, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html");
		DecimalFormat formatToEuro = new DecimalFormat("#0.00");
		DecimalFormat formatToSeconds = new DecimalFormat("#0.000");
		
		Statistik s = p.getStatistik();
		
		switch (param) {
		
		case ("UNDO"):{
			UndoCommand undo = new UndoCommand(p);
			undo.exec();
			return ("UNDO Erfolgreich");
		}
		
		case ("Gesamteinnahmen"): {
			return ("Gesamteinnahmen: " + formatToEuro.format(s.getGesamtEinnahmen()) + " Euro");
		}

		case ("avg"): {
			return ("Durchschnittspreis: " + formatToEuro.format(s.getEinnahmenAvg()) + " Euro | " + "Durchschnittsdauer: " + formatToSeconds.format(s.getParkdauerAvg()) + " Sekunden");
		}

		case ("Besucheranzahl"): {
			return (s.getGesamtBesucher() + " Besucher");

		}
		case ("min"): {
			return ("Min Parkgebuehr: " + formatToEuro.format(s.getEinnahmenMin()) + " Euro bei " + formatToSeconds.format(s.getParkdauerMin()) + " Sekunden Parkdauer");
		}

		case ("max"): {
			return ("max Parkgebuehr: " + formatToEuro.format(s.getEinnahmenMax()) + " Euro bei " + formatToSeconds.format(s.getParkdauerMax()) + " Sekunden Parkdauer");

		}

		case ("Einnahmen_pro_Kategorie"): {
			
			double[] einnahmenKategorie = s.getEinnahmenKategorieArray();
			JsonObject root = Json.createObjectBuilder()
					.add("data", Json.createArrayBuilder()
							.add(Json.createObjectBuilder()
									.add("x", Json.createArrayBuilder()
											.add("Frauen")
											.add("Any")
											.add("Behinderte")
											.add("Familien"))
									.add("y",Json.createArrayBuilder()
											.add(einnahmenKategorie[0])
											.add(einnahmenKategorie[1])
											.add(einnahmenKategorie[2])
											.add(einnahmenKategorie[3]))
									.add("type", "bar")
									.add("name", "Einnahmen pro Kategorie")))
					.build();
			
			return (root.toString());
		}

		case ("Anteil_Besucher"): {
			int[] besuchergesamt = s.getGesamtBesucherArray();

			JsonObject root = Json.createObjectBuilder()
					.add("data", Json.createArrayBuilder()
							.add(Json.createObjectBuilder()
									.add("values",Json.createArrayBuilder()
											.add(besuchergesamt[0])
											.add(besuchergesamt[1])
											.add(besuchergesamt[2])
											.add(besuchergesamt[3]))
									.add("labels", Json.createArrayBuilder()
											.add("Frauen")
											.add("Any")
											.add("Behinderte")
											.add("Familien"))
									.add("type", "pie")))
					.build();

			return root.toString();
		}

		default:
			System.out.println("Fehler Controller createResonse " + param);
			return null;

		}

	}

	public void doPost(String event, String[] params) {

		switch (event) {

		case ("occupied"):{
			p.undo();
			break;
		}
		
		case ("enter"): {
			p.add(new Car(params[1], params[8]));
			break;
		}

		case ("leave"): {
			String priceString = params[4];
			float dauer = Float.parseFloat(params[3]);
			Car c = p.remove(params[1]);
			
			
			if (c != null && !"_".equals(priceString)) {
				float price = Float.parseFloat(priceString);
				p.getStatistik().addEinnahme(price, params[8]);
				p.getStatistik().addParkdauer(dauer);
			}
			break;
		}

		default:
			System.out.println("Event im Post nicht gefunden " + event);

		}
	}
}
