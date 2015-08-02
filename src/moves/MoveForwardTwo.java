package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveForwardTwo.java
 * This is a Move decorator that represents a piece
 * moving exactly two space forwards.
 */
public class MoveForwardTwo extends ActualMove{
	public MoveForwardTwo(Color color){
		super(MoveType.ForwardTwo, 
			  color);
	}
}