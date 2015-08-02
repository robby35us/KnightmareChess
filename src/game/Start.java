package game;

import java.io.IOException;

import moves.ActualMove;
import utility.ErrorMessage;
import utility.MoveInput;
import definitions.Color;
import definitions.IOFramework;
import definitions.PieceType;
import definitions.Turn;
import io.ConsoleIO;

public class Start {
	public static void main(String[] args) throws IOException{
		ErrorMessage message = Start.playGame(new ConsoleIO(), new Operations());
		System.out.println(message);
	}
	
	public static ErrorMessage playGame(IOFramework fw, Operations ops) throws IOException{
		ActualMove move;
		MoveInput moveInput;
		Turn turn = Turn.Player1;
		ErrorMessage message = new ErrorMessage();
		do{

			fw.displayBoard();
			if(message.hasError())
				ops.getMessages().add(message);
			message = new ErrorMessage();
			fw.displayGetMoveInputText(turn);
			moveInput = fw.getMoveInput(Color.values()[turn.ordinal()], message);
			if(moveInput != null)
				move = MoveBuilder.buildMoveObject(moveInput.getInit(), moveInput.getDest(), ops, message);
			else{
				if(message.hasError()){
					move = null;
				}
				else{
					fw.displayMessage(message);
					break;
				}
			}
			// MoveBuilder.buildMoveObject() returns null, this doesn't run and the program exits.
			// This isn't always the desired behavior.
			if(move != null && ops.meetsUniversalConstraints(move, turn, message)){
				ops.makeMove(move, turn, message);
				if(message.getPromotePawn()){
//					System.out.println("Before pawn promotion");
//					fw.displayBoard();
					PieceType pawnPromotionType = fw.promotePawnTo();
					if(ops.promotePawn(move.getDestinationSpace().getPiece(), pawnPromotionType)){
						message = new ErrorMessage(); // clear error message
					}
					else
						message.setUnableToPromotePawn();
//					System.out.println("After pawn promotion");
//					fw.displayBoard();
				}
				if(!message.hasError()){
					if(turn == Turn.Player1)
						turn = Turn.Player2;
					else
						turn = Turn.Player1;
//					System.out.println("Board before check for mate");
//					fw.displayBoard();
					if(/*doesNotHaveError &&*/ ops.checkForMate(turn, message).hasError()){
	//					System.out.println("Breaking from while loop in Start.playGame()");
						break;
					}
	//				System.out.println("Board after check for mate");
	//				fw.displayBoard();
				}
			}
			if(message.hasError())
				fw.displayMessage(message);
		}while(true);
		return message;
	}

}
