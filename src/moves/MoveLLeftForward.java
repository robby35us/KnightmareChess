package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveLLeftForward.java
 * This is a Move decorator that represents a piece
 * moving in an L-shaped pattern two spaces to the
 * left and one space forward, from it's player's
 * point of view.
 */
public class MoveLLeftForward extends ActualMove{
	public MoveLLeftForward(Color color){
		super(MoveType.LLeftForward, 
			  color);
	}
}
