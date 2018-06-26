import java.io.*;
import java.net.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Class for handling response to OmdbApi path of REST request
 * Created in 26.06.2018 at 17:30:00
 * @author Maxim V. Mukhin
 * @version 0.0.1
 */

public class OmdbApiHandler implements HttpHandler {
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String response = null;
		System.out.println("INFO: " + exchange.getRemoteAddress().toString() + " send request to URL " + exchange.getRequestURI().toString());
		// Query is String variable for search data in remote API
		String query = null;
		if (exchange.getRequestURI().getQuery() == null || exchange.getRequestURI().getQuery().isEmpty()) {
			// Get search string if request looks like http://127.0.0.1/omdb/The%20Game%20of%20Thrones
			query = exchange.getRequestURI().getPath().split("/")[2];
		} else {
			// Get search string if request looks like http://127.0.0.1/omdb/?q=The%20Game%20of%20Thrones
			query = exchange.getRequestURI().getQuery().split("=")[1];
		}
		exchange.getRequestBody().close();
		try {
			// Encoding concatenated address to correct URL
			String address = ConfigHttpServer.OMDB_HOST.concat(URLEncoder.encode(query, "UTF-8"));
			System.out.println("INFO: Send request to OMDB API by URL: " + address);
			// Execute HTTP request to external API by structured address
			String answer = UtilityHttpServer.executeHttpRequest(address);
			System.out.println("INFO: Retrieved data from OMDB API in XML: " + answer);
			// The main part of this test case is the converting XML response to JSON response (!!!)
			response = UtilityHttpServer.convertToJson(answer);
			System.out.println("INFO: Response converted from XML to JSON: " + response);
			// Preparing HTTP headers for correct response
			exchange.getResponseHeaders().add("Content-Type", "application/json");
			exchange.sendResponseHeaders(200, response.getBytes().length);
		} catch (Exception error) {
			System.out.println("ERROR: " + error.getMessage());
			response = "Internal Server Error";
			exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");
			exchange.sendResponseHeaders(500, response.getBytes().length);
		} finally {
			if (exchange != null) {
				// Get OutputStream from ResponseBody for write the content 
				OutputStream output = exchange.getResponseBody();
				// Writing data into HTTP body for response
				output.write(response != null ? response.getBytes() : new byte[0]);
				// It is important !:)
				output.close();
			}
		}
	}
	
}
