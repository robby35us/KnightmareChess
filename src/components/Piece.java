package components;
import game.Operations;

import java.util.ArrayList;
import java.util.Iterator;

import moveDecorators.ActualMove;
import utility.ErrorMessage;
import utility.MoveBuilder;
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
public class Piece implements Iterable<MoveTypeAndConstraints>, PieceSubject, PieceObserver{
	protected PieceType type;
	protected Color color;
	protected Space space;
	
	// Represents whether a piece has been moved away from it's 
	// original position.
	boolean beenMoved = false;
	
	// A list of MoveTypeAndConstraints objects that hold what 
	// moves a piece can make and the constraints placed on those
	// movements.
	protected ArrayList<MoveTypeAndConstraints> moveTypesAndConstraints;
	private ArrayList<KingObserver> kings;
	private static Operations ops;
	
	
	public Piece(PieceType type, Color color){
		this.type = type;
		this.color = color;
		moveTypesAndConstraints = new ArrayList<MoveTypeAndConstraints>();
		kings = new ArrayList<KingObserver>();
	}
	
	public static void setOps(Operations ops){
		Piece.ops = ops;
	}
	// Public getters 
	public PieceType getType() {
		return type;
	}
	
	public Color getColor(){
		return color;
	}
	
	public Space getSpace(){
		return space;
	}
	
	public void setSpace(Space space){
		this.space = space;
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

	@Override
	public boolean updateOpposingPiece(Space destination) {
		return MoveBuilder.buildMoveObject(space, destination, ops) == null;
	}

	@Override
	public void registerKingObserver(KingObserver o) {
		kings.add(o);
	}

	@Override
	public void removeKingObserver(KingObserver o) {
		int index = kings.indexOf(o);
		if(index >= 0)
			kings.remove(index);
	}

	@Override
	public boolean notifyKingObservers() {
		for(KingObserver k : kings){
			if(!k.updateKing(this))
				return false;
		}
		return true;
		
	
	}

	public boolean tryEveryValidMove() {
	System.out.println("In tryEveryValidMove");
		for(MoveTypeAndConstraints mAndC : moveTypesAndConstraints){
			ActualMove move = MoveBuilder.buildMoveObject(space, mAndC.getMoveType(), ops);
			while(move != null){
				Piece captured = Operations.movePiece(move, ops);
				for(KingObserver k : kings){
					System.out.println(this);
					if(!k.updateKing(this))
						return false;
				}
				Operations.undoMove(move, captured, ops);
				move = MoveBuilder.buildMoveObject(move, mAndC.getMoveType(), ops);
			}
		}
		return true;
	}
}
