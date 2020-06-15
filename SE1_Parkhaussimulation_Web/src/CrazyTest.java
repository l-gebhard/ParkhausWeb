import org.json.JSONObject;

public class CrazyTest {

	public static void main(String[] args) {
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.put("age", "22");
		jo.put("city", "chicago");
		
		System.out.println(jo);
	}

}
