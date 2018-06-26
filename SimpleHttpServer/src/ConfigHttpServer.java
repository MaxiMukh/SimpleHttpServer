/**
 * Class with persistent parameters of CustomHttpServer configuration
 * Created in 26.06.2018 at 17:30:00
 * @author Maxim V. Mukhin
 * @version 0.0.1
 */
public final class ConfigHttpServer {
	public static final String HTTP_HOST = "localhost";
	public static final int    HTTP_PORT = 8080;
	public static final String HTTP_USER = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36";
	
	public static final String OMDB_KEYS = "235c77a6";
	public static final String OMDB_PATH = "/omdb";
	public static final String OMDB_HOST = "http://www.omdbapi.com/?apikey="+OMDB_KEYS+"&v=1&r=xml&t=";
	
	public static final String IMDB_KEYS = "";
	public static final String IMDB_PATH = "/imdb";
	public static final String IMDB_HOST = "http://www.imdb.com/xml/find?xml=1&nr=1&tt=on&q=";
}
