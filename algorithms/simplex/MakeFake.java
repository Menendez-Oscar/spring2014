// make a fake tableau of desired size

import java.util.Scanner;

public class MakeFake{
  public static void main(String[] args){
    Scanner keys = new Scanner(System.in);
    System.out.print("number of rows: ");
    int m = keys.nextInt();   
    System.out.print("number of cols: ");
    int n = keys.nextInt();   

    System.out.println( m + " " + n );
 
    for( int k=0; k<m; k++ )
      System.out.println( "row" + k );

    for( int k=0; k<n; k++ )
      System.out.println( "col" + k );

    for( int r=0; r<m; r++ )
    {
      for( int c=0; c<n; c++ )
        System.out.print( (r+1) + "." + (c+1) + " " );
      System.out.println();
    }
  }
}
