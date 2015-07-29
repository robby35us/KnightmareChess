package game;

import java.util.Scanner;

import moveDecorators.ActualMove;
import components.Board;
import components.Piece;
import utility.ErrorMessage;
import utility.MoveInput;
import definitions.Color;
import definitions.IOFramework;
import definitions.Turn;

public class TestIO implements IOFramework {
	private Operations ops;
	private Scanner input;
	private boolean showDisplay;
	private Board board;
	
	public TestIO(Board board, boolean showDisplay){
		ops = new Operations(showDisplay);
		this.board = board;
		this.showDisplay = showDisplay;
	}
	
	public TestIO(boolean showDisplay) {
		this.ops = new Operations(showDisplay);
		board = null;
		this.showDisplay = showDisplay;
	}

	@Override
	public Operations getOps() {
		return ops;
	}

	@Override
	public void displayBoard() {
		if(showDisplay)
			ops.prettyPrintBoard();
	}

	public void setMoveInput(String str){
		this.input = new Scanner(str);
	}
	
	@Override
	public MoveInput getMoveInput(Color color, ErrorMessage message) {
		return ops.getMoveInput(color, input, message);
	}

	@Override
	public void setupGame() {
		if(board != null)
			ops.setupTestGame(board);
		else
			ops.setupGame();
	}

	@Override
	public boolean meetsUniversalConstraints(ActualMove move, Turn turn, ErrorMessage message) {
		return ops.meetsUniversalConstraints(move, turn, message);
	}

	@Override
	public void displayMessage(ErrorMessage message) {
		return;
	}

	@Override
	public boolean promotePawn(Piece moving){
		return ops.promotePawn(moving, input);
	}

}
