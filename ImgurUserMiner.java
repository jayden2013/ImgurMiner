
/**
 * Driver class for UserMiner.
 * @author Jayden Weaver
 *
 */
public class ImgurUserMiner {

	public static void main(String[] args){
		if (args.length < 1 || args.length < 1){
			System.err.println(usage());
		}
		UserMiner userMine = new UserMiner(args[0]);
		userMine.startMining();		
	}

	/**
	 * Returns the usage statement.
	 * @return
	 */
	private static String usage(){
		return "java ImgurUserMiner <username>";
	}
}
