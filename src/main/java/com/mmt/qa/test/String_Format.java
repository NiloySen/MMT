package com.mmt.qa.test;

public class String_Format {

	public static void main(String[] args) {
		int number = 44910;
		String numberAsString = String.format("%,d", number);
		System.out.println(numberAsString);
	}

}
