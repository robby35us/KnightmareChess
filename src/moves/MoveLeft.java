package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveLeft.java
 * This is a Move decorator that represents a piece
 * moving one space to the left.
 */
public class MoveLeft extends ActualMove{
	public MoveLeft(Color color){
		super(MoveType.Left, 
			  color);
	}
}
