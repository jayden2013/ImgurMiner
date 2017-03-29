import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Mines an Imgur user's profile.
 * @author Jayden Weaver
 *
 */
public class UserMiner {
	String username;
	String url = "http://www.imgur.com/user/";

	public UserMiner(String un){
		this.username = un;
		this.url += username + "/submitted";		
	}

	/**
	 * Returns the username.
	 * @return
	 */
	public String getUsername(){
		return this.username;
	}

	/**
	 * Returns the URL.
	 * @return
	 */
	public String getURL(){
		return this.url;
	}

	/**
	 * Mines the user profile.
	 */
	public void startMining(){
		try{
			URL url = new URL(this.url);
			InputStream inStream = url.openStream();
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(inStream));
			String currentLine;
			String link = "http://imgur.com/";
			boolean isLink = false;
			int linkCount = 0;
			int modCount = 0;
			ArrayList<String> albumLinkArray = new ArrayList<String>();

			while((currentLine = bufRead.readLine()) != null){
				if (currentLine.contains("/gallery/")){ //get the image url
					char prev = ' ', curr = ' ';
					for (char a : currentLine.toCharArray()){
						prev = curr;
						curr = a;
						if (isLink){
							link += curr;
						}
						if (curr == '>' && isLink){
							albumLinkArray.add(link.substring(0, link.length() - 2));
							link = "http://imgur.com/";
							isLink = false;
							modCount++;
						}


						if (prev == '"' && curr == '/' && modCount % 2 == 0){
							isLink = true;
							linkCount++;
							modCount++;
						}

						if (!isLink && modCount % 2 != 0){
							System.out.println("threw away: " + curr);
						}
					}
				}
			}

			//Cleanse Array and prepare the final array.
			ArrayList<String> finalAlbumLinkArray = new ArrayList<String>();
			for (int i = 0; i < albumLinkArray.size(); i++){
				if (i % 2 == 0){
					finalAlbumLinkArray.add(albumLinkArray.get(i));
				}
			}

			for (int k = 0; k < finalAlbumLinkArray.size(); k++){
				AlbumMiner albumMine = new AlbumMiner(finalAlbumLinkArray.get(k));
				albumMine.startMining();
			}
		}
		catch(Exception e){
			System.err.println("error throwd");
		}
	}
}
