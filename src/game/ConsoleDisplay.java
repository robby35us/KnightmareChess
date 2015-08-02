package game;

import components.*;
import definitions.*;

public class ConsoleDisplay{
	public static void displayBoard(Board board){
		Space head = board.getSpace(Rank.Eight, File.A);
		Space current = head;
		System.out.println(" Rank    ----   ----   ----   ----   ----   ----   ----   ----");
		for(int i = 8; i >= 1; i--){
			if(current.getColor() == Color.White)
				System.out.printf("  %d    |  ", i);
			else {
				System.out.printf("  %d    | |", i);
			}
			for(int j = 1; j <= 8; j++){
				printPieceCode(current);
				if(current.getColor() == Color.White)
					if(current.getFile() == File.H)
						System.out.print("  |  ");
					else
						System.out.print("  | |");
				else
						System.out.print("| |  ");
				current = current.getSpaceRight();
			}
			System.out.println();
			System.out.println("         ----   ----   ----   ----   ----   ----   ----   ----");
			
			head = head.getSpaceBackward();
			current = head;
		}
		System.out.println("  File   a      b      c      d      e      f      g      h");
	}
	
	private static void printPieceCode(Space current) {
		Piece piece = current.getPiece();
		if(piece == null)
			System.out.print("  ");
		else{
			String result = "";
			result += piece.getColor() == Color.White ? "W" : "B";
			switch(piece.getType()){
				case Pawn : result += "P"; break;
				case Knight : result += "N"; break;
				case Bishop : result += "B"; break;
				case Rook : result += "R"; break;
				case Queen : result += "Q"; break;
				case King : result += "K"; break;
			}
			System.out.print(result);
		}
	}

	public static void displayGetMoveInputText(Turn turn) {
		System.out.println("Player " + (turn.ordinal() + 1) + ", enter next move (ex. e2 e4):");
		
	}	
	
	
	public static void invalidMoveText(){
		System.out.println("The entered move is not valid.");
		System.out.println("Please try again.");
		
	}
}
