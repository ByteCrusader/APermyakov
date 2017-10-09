package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
*Class for tests.
*@author apermyakov
*@version $Id$
*@since 09.10.2017
*/

public class CalculateTest {
	
	/**
	*Test echo
	*/
	
	@Test
	public void whenTakeSomeNameThenPrintThreeEchoPlusThisName() {
		String name = "Alexander";
		String expect = "Echo, echo, echo : Alexander";
		Calculate calc = new Calculate();
		String result = calc.echo(name);
		assertThat(result, is(expect));
	}
}