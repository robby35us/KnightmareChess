package game;

import components.King;
import components.Piece;
import components.PlayerSet;

import definitions.Color;

public class Player {
	Color color;
	PlayerSet set;
	private King opposingKing;
	
	public Player(PlayerSet set, Color color, King opposingKing){
		this.color = color;
		this.set = set;
		this.opposingKing = opposingKing;
	//	set.setKingObserver(opposingKing);
		for(Piece p : set){
			opposingKing.registerPieceObserver(p);
		}
	}
	
	public boolean losePiece(Piece piece){
		opposingKing.removePieceObserver(piece);
		//piece.removeKingObserver(opposingKing);
		piece.removeKingObserver(set.getKing());
		return set.removePiece(piece);
	}
	
	public Color getColor(){
		return color;
	}

	public void addPiece(Piece piece) {
		opposingKing.registerPieceObserver(piece);
		piece.registerKingObserver(set.getKing());
		set.addPiece(piece);
	}
}
