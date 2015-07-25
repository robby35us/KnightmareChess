package moveDecorators;
import definitions.*;

/* MoveBackward.java
 * This is a Move decorator that represents a piece
 * moving one space backwards.
 */

public class MoveBackward extends ActualMove{
	public MoveBackward(Color color){
		super(MoveType.Backward.getRankOffset(),
			  MoveType.Backward.getFileOffset(), 
			  color);
	}
}
