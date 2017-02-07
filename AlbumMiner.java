import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Mines an Imgur album.
 * @author Jayden Weaver
 *
 */
public class AlbumMiner {

	String albumURL;
	String username;
	int savedImageCount = 0;

	public AlbumMiner(String albumURL){
		this.albumURL = albumURL;
	}

	/**
	 * Returns the username.
	 * @return
	 */
	public String getUsername(){
		return this.username;
	}

	/**
	 * Begins album mining.
	 */
	public void startMining(){
		ArrayList<String> imageList = new ArrayList<String>();
		int imageCount = 0;
		try{
			//this.albumURL = "http://imgur.com/gallery/QzHbV"; //for testing
			URL url = new URL(this.albumURL);
			InputStream inStream = url.openStream();
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(inStream));
			String imageURL = "";
			String currentLine;


			while((currentLine = bufRead.readLine()) != null){
				imageURL = "";
				if (currentLine.contains("class=\"zoom\">")){ //get the image url
					imageURL += currentLine.substring(35, currentLine.length() - 15);
					imageList.add(imageURL);
					imageCount++;
				}
				else if (currentLine.contains("post-account")){ //set the username
					this.username = currentLine.substring(75, currentLine.lastIndexOf("\" title"));
				}
			}
		}
		catch(IOException io){
			System.err.println("Failed to fetch the album.");
		}

		//print number of images fetched.
		if (imageCount > 1 || imageCount < 1){
			System.out.println("Fetched " + imageCount + " images from " + this.username + ".");
		}
		else{
			System.out.println("Fetched " + imageCount + " image from" + this.username + ".");
		}

		saveImages(imageList); //start saving the images.

	}

	/**
	 * Saves the photos from an arraylist.
	 * @param imageList
	 */
	private void saveImages(ArrayList<String> imageList){
		File outputFile, directory;
		for (int i = 0; i < imageList.size(); i++){
			try {
				URL imageURL = new URL("http://www." + imageList.get(i));
				BufferedImage photo = ImageIO.read(imageURL);
				directory = new File("Imgur_Users\\");
				directory.mkdir();
				directory = new File("Imgur_Users\\" + this.username + "\\");
				directory.mkdir();
				if (imageURL.toString().substring(imageURL.toString().length() - 3, imageURL.toString().length()).equals("png")){
					outputFile = new File("Imgur_Users\\" + this.username + "\\" + i + ".png");					
					ImageIO.write(photo,"png", outputFile);
					this.savedImageCount++;
				}
				else if (imageURL.toString().substring(imageURL.toString().length() - 3, imageURL.toString().length()).equals("gif")){
					outputFile = new File("Imgur_Users\\" + this.username + "\\" + i + ".gif");					
					ImageIO.write(photo,"gif", outputFile);
					this.savedImageCount++;
				}
				else if (imageURL.toString().substring(imageURL.toString().length() - 3, imageURL.toString().length()).equals("jpg")){
					outputFile = new File("Imgur_Users\\" + this.username + "\\" + i + ".jpg");					
					ImageIO.write(photo,"jpg", outputFile);
					this.savedImageCount++;
				}
				System.out.println("SAVED IMAGE: " + imageURL.toString());

			} catch (IOException e) {
				System.err.println("Failed to save image: " + imageList.get(i));
			}

		}
		if (this.savedImageCount > 1 || this.savedImageCount < 1){
			System.out.println("Saved a total of " + this.savedImageCount + " images from " + this.username + ".");
		}
		else{
			System.out.println("Saved a total of  " + this.savedImageCount + " image from " + this.username + ".");
		}
	}

}
