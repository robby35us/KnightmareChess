package game;

import java.util.ArrayList;
import java.util.Scanner;

import components.Piece;

import moves.ActualMove;

import utility.ErrorMessage;
import utility.MoveInput;
import definitions.Color;
import definitions.IOFramework;
import definitions.Turn;

public class ConsoleIO implements IOFramework {
	private Operations ops;
	private Scanner input = new Scanner(System.in);
	
	public ConsoleIO(){
		ops = new Operations(true, new ArrayList<ErrorMessage>());
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
		return ops.getMoveInput(color, input, message);
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
		if(message.hasError())
			ops.invalidMoveText();
	}

	@Override
	public boolean promotePawn(Piece moving) {
		try{
			while(true){
				System.out.println("Choose type to promote pawn to - Q, R, B, or K:");
				if(ops.promotePawn(moving, input)){
					
					return true;
				}
				else
					System.out.println("Please try again.");
			}
		}	
		catch(Exception e){
			return false;
		}
	}
}
