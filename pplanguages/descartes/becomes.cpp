/*	For nodes representing assignment statements 
**  Written by Aaron Gordon	
*/

#include "tokens.h"
#include "becomes.h"
#include "number.h"
#include "error.h"
#include <iostream>

using namespace std;

void  Becomes::assignment(Scanner &scan) {	//parses assignment stmt
	Expr  *ex = new Expr();
	string variable = scan.getCurrName();
	Id *id = new Id(variable);
	setLHS(id);
	scan.nextToken();
	if (scan.getCurrSymb() != BECOMES) {
		string msg  = "Becomes::assignment:  wrong token type found --> ";
		msg.append(scan.getCurrName());
		new Error(-71, msg);
	} //if
	scan.nextToken();
	rhs = ex->parse(scan);
} //assignment
