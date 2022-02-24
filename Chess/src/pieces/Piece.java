package pieces;

import java.util.Collection;

import board.Board;
import board.Move;

public abstract class Piece 
{	
	protected final int piecePosition;
	protected final Alliance pieceAlliance;
	
	Piece(final int piecePosition , final Alliance pieceAlliance)
	{
		this.piecePosition = piecePosition;
		this.pieceAlliance = pieceAlliance;
	}
	
	public Alliance getPieceAlliance()
	{
		return this.pieceAlliance;
	}
	
	public abstract Collection<Move> calculateLehalMoves(final Board board);
}