package ru.job4j;

/**
*Class Calculate для вывода текста на кансоль.
*@author apermyakov
*@since 09.10.2017
*@version 1
*/
public class Calculate {

	/**
	*Вывод строки на консоль.
	*@param args args.
	*/
	public static void main(String[] args) {
		System.out.println("Hello world!");
	}

	/**
	* Method echo.
	* @param name Your name.
	* @return Echo plus your name.
	*/
	public String echo(String name) {
		return "Echo, echo, echo : " + name;
}
}