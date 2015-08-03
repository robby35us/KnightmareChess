package io;
import java.util.Scanner;
import utility.*;
import definitions.*;
import game.GameState;

public class ConsoleIO implements IOFramework {
	private GameState gs;
	private Scanner input = new Scanner(System.in);
	
	public ConsoleIO(GameState gs){
		this.gs = gs;
	}

	@Override
	public void displayBoard() {
		ConsoleDisplay.displayBoard(gs.getBoard());
	}

	@Override
	public MoveInput getMoveInput(Color color, ErrorMessage message) {
		return InputParser.getMoveInput(color, input, gs.getBoard(), message);
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
		if(message.hasError())
			ConsoleDisplay.invalidMoveText();
	}

	@Override
	public PieceType promotePawnTo() {
		try{
			while(true){
				System.out.println("Choose type to promote pawn to - Q, R, B, or K:");
				PieceType promotionType = InputParser.getPawnPromotionType(input);	
				if(promotionType != null)
					return promotionType;
				
				else
					System.out.println("Please try again.");
			}
		}	
		catch(Exception e){
			return null;
		}
	}

	@Override
	public void runGameIntro() {
		// TODO /* leaving blank for now */
		
	}

	@Override
	public void displayGetMoveInputText(Turn turn) {
		ConsoleDisplay.displayGetMoveInputText(turn);
	}
}
