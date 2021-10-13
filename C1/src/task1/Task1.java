/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Task1 {

	static List<Integer> Divions(int n) {
		List<Integer> listDiv = new ArrayList<>();
		int maxD = (int) Math.sqrt(n);
		for (int i = 1; i <= maxD; i++) {
			if (n % i == 0) {

				if (n / i == i) {
					listDiv.add(i);
				} else {
					listDiv.add(i);
					listDiv.add(n / i);
				}
			}
		}
		return listDiv;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the first number greater than zero:");
		int num1 = sc.nextInt();
		System.out.println("Please enter the second number greater than zero and not equal to first number:");
		int num2 = sc.nextInt();
		System.out.println("The factors of " + num1 + " are: ");
		List<Integer> l1 = Divions(num1);
		System.out.println(l1);
		System.out.println("The factors of " + num2 + " are: ");
		List<Integer> l2 = Divions(num2);
		System.out.println(l2);

		l1.retainAll(l2);
		if (l1.size() == 1 && l1.get(0) == 1) {
			System.out.println(num1 + " and " + num2 + " are relative primes.");
		} else {
			System.out.println(num1 + " and " + num2 + " are not relative primes.");
		}
	}

}
