package ru.apermyakov.strategy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
* Class for test paint anyone shape.
*
* @author apermyakov
* @since 16.10.2017
* @version 1.0
*/
public class PaintTest {

	/**
	* Test when drow square then square.
	*/
	@Test
	public void whenDrowSquareThenSquare() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		new Paint(new Square()).drow();
		StringBuilder picture = new StringBuilder();
		picture.append("++++++");
		picture.append(System.getProperty("line.separator"));
		picture.append("+xxxx+");
		picture.append(System.getProperty("line.separator"));
		picture.append("+xxxx+");
		picture.append(System.getProperty("line.separator"));
		picture.append("+xxxx+");
		picture.append(System.getProperty("line.separator"));
		picture.append("+xxxx+");
		picture.append(System.getProperty("line.separator"));
		picture.append("++++++");
		assertThat(out.toString(), is(String.format("%s%s", picture.toString(), System.getProperty("line.separator"))));
	}

	/**
	* Test when drow square then square.
	*/
	@Test
	public void whenDrowTriagleThenTriangle() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		new Paint(new Triangle()).drow();
		StringBuilder picture = new StringBuilder();
		picture.append("+++++++");
		picture.append(System.getProperty("line.separator"));
		picture.append("+++x+++");
		picture.append(System.getProperty("line.separator"));
		picture.append("++xxx++");
		picture.append(System.getProperty("line.separator"));
		picture.append("+xxxxx+");
		picture.append(System.getProperty("line.separator"));
		picture.append("+++++++");
		assertThat(out.toString(), is(String.format("%s%s", picture.toString(), System.getProperty("line.separator"))));
	}
}