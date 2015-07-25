package components;

import java.util.ArrayList;
import java.util.Iterator;
import utility.MoveTypeAndConstraints;
import constraints.MoveConstraint;
import definitions.MoveType;


import definitions.Color;
import definitions.PieceType;

public class Piece implements Iterable<MoveTypeAndConstraints>{
	protected PieceType type;
	protected Color color;
	boolean beenMoved = false;
	protected ArrayList<MoveTypeAndConstraints> moveTypesAndConstraints;
	
	public Piece(PieceType type, Color color){
		this.type = type;
		this.color = color;
		moveTypesAndConstraints = new ArrayList<MoveTypeAndConstraints>();
	}
	
	public PieceType getType() {
		return type;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void addMove(MoveType moveType, MoveConstraint[] constraints){
		moveTypesAndConstraints.add(new MoveTypeAndConstraints(moveType,constraints));
	}

	@Override
	public Iterator<MoveTypeAndConstraints> iterator() {
		return moveTypesAndConstraints.iterator();
	}
	
	public void markAsMoved(){
		beenMoved = true;
	}
	
	public boolean beenMoved(){
		return beenMoved;
	}

	public MoveConstraint[] getConstraints(MoveType moveType) {
		for(int i = 0; i < moveTypesAndConstraints.size(); i++)
			if(moveTypesAndConstraints.get(i).getMoveType() == moveType)
				return moveTypesAndConstraints.get(i).getConstraints();
		return null;
	}
}
