/*	To act as a front end to the scan function
**  Written by Aaron Gordon
*/
#ifndef SCANNER_h
#define SCANNER_h

#include	<fstream>

class Scanner {
	private:
		int   	currSymb; 	//token number returned from scan
		std::string  currName; 	//identifier name
		double  currValue;  //value of number returned from scan
	public:
		void	init(std::string fname); 	
		int 	getCurrSymb();
		std::string	getCurrName();
		double	getCurrValue();
		void	nextToken();	//move on to next token
};
#endif
