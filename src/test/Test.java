package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({MateTest.class, MoveTest.class, PawnTest.class, MoveDecoratorTest.class})
public class Test{

	public static final boolean SHOW_DISPLAY = false;

	public void runTestSuite(){
		
	}
}