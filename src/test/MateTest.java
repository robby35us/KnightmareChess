package test;
import org.junit.Test;
import static org.junit.Assert.*;

import game.Operations;
import game.Start;
import io.TestIO;

import java.io.IOException;
import utility.ErrorMessage;

public class MateTest {
	
	@Test
	public void testScholarsMate(){
		Operations ops = new Operations();
		TestIO tio = new TestIO(ops, "e2 e4 e7 e5 f1 c4 b8 c6 d1 h5 a7 a5 h5 f7 q", test.Test.SHOW_DISPLAY);
		ErrorMessage message = new ErrorMessage();
		try {
			message = Start.playGame(tio, ops);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(message.getMate());
	}
}
