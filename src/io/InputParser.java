package io;

import java.util.Scanner;

import components.Board;
import components.Space;
import definitions.Color;
import definitions.File;
import definitions.PieceType;
import definitions.Rank;
import utility.ErrorMessage;
import utility.MoveInput;

public class InputParser {
	/*
	 * 
	 */
	public static MoveInput getMoveInput(Color color, Scanner in, Board board, ErrorMessage message) {
		String input = null;
		input = in.next();
		Space init = getSpace(input, board);
		if(init == null){
			if(!exitCondition(input)){
				message.setInvalidInput();
			}
			return null;
		}
		input = in.next();
		Space dest = getSpace(input, board);
		if(dest == null){
			message.setInvalidInput();
			return null;
		}
		return new MoveInput(init, dest);
	}
	
	public static PieceType getPawnPromotionType(Scanner in){
		String input = in.next();
		char selection = input.charAt(0);
		PieceType replacementType;
		switch(selection){
			case 'Q':
			case 'q':
				replacementType = PieceType.Queen;
				break;
			case 'R':
			case 'r':
				replacementType = PieceType.Rook;
				break;
			case 'K' :
			case 'k' :
				replacementType = PieceType.Knight;
				break;
			case 'B' :
			case 'b' :
				replacementType = PieceType.Bishop;
				break;
			default :
				return null;
		}
		return replacementType;
	}


	private static Space getSpace(String input, Board board) {
		if(input == null || input.length() != 2)
			return null;
		char fileInput = input.toLowerCase().charAt(0);
		char rankInput = input.charAt(1);
		if(fileInput >= 'a' && fileInput <= 'h' &&
		   rankInput >= '1' && rankInput <= '8')
			return board.getSpace(Rank.values()[rankInput - '1'], 
					              File.values()[fileInput - 'a']);
		else
			return null;
	}

	private static boolean exitCondition(String input) {
		 return input.charAt(0) == 'q' || input.charAt(0) == 'Q';
	}
}
