package game;

import components.Piece;
import components.PlayerSet;

import definitions.Color;

public class Player {
	Color color;
	PlayerSet set;
	
	public Player(PlayerSet set, Color color){
		this.color = color;
		this.set = set;
	}
	
	public boolean losePiece(Piece piece){
		return set.removePiece(piece);
	}
	
	public Color getColor(){
		return color;
	}
}
