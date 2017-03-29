import java.util.ArrayList;
import java.util.Random;

/**
 * Generates Random Imgur Links
 * @author Jayden Weaver
 *
 */
public class ErraticMiner {
	String imgurURL = "i.imgur.com/";

	public ErraticMiner(){

	}

	/**
	 * Mines for URLs, and returns arrayList of URLs.
	 * @param numSwings
	 * @return
	 */
	public ArrayList<String> startMining(int numSwings){
		ArrayList<String> linkArray = new ArrayList<String>();
		for (int i = 0; i < numSwings; i++){
			ImgurLink link = new ImgurLink(this.imgurURL + generateContentID());
			if (link.isValid()){
				linkArray.add(link.getURL() + ".png");
			}
		}
		return linkArray;
	}	

	/**
	 * Generates and returns a random Content ID
	 * @return
	 */
	private static String generateContentID(){
		String possibleCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String contentID = "";
		Random rand = new Random();

		//Most content IDs are either 5 or 7 characters. Some six, but not many. 
		if (rand.nextInt(2) == 0){ //if random number is zero, we'll use a 5 character ID
			for (int i = 0; i < 5; i++){
				contentID += possibleCharacters.charAt(rand.nextInt(possibleCharacters.length()));
			}
		}
		else{ //else we'll use a 7 character ID.
			for (int i = 0; i < 7; i++){
				contentID += possibleCharacters.charAt(rand.nextInt(possibleCharacters.length()));
			}	
		}		
		return contentID;
	}
}
