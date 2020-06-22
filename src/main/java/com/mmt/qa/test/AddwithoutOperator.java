package com.mmt.qa.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddwithoutOperator {


	public static void main(String args[]) throws NumberFormatException, IOException {
		
		System.out.println("Enter the first number");
		BufferedReader obj = new BufferedReader(new InputStreamReader((System.in)));
		int a = Integer.parseInt(obj.readLine());
		System.out.println("Enter the Second number");
		int b = Integer.parseInt(obj.readLine());
		int c = a;
		for (int i = 0; i < b; i++) {
			c = c + 1;
		}
		System.out.println("The sum is " + c);
	}

}
