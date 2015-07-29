package test;
import org.junit.Test;
import game.Start;
import game.TestIO;

import java.io.IOException;

import junit.framework.Assert;

import utility.ErrorMessage;

public class MateTest {

	@Test
	public void testScholarsMate(){
		TestIO tio = new TestIO(false);
		tio.setMoveInput("e2 e4 e7 e5 f1 c4 b8 c6 d1 h5 a7 a5 h5 f7 q");
		ErrorMessage message = new ErrorMessage();
		try {
			message = Start.playGame(tio);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(message.getMate());
	}
}
