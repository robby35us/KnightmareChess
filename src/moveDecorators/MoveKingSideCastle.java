package moveDecorators;
import definitions.Color;
import definitions.MoveType;

/* MoveKingSideCastle.java
 * This is a Move decorator that represents a piece
 * moving two spaces toward its rook.
 */
public class MoveKingSideCastle extends ActualMove{
	public MoveKingSideCastle(Color color){
		super(MoveType.KingSideCastle.getRankOffset(), 
		      color == Color.White ? 
		    		  MoveType.KingSideCastle.getFileOffset() : 
		    		  -MoveType.KingSideCastle.getFileOffset(), 
		      color);
	}
}
