package definitions;

//import components.Space;

//import moveDecorators.ActualMove;
import game.Operations;
import moveDecorators.ActualMove;
import utility.ErrorMessage;
import utility.MoveInput;

public interface IOFramework {
	public Operations getOps();
	public void displayBoard();
	public MoveInput getMoveInput(Color color, ErrorMessage message);
	public void setupGame();
	public boolean meetsUniversalConstraints(ActualMove move, Turn turn, ErrorMessage message);
	public void displayMessage(ErrorMessage message);

//	public ActualMove buildMoveObject(Space init, Space dest, Operations ops);
}
