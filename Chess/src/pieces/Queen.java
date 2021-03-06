package pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import spals.shaded.com.google.common.collect.ImmutableList;
import board.Board;
import board.BoardUtils;
import board.Move;
import board.Tile;
import board.Move.AttackMove;
import board.Move.MajorMove;

public class Queen extends Piece
{
	
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9 , -8 , -7 , -1 , 1 , 7 , 8 , 9};

	Queen(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
		
	}

	
public Collection<Move> calculateLehalMoves(Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for(final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES)
		{
			int candidateDestinationCoordinate = this.piecePosition;
			
			while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
			{
				
				if(isFirstColumnExclusion(candidateDestinationCoordinate , candidateCoordinateOffset) || isEighthColumnExclusion(candidateDestinationCoordinate , candidateCoordinateOffset))
					break;
				candidateDestinationCoordinate += candidateCoordinateOffset;
				
				if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
				{
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
						break;
					}
					
				}
				
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition , final int candidateOffset)
	{
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1 || candidateOffset == -9 || candidateOffset == 7);
	}
	
	private static boolean isEighthColumnExclusion(final int currentPosition , final int candidateOffset)
	{
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
	}
}
