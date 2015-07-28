package test;
import moves.Move;
import org.junit.Test;
import org.junit.BeforeClass;
import definitions.Color;
import definitions.MoveType;
import factory.MoveFactory;


public class MoveDecoratorTest {
	static MoveFactory factory;
	
	@BeforeClass
	public static void setFactory(){
		factory = new MoveFactory();
	}
	
	@Test
	public void TestMoveForward(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.Forward, colors[i]);
			TestUtility.verifyMove(move, 1,0, colors[i]);
		}
	}
	
	@Test
	public void TestMoveForwardLeft(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.ForwardLeft, colors[i]);
			TestUtility.verifyMove(move, 1, -1, colors[i]);
		}
	}
	
	@Test
	public void TestMoveForwardRight(){

		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.ForwardRight, colors[i]);
			TestUtility.verifyMove(move, 1, 1, colors[i]);
		}
	}
	
	@Test
	public void TestMoveForwardTwo(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.ForwardTwo, colors[i]);
			TestUtility.verifyMove(move, 2, 0, colors[i]);
		}
	}
	
	@Test
	public void TestMoveLForwardLeft(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.LForwardLeft, colors[i]);
			TestUtility.verifyMove(move, 2, -1, colors[i]);
		}
	}
	
	@Test
	public void TestMoveLForwardRight(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.LForwardRight, colors[i]);
			TestUtility.verifyMove(move, 2, 1, colors[i]);
		}
	}
	
	@Test
	public void TestMoveLRightForward(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.LRightForward, colors[i]);
			TestUtility.verifyMove(move, 1, 2, colors[i]);
		}
	}
	
	@Test
	public void TestMoveLRightBackward(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.LRightBackward, colors[i]);
			TestUtility.verifyMove(move, -1, 2, colors[i]);
		}
	}
	
	@Test
	public void TestMoveLBackwardRight(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.LBackwardRight, colors[i]);
			TestUtility.verifyMove(move, -2, 1, colors[i]);
		}
	}
	
	@Test
	public void TestMoveLBackwardLeft(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.LBackwardLeft, colors[i]);
			TestUtility.verifyMove(move, -2, -1, colors[i]);
		}
	}
	
	@Test
	public void TestMoveLLeftBackward(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.LLeftBackward, colors[i]);
			TestUtility.verifyMove(move, -1,-2, colors[i]);
		}
	}
	
	@Test
	public void TestMoveLLeftForward(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.LLeftForward, colors[i]);
			TestUtility.verifyMove(move, 1, -2, colors[i]);
		}
	}
	
	@Test
	public void TestMoveBackwardRight(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.BackwardRight, colors[i]);
			TestUtility.verifyMove(move, -1, 1, colors[i]);
		}
	} 
	
	@Test
	public void TestMoveBackwardLeft(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.BackwardLeft, colors[i]);
			TestUtility.verifyMove(move, -1, -1, colors[i]);
		}
	}
	
	@Test
	public void TestMoveRight(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.Right, colors[i]);
			TestUtility.verifyMove(move, 0, 1, colors[i]);
		}
	}
	
	@Test
	public void TestMoveBackward(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.Backward, colors[i]);
			TestUtility.verifyMove(move, -1,0, colors[i]);
		}
	}
	
	@Test
	public void TestMoveLeft(){
		Move move;
		Color[] colors = Color.values();
		for(int i = 0; i <colors.length; i++){
			move = factory.makeMove(MoveType.Left, colors[i]);
			TestUtility.verifyMove(move, 0, -1, colors[i]);
		}
	}
	
	@Test
	public void TestMoveReverseKingSideCastle(){
		Move move;
		move = factory.makeMove(MoveType.ReverseKingSideCastle, Color.White);
		TestUtility.verifyMove(move, 0, -2, Color.White);
		move = factory.makeMove(MoveType.ReverseKingSideCastle, Color.Black);
		TestUtility.verifyMove(move, 0, 2, Color.Black);
	}
	
	@Test
	public void TestMoveReverseQueenSideCastle(){
		Move move;
		move = factory.makeMove(MoveType.ReverseQueenSideCastle, Color.White);
		TestUtility.verifyMove(move, 0, 3, Color.White);
		move = factory.makeMove(MoveType.ReverseQueenSideCastle, Color.Black);
		TestUtility.verifyMove(move, 0, -3, Color.Black);
	}
	
	@Test
	public void TestMoveKingSideCastle(){
		Move move;
		move = factory.makeMove(MoveType.KingSideCastle, Color.White);
		TestUtility.verifyMove(move, 0, 2, Color.White);
		move = factory.makeMove(MoveType.KingSideCastle, Color.Black);
		TestUtility.verifyMove(move, 0, -2, Color.Black);
	}
	
	@Test
	public void TestMoveQueenSideCastle(){
		Move move;
		move = factory.makeMove(MoveType.QueenSideCastle, Color.White);
		TestUtility.verifyMove(move, 0, -2, Color.White);
		move = factory.makeMove(MoveType.QueenSideCastle, Color.Black);
		TestUtility.verifyMove(move, 0, 2, Color.Black);
	}
}
