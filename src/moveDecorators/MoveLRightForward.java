package moveDecorators;
import definitions.Color;
import definitions.MoveType;

/* MoveLRightForward.Java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces to the
 * right and one space forward.
 */
public class MoveLRightForward extends ActualMove{
	public MoveLRightForward(Color color){
		super(MoveType.LRightForward.getRankOffset(),
			  MoveType.LRightForward.getFileOffset(), 
			  color);
	}
}
