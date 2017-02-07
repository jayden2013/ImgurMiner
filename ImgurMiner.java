import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * An Imgur Miner
 * @author Jayden Weaver
 *
 */
public class ImgurMiner {

	public static void main(String[] args){
		final String urlString = "http://imgur.com/new/time"; //URL to use.
		try {
			URL url = new URL(urlString);
			InputStream inStream = url.openStream();
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(inStream));
			ArrayList<String> urlStringList = new ArrayList<String>();
			String currentURL = "";
			int albumCount = 0;
			String albumURL;
			while((currentURL = bufRead.readLine()) != null){
				albumURL = "http://www.imgur.com";
				if (currentURL.contains("<a class=\"image-list-link\" href=\"/gallery/")){
					albumURL += currentURL.substring(37, currentURL.length() - 16);
					System.out.println(albumURL);
					urlStringList.add(albumURL);
					albumCount++;
				}					
			}	

			System.out.println("Captured " + albumCount + " albums.");			

			//Begin downloading the albums.
			for (int i = 0; i < albumCount; i++){
				AlbumMiner albumMiner = new AlbumMiner(urlStringList.get(i));
				albumMiner.startMining();
			}

		} catch (IOException e) {
			System.err.println("Failed to create URL");
		}

	}

}
