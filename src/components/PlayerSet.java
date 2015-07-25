package components;
import factory.PieceFactory;
import definitions.*;

import java.util.*;

public class PlayerSet implements Iterable<Piece>{
	private PieceFactory factory;
	private Color color;
	private ArrayList<Piece> pieces;
	
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

	public boolean removePiece(Piece piece){
		int index = pieces.indexOf(piece);
		if(index >=0){
			pieces.remove(index);
			return true;
		}
		return false;
	}
	
	public Piece addPiece(PieceType pieceType){
		Piece newPiece = factory.makePiece(pieceType, color) ;
		pieces.add(newPiece);
		return newPiece;
	}
	
	@Override
	public Iterator<Piece> iterator() {
		return pieces.iterator();
	}

	public Color getColor() {
		return color;
	}
}
