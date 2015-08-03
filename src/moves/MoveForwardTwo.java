package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveForwardTwo.java
 * This is a Move decorator that represents a piece
 * moving exactly two space forwards, from it's player's 
 * point of view.
 */
public class MoveForwardTwo extends ActualMove{
	public MoveForwardTwo(Color color){
		super(MoveType.ForwardTwo, 
			  color);
	}
}