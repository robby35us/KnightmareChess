package game;

import java.util.Scanner;

import moveDecorators.ActualMove;

import utility.ErrorMessage;
import utility.MoveInput;
import definitions.Color;
import definitions.IOFramework;
import definitions.Turn;

public class ConsoleIO implements IOFramework {
	private Operations ops;
	
	public ConsoleIO(){
		ops = new Operations(true);
	}
	
	@Override
	public Operations getOps() {
		return ops;
	}

	@Override
	public void displayBoard() {
		ops.prettyPrintBoard();
	}

	@Override
	public MoveInput getMoveInput(Color color, ErrorMessage message) {
		return ops.getMoveInput(color, new Scanner(System.in), message);
	}

	@Override
	public void setupGame() {
		ops.setupGame();
	}

	@Override
	public boolean meetsUniversalConstraints(ActualMove move, Turn turn, ErrorMessage message) {
		return ops.meetsUniversalConstraints(move, turn, message);
	}

	@Override
	public void displayMessage(ErrorMessage message) {
		System.out.println(message);
	}
}
