package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveBackwardRight.java
 * This is a Move decorator that represents a piece
 * moving one space diagonally both backwards and to 
 * the right.
 */
public class MoveBackwardRight extends ActualMove{
	public MoveBackwardRight(Color color){
		super(MoveType.BackwardRight.getRankOffset(),
			  MoveType.BackwardRight.getFileOffset(), 
			  color);
	}
}
