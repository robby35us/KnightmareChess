package utility;
import constraints.MoveConstraint;
import definitions.MoveType;

public class MoveTypeAndConstraints {
	private MoveType moveType;
	private MoveConstraint[] constraints;
	
	public MoveTypeAndConstraints(MoveType moveType, MoveConstraint[] constraints){
		this.moveType = moveType;
		this.constraints = constraints;
	}
	
	public MoveType getMoveType(){
		return moveType;
	}
	
	public MoveConstraint[] getConstraints(){
		return constraints;
	}
}
