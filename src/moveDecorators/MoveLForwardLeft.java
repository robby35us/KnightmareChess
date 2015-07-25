package moveDecorators;
import definitions.Color;
import definitions.MoveType;

/* MoveLForwardLeft.java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces forwardss
 * and one space to the left.
 */
public class MoveLForwardLeft extends ActualMove{
	public MoveLForwardLeft(Color color){
		super(MoveType.LForwardLeft.getRankOffset(),
			  MoveType.LForwardLeft.getFileOffset(), 
			  color);
	}
}
