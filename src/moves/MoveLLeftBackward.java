package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveLLeftBackward.java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces two the
 * left and one space backwards, from it's player's
 * point of view.
 */
public class MoveLLeftBackward extends ActualMove{
	public MoveLLeftBackward(Color color){
		super(MoveType.LLeftBackward, 
			  color);
	}
}
