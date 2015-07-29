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
		if(message.getConstraintNotMet()){
			System.out.println("This piece cannot move there at this time.");
		}
		else if(message.getIllegalPattern()){
			System.out.println("This piece cannot move in that pattern.");
		}
		else if(message.getWrongColorMoving()){
			System.out.println("This piece is the wrong color.");
		}
		else if(message.getWrongColorCaptured()){
			System.out.println("This piece cannot capture a piece of the same color.");
		}
		ops.invalidMoveText();
	}
}
