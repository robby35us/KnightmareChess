package game;

import java.io.IOException;

import moveDecorators.ActualMove;
import utility.ErrorMessage;
import utility.MoveBuilder;
import utility.MoveInput;
import definitions.Color;
import definitions.IOFramework;
import definitions.Turn;

public class Start {
	public static ErrorMessage playGame(IOFramework fw) throws IOException{
		fw.setupGame();
		ActualMove move;
		MoveInput moveInput;
		Turn turn = Turn.Player1;
		ErrorMessage message;
		do{

			fw.displayBoard();
			message = new ErrorMessage();
			moveInput = fw.getMoveInput(Color.values()[turn.ordinal()], message);
			if(moveInput != null)
				move = MoveBuilder.buildMoveObject(moveInput.getInit(), moveInput.getDest(), fw.getOps(), message);
			else{
				if(message.hasError())
					move = null;
				else
					break;
			}
			// MoveBuilder.buildMoveObject() returns null, this doesn't run and the program exits.
			// This isn't always the desired behavior.
			if(move != null && fw.meetsUniversalConstraints(move, turn, message) && !Operations.makeMove(move, turn, fw.getOps(), message).hasError()){
				if(turn == Turn.Player1)
					turn = Turn.Player2;
				else
					turn = Turn.Player1;
				System.out.println("Board before check for mate");
				fw.displayBoard();
				if(!message.hasError() && fw.getOps().checkForMate(turn, message).hasError()){
					System.out.println("Breaking from while loop in Start.playGame()");
					break;
				}
				System.out.println("Board after check for mate");
				fw.displayBoard();
			}
			if(message.hasError())
				fw.displayMessage(message);
		}while(true);
		return message;
	}

}
