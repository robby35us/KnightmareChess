package game;

import java.util.Scanner;

import moveDecorators.ActualMove;
import components.Board;
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
	public MoveInput getMoveInput(Color color) {
		return ops.getMoveInput(color, input);
	}

	@Override
	public void setupGame() {
		ops.setupTestGame(board);
	}

	@Override
	public boolean meetsUniversalConstraints(ActualMove move, Turn turn) {
		return ops.meetsUniversalConstraints(move, turn);
	}

}
