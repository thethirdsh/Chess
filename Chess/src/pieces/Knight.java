package pieces;

import java.util.ArrayList;
import java.util.List;

import spals.shaded.com.google.common.collect.ImmutableList;
import board.Board;
import board.BoardUtils;
import board.Move;
import board.Tile;

public class Knight extends Piece 
{
	
	private static final int[] CANDIDATE_MOVE_COORDINATES = {-17 , -15 , -10 , -6 , 6 , 10 , 15 , 17};
	
	Knight(final int piecePosition, final Alliance pieceAlliance) 
	{
		super(piecePosition, pieceAlliance);
	}
	
	public List<Move> calculateLehalMoves(Board board) 
	{
		
		int candidateDestinationCoordinate;
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidate : CANDIDATE_MOVE_COORDINATES)
		{
			candidateDestinationCoordinate = this.piecePosition + currentCandidate;
			
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
			{
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
				
				if(!candidateDestinationTile.isTileOccupied())
				{
					legalMoves.add(new Move());
				}
				else
				{
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					if(this.pieceAlliance != pieceAlliance)
					{
						legalMoves.add(new Move());
					}
				}
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition , final int candidateOffset)
	{
		return false;
	}
	
}
