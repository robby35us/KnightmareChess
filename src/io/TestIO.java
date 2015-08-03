package io;
import java.util.Scanner;
import utility.ErrorMessage;
import utility.MoveInput;
import definitions.Color;
import definitions.IOFramework;
import definitions.PieceType;
import definitions.Turn;
import game.GameState;

public class TestIO implements IOFramework {
	private GameState gs;
	private Scanner input;
	private boolean showDisplay;
	
	public TestIO(GameState gs, String str,  boolean showDisplay){
		this.gs = gs;
		input = new Scanner(str);
		this.showDisplay = showDisplay;
	}

	@Override
	public void displayBoard() {
		if(showDisplay)
			ConsoleDisplay.displayBoard(gs.getBoard());
	}
	
	@Override
	public MoveInput getMoveInput(Color color, ErrorMessage message) {
		return InputParser.getMoveInput(color, input, gs.getBoard(), message);
	}

	@Override
	public void displayMessage(ErrorMessage message) {
		return;
	}

	@Override
	public PieceType promotePawnTo(){
		return InputParser.getPawnPromotionType(input);
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
