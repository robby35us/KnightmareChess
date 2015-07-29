package game;

import utility.ErrorMessage;
import components.King;
import components.Piece;
import components.PlayerSet;

import definitions.Color;
import definitions.PieceType;

public class Player {
	Color color;
	PlayerSet set;
	private King opposingKing;
	
	public Player(PlayerSet set, Color color, King opposingKing){
		this.color = color;
		this.set = set;
		this.opposingKing = opposingKing;
		for(Piece p : set){
			opposingKing.registerOpposingPieceObserver(p);
		}
	}
	
	public boolean losePiece(Piece piece){
		opposingKing.removeOpposingPieceObserver(piece);
		piece.removeKingObserver(set.getKing());
		return set.removePiece(piece);
	}
	
	public Color getColor(){
		return color;
	}

	public void addPiece(Piece piece) {
		opposingKing.registerOpposingPieceObserver(piece);
		piece.registerKingObserver(set.getKing());
		set.addPiece(piece);
	}

	// this code seems to be incorrect
	public void checkForMate(ErrorMessage message) {
		boolean isMate = true;
		for(Piece p : set){
			if(p.tryEveryValidMove()){
				isMate = false;
				break;
			}
		}
		if(isMate)
			message.setMate();
		System.out.println(message + " in Player.checkForMate().");
	}
}
