package produktionscode;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

public class Controller {
	// Author: Teamarbeit

	private IF_Parkhaus p = new Parkhaus("0", 9, new ArrayList<Car>(),
			new Statistik(new ArrayList<Double>(), new ArrayList<Double>()));

	// Author: Teamarbeit
	public String doGet(String cmd, String param, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		DecimalFormat formatToEuro = new DecimalFormat("#0.00");
		DecimalFormat formatToSeconds = new DecimalFormat("#0.000");

		Statistik s = p.getStatistik();

		switch (param) {

		case ("Gesamteinnahmen"): {
			return Gesamteinnahmen();
		}

		case ("avg"): {
			return avg;
		}

		case ("Besucheranzahl"): {
			return Besucheranzahl();

		}
		case ("min"): {
			return min();
		}

		case ("max"): {
			return max();
		}

		case ("Einnahmen_pro_Kategorie"): {
			return Einnahmen_pro_Kategorie();
		}

		case ("Anteil_Besucher"): {
			return Anteil_Besucher();
		}

		default:
			System.out.println("Fehler Controller createResonse " + param);
			return null;

		}

	}

	// Author: Teamarbeit
	public String doPost(String event, String[] params) {

		switch (event) {
		
		case ("enter"): {
			return enter();
		}

		case ("leave"): {
			return leave();
		}
		default:System.out.println("Event im Post nicht gefunden "+event);

		}

		// Default return;
		return null;}
	}

	// Author: Teamarbeit
	private String Gesamteinnahmen() {
		return ("Gesamteinnahmen: " + formatToEuro.format(s.getGesamtEinnahmen()) + " Euro");
	}

	// Author: Teamarbeit
	private String avg() {
		return ("Durchschnittspreis: " + formatToEuro.format(s.getEinnahmenAvg()) + " Euro | " + "Durchschnittsdauer: "
				+ formatToSeconds.format(s.getParkdauerAvg()) + " Sekunden");
	}

	// Author: Teamarbeit
	private String Besucheranzahl() {
		return (s.getGesamtBesucher() + " Besucher");
	}

	// Author: Teamarbeit
	private String min() {
		return ("Min Parkgebuehr: " + formatToEuro.format(s.getEinnahmenMin()) + " Euro bei "
				+ formatToSeconds.format(s.getParkdauerMin()) + " Sekunden Parkdauer");
	}

	// Author: Teamarbeit
	private String max() {
		return ("max Parkgebuehr: " + formatToEuro.format(s.getEinnahmenMax()) + " Euro bei "
				+ formatToSeconds.format(s.getParkdauerMax()) + " Sekunden Parkdauer");
	}

	// Author: Teamarbeit
	private String Anteil_Besucher() {
		int[] besuchergesamt = s.getGesamtBesucherArray();

		JsonObject root = Json.createObjectBuilder().add("data", Json.createArrayBuilder().add(Json
				.createObjectBuilder()
				.add("values",
						Json.createArrayBuilder().add(besuchergesamt[0]).add(besuchergesamt[1]).add(besuchergesamt[2])
								.add(besuchergesamt[3]))
				.add("labels", Json.createArrayBuilder().add("Frauen").add("Any").add("Behinderte").add("Familien"))
				.add("type", "pie"))).build();

		return root.toString();
	}

	// Author: Teamarbeit
	private String Einnahmen_pro_Kategorie() {

		double[] einnahmenKategorie = s.getEinnahmenKategorieArray();
		JsonObject root = Json.createObjectBuilder()
				.add("data", Json.createArrayBuilder().add(Json.createObjectBuilder()
						.add("x", Json.createArrayBuilder().add("Frauen").add("Any").add("Behinderte").add("Familien"))
						.add("y",
								Json.createArrayBuilder().add(einnahmenKategorie[0]).add(einnahmenKategorie[1])
										.add(einnahmenKategorie[2]).add(einnahmenKategorie[3]))
						.add("type", "bar").add("name", "Einnahmen pro Kategorie")))
				.build();

		return (root.toString());
	}

	// Author: Teamarbeit
	private String enter() {
		return String.valueOf(p.add(new Car(params[1], params[8])));
	}

	// Author: Teamarbeit
	private String leave() {
		String priceString = params[4];
		float dauer = Float.parseFloat(params[3]);
		Car[] cars = p.cars();
		Car tmp = null;
		for (Car i : cars) {
			if (i.getID().equals(params[1])) {
				tmp = i;
			}
		}
		Car c = null;

		if (tmp != null) {
			c = p.remove(tmp);
		}

		if (c != null && !"_".equals(priceString)) {
			float price = Float.parseFloat(priceString);
			p.getStatistik().addEinnahme(price, params[8]);
			p.getStatistik().addParkdauer(dauer);
		}
		break;
	}

	

}