package com.mmt.qa.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome {

	public static void checkPalindrome(String s) {
		// reverse the given String
		//String reverse = new StringBuffer(s).reverse().toString();
		String reverse = new StringBuilder().append(s).reverse().toString();

		// check whether the string is palindrome or not
		if (s.equals(reverse))
			System.out.println("String is a palindrome");

		else
			System.out.println("String is not a palindrome");
	}

	public static void main(String[] args) throws IOException {

		System.out.println("Enter the string to validate palindrome");
		BufferedReader obj = new BufferedReader(new InputStreamReader((System.in)));
		checkPalindrome(obj.readLine());
	}
}
