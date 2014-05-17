/**
 * Object representing data in each cell [weight, edge]
 * Author: Oscar Menendez
 */
public class D{

	private int length;//weight, length just describes it better.
	private int edge; // vertix
	public D(int length){
		this.length = length;
		edge = 0;
	}

	public void setEdge(int edge){
		this.edge = edge;
	}

	//new length cannot be "-"
	public void setLength(int weight){
		this.length = weight;
	}

	public int getLength(){
	return length;
	}	

	//if length != 'infinity'
	public int getSum(D weight){
		int sum = this.length + weight.length;
		return sum;
	}

	public String toString(){
	    String str = "";
	    if(length == -1)
	        str = "[-, " + edge + "]";
	    else 
	        str = "[" + length + ", " + edge + "]";
	return str;
	}
}	

