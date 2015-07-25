package moveDecorators;
import definitions.Color;
import definitions.MoveType;

/* MoveLRightBackward.Java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces to the 
 * right and one space backwards.
 */
public class MoveLRightBackward extends ActualMove{
	public MoveLRightBackward(Color color){
		super(MoveType.LRightBackward.getRankOffset(),
			  MoveType.LRightBackward.getFileOffset(), 
			  color);
	}
}
