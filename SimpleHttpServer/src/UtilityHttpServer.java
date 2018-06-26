import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Utility class with converters and requesters
 * Created in 26.06.2018 at 17:30:00
 * @author Maxim V. Mukhin
 * @version 0.0.1
 */
public class UtilityHttpServer {
	
	public static String executeHttpRequest(String address) throws IOException {
		URL url = new URL(address);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent",  ConfigHttpServer.HTTP_USER);
		connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setReadTimeout(10000);
		connection.setConnectTimeout(15000);
		connection.connect();
		return retrieveHttpData(connection);
	}

	private static String retrieveHttpData(HttpURLConnection connection) throws IOException {
		InputStreamReader input = null;
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			input = new InputStreamReader(connection.getInputStream(), "UTF-8");
		} else {
			input = new InputStreamReader(connection.getErrorStream(), "UTF-8");
		}
		BufferedReader reader = new BufferedReader(input);
		StringBuilder  result = new StringBuilder();
		String           line = new String();
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		reader.close();
		input.close(); 
		return result.toString();
	}

	public static String convertToJson(String xml) {
		StringBuilder json = new StringBuilder();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream inputStream = new    ByteArrayInputStream(xml.getBytes());
			Document document = builder.parse(inputStream);
			json.append("{").append(parseXmlData(document.getFirstChild())).append("}");
		} catch (Exception error) {
			System.out.println("ERROR: " + error.getMessage());
			json.setLength(0);
			json.append("{}");
		}
		return json.toString();
	}
	
	private static String parseXmlData(Node node) {
		StringBuilder json = new StringBuilder();
		json.append("\"").append(node.getNodeName()).append("\":{");
		if (node.hasChildNodes()) {
			for (int i = 0; i < node.getChildNodes().getLength(); i++) {
				json.append(parseXmlData(node.getChildNodes().item(i)));
			}
		}
		if (node.hasAttributes()) {
			for (int i = 0; i < node.getAttributes().getLength(); i++) {
				if (i > 0 || node.hasChildNodes()) json.append(",");
				json.append("\"").append(node.getAttributes().item(i).getNodeName()).append("\":\"").append(node.getAttributes().item(i).getNodeValue()).append("\"");
			}
		}
		json.append("}");
		return json.toString();
	}

}
