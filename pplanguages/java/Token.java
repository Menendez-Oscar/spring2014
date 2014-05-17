import java.util.Scanner;
import java.io.FileReader;
import java.util.*;
public class Token{
	ArrayList<Syntax> syntax = new ArrayList();
	static Queue<String> code = new LinkedList();
	static ArrayList<Syntax> result = new ArrayList();

	public static void main(String [] args){
		
		new Token().getToken();
	
		for(int i = 0; i < result.size(); i++){
			System.out.println(result.get(i));
		}
	}


	public void getToken(){
		System.out.println("Enter file name(.txt or .dat): ");
		String fileName = new Scanner(System.in).next();
		init(fileName, "syntax.txt");
		String token, string, identifier;
		while(!code.isEmpty()){
			identifier = "";
			token = code.poll();
			
			while(token.length() > 0){
					// check if token is a number
				if(isNumeric(token)){
					result.add(new Syntax(token, 29));
					token = "";
				}//check if token starts with a number.
				else {
					String num = "";
					for(int i = 1; i < token.length(); i++){
						if(isNumeric(token.substring(0, i))){
							num = token.substring(0, i);
						}
						else if(!isNumeric(token.substring(0, i))){
							token = token.substring(i -1, token.length());
							i = token.length();
							if(num.length() > 0){
								result.add(new Syntax(num, 29));
							}
						}
					}
				}
				//check if token is a string
				if(token.startsWith("\"") && token.endsWith("\"")){
					result.add(new Syntax(token, 30));
					token = "";
				}//begins with ", but does not end with "
				else if(token.startsWith("\"")){
					result.add(new Syntax("unending string: " + token, 30));
					token = "";
				}


				//check if it is a reserved word
				for(int j = 0; j < syntax.size(); j++){
					if(token.equals(syntax.get(j).getSymbol())){
						result.add(syntax.get(j));
						token = "";
					}
					else if(token.startsWith(syntax.get(j).getSymbol())){
						result.add(syntax.get(j));
						token = token.substring(syntax.get(j).getSymbol().length() -1);
					}
				}
				if(token.length() > 0){
					boolean startsWith = false;
					for(int k = 0; k < syntax.size(); k++){
						if(token.startsWith(syntax.get(k).getSymbol())){
						result.add(syntax.get(k));
						token = token.substring(syntax.get(k).getSymbol().length());
						identifier = "";
						startsWith = true;
						}
					}
					if(!startsWith){
						identifier = identifier + token.charAt(0);
						token = token.substring(1, token.length());	
					}					
				}
				
			}
			if(identifier.length() > 0){
				result.add(new Syntax(identifier, 28));
			}
		}
	}

	/*
	 * populates an array list with the syntax to check code against syntax
	 * meaning(reserved words, operators and separators)
	 */
	public void init(String fileName, String rWords) {
		
			Scanner fr,lineSc;
			String line, token, string;
		// populates a queue code with the code to check
		try{
			fr = new Scanner(new FileReader(fileName));
			while(fr.hasNextLine()){
				line = fr.nextLine();
				lineSc = new Scanner(line);
				while(lineSc.hasNext()){
					token = lineSc.next();
					if(token.startsWith("\"") && !token.endsWith("\"")){
						//asuming no string is longer than one line
						string = token;
						while(lineSc.hasNext()){
							String temp = " " + lineSc.next();
							if(temp.endsWith("\"")){
								token = token + temp;
								break;
							}
							else{
								for(int i = 1; i <= temp.length(); i++){
									if(temp.substring(i-1, i).equals("\"")){
										string = token + temp.substring(i-1, i);
										code.add(string);
										token = temp.substring(i-1, temp.length());
										i = temp.length()+1;
									}
									else{
										string = token + temp.substring(i-1, i);
									}
								}
							}
						}
					}
					code.add(token);
					code.add("SPACE");
				}
				code.add("EOLN");
			}	
			fr.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		// populating syntax list with reserved words.
		try {
			fr = new Scanner(new FileReader(rWords));
			String[] tokens;
			while (fr.hasNextLine()) {
					tokens = fr.nextLine().split("\t");
					syntax.add(new Syntax(tokens[0], Integer.parseInt(tokens[1])));
				}
				fr.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}

		/*
		* Check if a give string
		* matches a number with optional '-' and decimal.
		*/
		public static boolean isNumeric(String str){
  			return str.matches("-?\\d+(\\.\\d+)?");  
		}
}
