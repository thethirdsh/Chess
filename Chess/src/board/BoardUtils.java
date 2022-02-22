package board;

public class BoardUtils {

	private BoardUtils()
	{
		throw new RuntimeException("You can not instantiate me");
	}
	
	public static boolean isValidTileCoordinate(int candidateDestinationCoordinate) {
		
		return candidateDestinationCoordinate >= 0 && candidateDestinationCoordinate < 64;
	}

}
