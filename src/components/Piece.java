package components;
import java.util.ArrayList;
import java.util.Iterator;
import utility.MoveTypeAndConstraints;
import constraints.MoveConstraint;
import definitions.*;

/*
 * Piece.java
 * A Piece object represents a chess piece in the game. Pieces
 * have a PieceType, Color and a list of MoveTypeAndConstraints
 * objects (for each type of Move that a Piece can make, there is 
 * a list of Constraint objects used to prevent that piece from 
 * making and invalid move). A Piece also keeps track of whether 
 * or not it has been moved away from it's original position.
 */
public class Piece implements Iterable<MoveTypeAndConstraints>{
	protected PieceType type;
	protected Color color;
	
	// Represents whether a piece has been moved away from it's 
	// original position.
	boolean beenMoved = false;
	
	// A list of MoveTypeAndConstraints objects that hold what 
	// moves a piece can make and the constraints placed on those
	// movements.
	protected ArrayList<MoveTypeAndConstraints> moveTypesAndConstraints;
	
	
	public Piece(PieceType type, Color color){
		this.type = type;
		this.color = color;
		moveTypesAndConstraints = new ArrayList<MoveTypeAndConstraints>();
	}
	
	// Public getters 
	public PieceType getType() {
		return type;
	}
	
	public Color getColor(){
		return color;
	}
	
	/* !!? How do we handle the MoveType already being added?!!!
	 * Used to add a new MoveType with cooresponding Constraint objects
	 * to the Piece's moveTypesAndConstraints.
	 */
	public void addMove(MoveType moveType, MoveConstraint[] constraints){
		moveTypesAndConstraints.add(new MoveTypeAndConstraints(moveType,constraints));
	}

	@Override
	/* !!? DO WE NEED THIS FUNCTIONALITY ?!!
	 * Returns an Iterator that iterates over the given
	 * MoveTypes and cooresponding Constraints in a 
	 * MoveTypesAndConstraints object.
	 */
	public Iterator<MoveTypeAndConstraints> iterator() {
		return moveTypesAndConstraints.iterator();
	}
	
	/*
	 * Sets a piece as having moved away from its original
	 * location. Before it is called, beenMoved will return
	 * false. Once called, beenMoved() will always return
	 * true. There is no way to set it back.
	 */
	public void markAsMoved(){
		beenMoved = true;
	}
	
	/*
	 * Returns whether markAsMoved() has been called.
	 */
	public boolean beenMoved(){
		return beenMoved;
	}

	/*
	 * Returns a list of Constraint objects associated with
	 * the given MoveType, if that MoveType has been added
	 * to the Piece using the addMove() method. Otherwise,
	 * returns null;
	 */
	public MoveConstraint[] getConstraints(MoveType moveType) {
		for(int i = 0; i < moveTypesAndConstraints.size(); i++)
			if(moveTypesAndConstraints.get(i).getMoveType() == moveType)
				return moveTypesAndConstraints.get(i).getConstraints();
		return null;
	}
}
