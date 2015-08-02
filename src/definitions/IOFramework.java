package definitions;
import utility.ErrorMessage;
import utility.MoveInput;

/*
 * This interface is intended allow the encapsulation
 * of the and IO API from the chess game play logic. 
 * Currently, however, there are functions that go 
 * beyond this intention and should eventually be
 * removed from this interface.
 */
public interface IOFramework {
	
	/*
	 * Refresh the board display.
	 */
	public void displayBoard();
	
	/*
	 * Return a MoveInput object that represents the next move
	 * as provided by the underlying input mechanism.
	 */
	public MoveInput getMoveInput(Color color, ErrorMessage message);
	
	/*
	 * Run game setup using the appropriate display.
	 */
	public void runGameIntro();

	
	/*
	 * Display the provided ErrorMessage object via the appropriate display.
	 */
	public void displayMessage(ErrorMessage message);
	
	/*
	 * Called when a pawn needs promoting.  Returns selected
	 *  PieceType that the pawn should be promoted to.
	 */
	public PieceType promotePawnTo();

	public void displayGetMoveInputText(Turn turn);
}
