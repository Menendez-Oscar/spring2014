/*  given a TSP format file:

       # points
       points     (literally "points" --- to fit format of others)
       x1 y1
       ...
       xn yn

   create a new file with same format but points
   shifted and scaled (same scaling in x and y directions)
   to fit in [2,98] in both x and y 
   so will look nice in HeuristicTSP window

   Inspired by burma14 TSP example
*/

import java.io.*;
import java.util.*;

public class Scaler
{
  // coordinate window in which to map
  public static final double XMIN=2, YMIN=2, XMAX=98, YMAX=98;

  // round final coords to ints
  public static final boolean MAKE_INTS = true;

  public static void main(String[] args) throws Exception
  {
    if( args.length != 2 )
    {
      System.out.println("Usage:  java Scaler <input file> <output file>");
      System.exit(1);
    }

    Scanner input = new Scanner( new File( args[0] ) );
    
    int n = input.nextInt();  input.nextLine();
    double[][] p = new double[n][2];

    String format = input.nextLine();  // read format line
    
    // read first point and initialize limits
    double x = input.nextDouble(), y = input.nextDouble();
    p[0][0]=x;  p[0][1]=y;
    double xmin=x, xmax=x, ymin=y, ymax=y;

    for( int k=1; k<n; k++ )
    {
      x = input.nextDouble(); y = input.nextDouble();
      p[k][0]=x;  p[k][1]=y;
      xmin = Math.min( xmin, x );
      xmax = Math.max( xmax, x );
      ymin = Math.min( ymin, y );
      ymax = Math.max( ymax, y );
    }

    System.out.println("x bounds: " + xmin + " " + xmax + " y bounds: " + ymin + " " + ymax );

    input.close();
    
    double scale = Math.min( (XMAX-XMIN)/(xmax-xmin), (YMAX-YMIN)/(ymax-ymin) );
    System.out.println("scale: " + scale );

    // create list of scaled points
    double[][] q = new double[n][2];
    for( int k=0; k<n; k++ )
    {
      q[k][0] = scale*( p[k][0] - xmin ) + XMIN;
      q[k][1] = scale*( p[k][1] - ymin ) + YMIN;
      System.out.println( k + ": (" + p[k][0] + "," + p[k][1] + ") --> (" + q[k][0] + "," +
                              q[k][1] + ")" );
    }
    
    PrintWriter output = new PrintWriter( new File( args[1] ) );

    output.println( n );

    output.println( format );

    for( int k=0; k<n; k++ )
      if( MAKE_INTS )
        output.println( (int) Math.round(q[k][0]) + " " + 
                        (int) Math.round(q[k][1]) );
      else
        output.println( q[k][0] + " " + q[k][1] );

    output.close();

  }
}
