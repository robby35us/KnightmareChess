package io;
import java.util.Scanner;
import utility.ErrorMessage;
import utility.MoveInput;
import definitions.Color;
import definitions.IOFramework;
import definitions.PieceType;
import definitions.Turn;
import game.Operations;

public class TestIO implements IOFramework {
	private Operations ops;
	private Scanner input;
	private boolean showDisplay;
	
	public TestIO(Operations ops, String str,  boolean showDisplay){
		this.ops = ops;
		input = new Scanner(str);
		this.showDisplay = showDisplay;
	}

	@Override
	public void displayBoard() {
		if(showDisplay)
			ConsoleDisplay.displayBoard(ops.getBoard());
	}
	
	@Override
	public MoveInput getMoveInput(Color color, ErrorMessage message) {
		return ops.getMoveInput(color, input, message);
	}

	@Override
	public void displayMessage(ErrorMessage message) {
		return;
	}

	@Override
	public PieceType promotePawnTo(){
		return ops.getPawnPromotionType(input);
	}

	@Override
	public void runGameIntro() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayGetMoveInputText(Turn turn) {
		if(showDisplay)
			ConsoleDisplay.displayGetMoveInputText(turn);
	}
}
