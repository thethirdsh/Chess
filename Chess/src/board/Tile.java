package board;

import spals.shaded.com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

import pieces.Piece;

public abstract class Tile 
{
	protected final int tileCoordinate;
	
	private static final Map<Integer , EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
	
	private Tile(final int tileCoordinate)
	{
		this.tileCoordinate = tileCoordinate;
	}
	
	public abstract boolean isTileOccupied();
	
	public abstract Piece getPiece();
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() 
	{
		final Map<Integer , EmptyTile> emptyTileMap = new HashMap<>();
		
		for(int i = 0 ; i < BoardUtils.NUM_TILES ; i++)
			emptyTileMap.put(i , new EmptyTile(i));
		
		return ImmutableMap.copyOf(emptyTileMap);
	}
	
	public static Tile createTile(final int tileCoordinate , final Piece piece)
	{
		return piece != null ? new OccupiedTile(tileCoordinate , piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
	}

	
	public static final class EmptyTile extends Tile
	{
		public EmptyTile(final int tileCoordinate)
		{
			super(tileCoordinate);
		}
		
		public boolean isTileOccupied()
		{
			return false;
		}
		
		public Piece getPiece()
		{
			return null;
		}
		
	}
	
	
	public static final class OccupiedTile extends Tile
	{
		
		private final Piece pieceOnTile;
		
		private OccupiedTile(int tileCoordinate , final Piece pieceOnTile)
		{
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}
		
		public boolean isTileOccupied()
		{
			return true;
		}
		
		public Piece getPiece()
		{
			return this.pieceOnTile;
		}
	}
}
