package components;
import factory.PieceFactory;
import definitions.*;
import java.util.*;

/*
 * PlayerSet.java
 * A PlayerSet contains information about a set of 
 * Piece objects that a player controls. It is initialized
 * with the standard set of 16 chess pieces and then gets
 * modified from there. Pieces can be removed and added
 * as needed.
 */
public class PlayerSet implements Iterable<Piece>{
	private PieceFactory factory;
	private Color color;
	private ArrayList<Piece> pieces;
	
	/*
	 * Creates a PlayerSet with the standard 16 pieces in the given
	 * color. The color cannot be changed.
	 */
	public PlayerSet(PieceFactory factory, Color color){

		this.factory = factory;
		this.color = color;
		pieces = new ArrayList<Piece>();
		pieces.add(factory.makePiece(PieceType.Rook, color));
		pieces.add(factory.makePiece(PieceType.Knight, color));
		pieces.add(factory.makePiece(PieceType.Bishop, color));
		pieces.add(factory.makePiece(PieceType.Queen, color));
		pieces.add(factory.makePiece(PieceType.King, color));
		pieces.add(factory.makePiece(PieceType.Bishop, color));
		pieces.add(factory.makePiece(PieceType.Knight, color));
		pieces.add(factory.makePiece(PieceType.Rook, color));
		for(int i = 0; i < 8; i++)
			pieces.add(factory.makePiece(PieceType.Pawn, color));
	}

	/*
	 * Removes the provided Piece object from the set, if
	 * it is contained in the set. Returns true if successful,
	 * false otherwise.
	 */
	public boolean removePiece(Piece piece){
		int index = pieces.indexOf(piece);
		if(index >=0){
			pieces.remove(index);
			return true;
		}
		return false;
	}
	
	/* 
	 * Makes a new Piece of the given type and 
	 * adds it to the set. Returns the new Piece
	 * object.
	 */
	public Piece addPiece(PieceType pieceType){
		Piece newPiece = factory.makePiece(pieceType, color) ;
		pieces.add(newPiece);
		return newPiece;
	}
	
	@Override
	/*
	 * Returns an Iterator of the pieces in the 
	 * set.
	 */
	public Iterator<Piece> iterator() {
		return pieces.iterator();
	}

	// public getter
	public Color getColor() {
		return color;
	}
}
