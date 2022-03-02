package pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import spals.shaded.com.google.common.collect.ImmutableList;
import board.Board;
import board.BoardUtils;
import board.Move;
import board.Move.MajorMove;
import board.Move.AttackMove;
import board.Tile;

public class Knight extends Piece 
{
	
	private static final int[] CANDIDATE_MOVE_COORDINATES = {-17 , -15 , -10 , -6 , 6 , 10 , 15 , 17};
	
	Knight(final int piecePosition, final Alliance pieceAlliance) 
	{
		super(piecePosition, pieceAlliance);
	}
	
	public Collection<Move> calculateLehalMoves(final Board board) 
	{
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES)
		{
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
			{
				
				if(isFirstColumnExclusion(this.piecePosition , currentCandidateOffset) || isSecondColumnExclusion(this.piecePosition , currentCandidateOffset) || isSeventhColumnExclusion(this.piecePosition , currentCandidateOffset) || isEighthColumnExclusion(this.piecePosition , currentCandidateOffset))			
					continue;
	
				
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
				
				if(!candidateDestinationTile.isTileOccupied())
				{
					legalMoves.add(new MajorMove(board , this , candidateDestinationCoordinate));
				}
				else
				{
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					if(this.pieceAlliance != pieceAlliance)
					{
						legalMoves.add(new AttackMove(board , this , candidateDestinationCoordinate , pieceAtDestination));
					}
				}
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition , final int candidateOffset)
	{
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
	}
	
	private static boolean isSecondColumnExclusion(final int currentPosition , final int candidateOffset)
	{
		return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
	}
	
	private static boolean isSeventhColumnExclusion(final int currentPosition , final int candidateOffset)
	{
		return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
	}
	
	private static boolean isEighthColumnExclusion(final int currentPosition , final int candidateOffset)
	{
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6 || candidateOffset == 10 || candidateOffset == 17);
	}
	
}
