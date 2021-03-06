package testComponents;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import components.Board;
import components.Space;
import definitions.File;
import definitions.Rank;
import definitions.Color;

public class BoardTest extends Board {

	@Test
	public void testGetSpace(){
		Board board = new Board();
		ArrayList<Space> spaces = new ArrayList<Space>();
		Color color = Color.Black;
		Space space;
		for(Rank r : Rank.values())
			for(File f : File.values()){
				space = board.getSpace(r,f);
				assertEquals(color, space.getColor());
				assertFalse(spaces.contains(space));
				spaces.add(space);
				if(f != File.H){
					if(color == Color.White)
						color = Color.Black;
					else
						color = Color.White;
				}	
			}
		assertEquals(64, spaces.size());
	}

	@Test
	public void testGetNextSpace(){
		Board board = new Board();
		ArrayList<Space> spaces = new ArrayList<Space>();
		for(Rank r : Rank.values())
			for(File f : File.values())
				spaces.add(board.getSpace(r, f));
		Space space;
		for(int spaceNum = 0; spaceNum < spaces.size(); spaceNum++){
			space = spaces.get(spaceNum);
			int rankOrdinal = spaceNum / 8;
			int lowerRankOffset = -rankOrdinal;
			int upperRankOffset = 7 - rankOrdinal;
			int spaceNum2 = 0;
			for(int i = lowerRankOffset; i <= upperRankOffset; i++){
				int fileOrdinal = spaceNum % 8;
				int lowerFileOffset = -fileOrdinal;
				int upperFileOffset = 7 - fileOrdinal;
				for(int j = lowerFileOffset; j <= upperFileOffset; j++, spaceNum2++){
//					System.out.println("spaceNum: " + spaceNum);
//					System.out.println("spaceNum2: " + spaceNum2);
//					System.out.println("i: " + i);
//					System.out.println("j: " + j);
					assertEquals(spaces.get(spaceNum2), board.getNextSpace(i, j, space));
				}
			}
		}
	}
	
	@Test
	public void testGetNextSpaceOutOfBounds(){
		Board board = new Board();
		assertNull(board.getNextSpace(0, 0, null));
		assertNull(board.getNextSpace(-1, -1, board.getSpace(Rank.One, File.A)));
		assertNull(board.getNextSpace(1, 1, board.getSpace(Rank.Eight, File.H)));
		assertNull(board.getNextSpace(-1, 1, board.getSpace(Rank.One, File.H)));
		assertNull(board.getNextSpace(1, -1, board.getSpace(Rank.Eight, File.A)));
	}
	
	@Test
	public void testGetSpaceNullValue(){
		Board board = new Board();
		assertNull(board.getSpace(null, File.A));
		assertNull(board.getSpace(Rank.Eight, null));
		assertNull(board.getSpace(null, null));
	}
}
