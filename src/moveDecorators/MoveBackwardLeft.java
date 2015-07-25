package moveDecorators;
import definitions.Color;
import definitions.MoveType;

/* MoveBackwardLeft.java
 * This is a Move decorator that represents a piece
 * moving one space diagonally both backwards and to 
 * the left.
 */
public class MoveBackwardLeft extends ActualMove{
	public MoveBackwardLeft(Color color){
		super(MoveType.BackwardLeft.getRankOffset(),
			  MoveType.BackwardLeft.getFileOffset(), 
			  color);
	}
}
