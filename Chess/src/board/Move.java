package board;

import pieces.Piece;

public abstract class Move 
{
	
	final Board board;
	final Piece movePiece;
	final int destinationCoordinate;
	
	Move(final Board board , final Piece movePiece , final int destinationCoordinate)
	{
		this.board = board;
		this.movePiece = movePiece;
		this.destinationCoordinate = destinationCoordinate;
	}
	
	public static final class MajorMove extends Move
	{

		MajorMove(final Board board, final Piece movePiece, final int destinationCoordinate) {
			super(board, movePiece, destinationCoordinate);
			
		}
		
	}
	
	public static final class AttackMove extends Move
	{
		
		final Piece attackedPiece;

		AttackMove(final Board board, final Piece movePiece, final int destinationCoordinate , final Piece attackedPiece) {
			super(board, movePiece, destinationCoordinate);
			this.attackedPiece = attackedPiece;
		}
		
	}
	
}
