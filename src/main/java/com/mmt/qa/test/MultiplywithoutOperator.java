package com.mmt.qa.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiplywithoutOperator {
	public static void main(String args[]) throws NumberFormatException, IOException {
		
		System.out.println("Enter the first number");
		BufferedReader obj = new BufferedReader(new InputStreamReader((System.in)));
		int a = Integer.parseInt(obj.readLine());
		System.out.println("Enter the Second number");
		int b = Integer.parseInt(obj.readLine());
		int c = 0;
		for (int i = 0; i < b; i++) {
			c = c + a;
		}
		System.out.println("The Product is " + c);
	}
}
