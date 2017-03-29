import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Object to store information about imgur links
 * @author Jayden Weaver
 *
 */
public class ImgurLink {

	String URL = "";
	boolean isValid = false;
	URL link;
	InputStream inStream;
	BufferedReader bufRead;

	public ImgurLink(String url) { 
		this.URL = url;
		checkValidity();
	}

	private void checkValidity(){
		try {
			this.link = new URL("http://www." + this.URL);
			this.inStream = link.openStream();
			this.bufRead = new BufferedReader(new InputStreamReader(this.inStream));
			this.isValid = true;
		} catch (IOException e) {
			this.isValid = false;
		}
	}

	/**
	 * Sets the URL to any string.
	 * @param url
	 */
	public void setURL(String url){
		this.URL = url;
	}

	/**
	 * Returns the associated URL
	 * @return
	 */
	public String getURL(){
		return this.URL;
	}

	/**
	 * Returns true if it contains a valid URL
	 * @return
	 */
	public boolean isValid(){	
		return this.isValid;
	}
}
