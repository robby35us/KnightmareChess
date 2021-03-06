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
	private King king;
	
	/*
	 * Creates a PlayerSet with the standard 16 pieces in the given
	 * color. The color cannot be changed.
	 */
	public PlayerSet(PieceFactory factory, Color color){
		this.color = color;
		this.factory = factory;
		this.king = (King) factory.makePiece(PieceType.King, color);
		
		pieces = new ArrayList<Piece>();
		pieces.add(factory.makePiece(PieceType.Rook, color));
		pieces.add(factory.makePiece(PieceType.Knight, color));
		pieces.add(factory.makePiece(PieceType.Bishop, color));
		pieces.add(factory.makePiece(PieceType.Queen, color));
		pieces.add(king);
		pieces.add(factory.makePiece(PieceType.Bishop, color));
		pieces.add(factory.makePiece(PieceType.Knight, color));
		pieces.add(factory.makePiece(PieceType.Rook, color));
		for(int i = 0; i < 8; i++)
			pieces.add(factory.makePiece(PieceType.Pawn, color));
	
		// the king is registered as an observer of each piece
		setKingObserver(king);
	}

	/*
	 * Registers the given king as an observer for each
	 * piece in the set.
	 */
	public void setKingObserver(King king){
		for(Piece p : pieces){
			p.registerKingObserver(king);
		}
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
	
	/*
	 * Adds the given piece to the set. Provided to support
	 * the undo function.
	 */
	public void addPiece(Piece piece) {
		pieces.add(piece);
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

	public King getKing() {
		return king;
	}


}
