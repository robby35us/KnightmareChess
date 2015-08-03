package moves;
import definitions.Color;
import definitions.MoveType;

/* MoveLeft.java
 * This is a Move decorator that represents a piece
 * moving one space to the left, from it's player's 
 * point of view.
 */
public class MoveLeft extends ActualMove{
	public MoveLeft(Color color){
		super(MoveType.Left, 
			  color);
	}
}
