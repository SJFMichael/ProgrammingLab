/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task2;

/**
 *
 * @author Administrator
 */
public class Task2 {

	public static void main(String[] args) {
		int i = 1;
		int j = 0;
		while (i <= 10) {
			j = 1;
			while (j <= 10) {
				System.out.printf("  %3d", i * j);
				j = j + 1;
			}
			System.out.println();
			i = i + 1;
		}
		
	}

}
