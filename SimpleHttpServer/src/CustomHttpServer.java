import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

/**
 * Main class for Simple and Lightweight HTTP Server creation
 * Created in 26.06.2018 at 17:30:00
 * @author Maxim V. Mukhin
 * @version 0.0.1
 */

public class CustomHttpServer {

	public static void main(String[] args) {
		try {
			InetSocketAddress socket = null;
			// In the beginning was the word and the word it was a socket
			if (ConfigHttpServer.HTTP_HOST == null || ConfigHttpServer.HTTP_HOST.isEmpty()) {
				socket = new InetSocketAddress(ConfigHttpServer.HTTP_PORT);
			} else {
				socket = new InetSocketAddress(ConfigHttpServer.HTTP_HOST, ConfigHttpServer.HTTP_PORT);
			}
			// Create lightweight HTTP Server implemented many-many-many year ago in the Kingdom of Sun
			HttpServer server = HttpServer.create(socket, 10);
			System.out.println("INFO: HTTP server created on HOST: " + ConfigHttpServer.HTTP_HOST);
			System.out.println("INFO: HTTP server listens on PORT: " + ConfigHttpServer.HTTP_PORT);
			// Now we will put the traps on the forest tracks
			server.createContext(ConfigHttpServer.OMDB_PATH, new OmdbApiHandler());
			System.out.println("INFO: Created Handler for ROUTE: " + ConfigHttpServer.OMDB_PATH);
			server.createContext(ConfigHttpServer.IMDB_PATH, new ImdbApiHandler());
			System.out.println("INFO: Created Handler for ROUTE: " + ConfigHttpServer.IMDB_PATH);
			// Assign the first who meeting in the road to execute a dirty job
			server.setExecutor(null);
			System.out.println("INFO: Assigned Default Executor for SERVER");
			// Believe or not believe, but from this places beginning of the dark magic
			server.start();
			System.out.println("INFO: HTTP server successfully started ... ");
		} catch (IOException error) {
			// Begin at the beginning, "the King said, very gravely", and go on till you come to the end, then stop.
			System.out.println("ERROR: " + error.getMessage());
		}
	}

}
