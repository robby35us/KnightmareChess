/* Board.java
 * The board class represents the chess board.
 * A board is simply a 2-D array of space objects.
 */

package components;
import definitions.File;
import definitions.Rank;


public class Board {
	private Space[][] spaces;
	
	public Board(){
		spaces = new Space[Rank.values().length][File.values().length];
		for(Rank r : Rank.values())
			for(File f : File.values())
				spaces[r.ordinal()][f.ordinal()] = new Space(r,f, this);
	}
	
	/*
	 * This function finds another space(the next space) on the board in relation to
	 * a given space (initialSpace). It returns null if there is no such space. 
	 */
	public Space getNextSpace(int rankOffset, int fileOffset, Space initialSpace){
		int newRank = initialSpace.getRank().ordinal() + rankOffset;
		int newFile = initialSpace.getFile().ordinal() + fileOffset;
		if(newRank >= 0 && newRank < Rank.values().length &&
		   newFile >= 0 && newFile < File.values().length)
			return spaces[newRank][newFile];
		else{
			return null;
		}
	}
	
	public Space getSpace(Rank rank, File file){
		return spaces[rank.ordinal()][file.ordinal()];
	}
}
