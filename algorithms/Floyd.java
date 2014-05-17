/**
* CS 4050, Algoritms
* Author: Oscar Menendez
* Exercise 7, Floyd's algorithm
*/

import java.util.Scanner;
public class Floyd{
	

	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		int vertices = 0;
		D[][] lengths;//weight
		D[][] optLengths;//optimal weights
		System.out.println("Enter number of vertices: ");
		vertices = Integer.parseInt(sc.next());
		lengths = new D[vertices][vertices];

		for(int i = 0; i < vertices; i++){
			for(int j = 0; j < vertices; j++){
			System.out.println("Enter weight('-1' for ∞): ");
				lengths[i][j] = new D(Integer.parseInt(sc.next()));
				System.out.println(lengths[i][j]);
			}
		}
		//print optimal lengths
		for(int i = 0; i < vertices; i++){
		    System.out.print("|");
			for(int j = 0; j < vertices; j++){
				System.out.print(lengths[i][j].toString());
			}
			System.out.print("|\n");
			}
			
		optLengths = floydA(lengths, vertices);

		//print optimal lengths
		for(int i = 0; i < vertices; i++){
		    System.out.print("|");
			for(int j = 0; j < vertices; j++){
				System.out.print(optLengths[i][j].toString());
			}
			System.out.print("|\n");
			}
	}

	public static D[][] floydA(D[][] lengths, int vertices){
		
		for(int i = 0; i < vertices; i++){
		   	for(int j = 0; j < vertices; j++){
		    	for(int k = 0; k < vertices; k++){
				    if(j != i && k != i){
						if(lengths[j][k].getLength() == -1
						&& (lengths[i][j].getLength() != -1
						&& lengths[k][i].getLength() != -1)){
							lengths[j][k].setLength(lengths[i][j].getSum(lengths[k][i]));
							lengths[j][k].setEdge(i + 1);
							}
						else if(lengths[j][k].getLength() != -1
							&& (lengths[i][j].getLength() != -1
					    	&& lengths[k][i].getLength() != -1)){
							if(lengths[j][k].getLength() > lengths[i][j].getSum(lengths[k][i])){
								lengths[j][k].setLength(lengths[i][j].getSum(lengths[k][i]));
								lengths[j][k].setEdge(i + 1);
							}
						}
					}
				
				}
			}
		}

		return lengths;
	}
}