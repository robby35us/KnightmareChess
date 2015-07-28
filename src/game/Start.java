package game;

import java.io.IOException;

import moveDecorators.ActualMove;
import utility.MoveBuilder;
import utility.MoveInput;
import definitions.Color;
import definitions.IOFramework;
import definitions.Turn;

public class Start {
	public static void playGame(IOFramework fw) throws IOException{
		fw.setupGame();
		ActualMove move;
		MoveInput moveInput;
		Turn turn = Turn.Player1;
		do{
			fw.displayBoard();
			moveInput = fw.getMoveInput(Color.values()[turn.ordinal()]);
			if(moveInput != null)
				move = MoveBuilder.buildMoveObject(moveInput.getInit(), moveInput.getDest(), fw.getOps());
			else
				move = null;
			
			// MoveBuilder.buildMoveObject() returns null, this doesn't run and the program exits.
			// This isn't always the desired behavior.
			if(move != null && fw.meetsUniversalConstraints(move, turn) && !Operations.makeMove(move, turn, fw.getOps()).hasError()){
				if(turn == Turn.Player1)
					turn = Turn.Player2;
				else
					turn = Turn.Player1;
				if(!fw.getOps().checkForMate(turn))
					break;
			}
		}while(move != null);
	}

}
