public class Syntax{
	private String symbol;
	private int value;
	public Syntax(String symbol, int value){
		this.symbol = symbol;
		this.value = value;
	}

	public String getSymbol(){
	return symbol;
	}
	
	public int getValue(){
	return value;
	}

	public String toString(){
		return symbol + "\t" + value;
	}
}
