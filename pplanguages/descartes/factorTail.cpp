#include <stdlib.h>
#include "factorTail.h"

Expr * FactorTail::parse(Scanner &scan) {
	Expr		*subtree;				//the expression subtree
	Expr		*rhs;					//lhs of the above subtree
	Factor		*f	= new Factor();		
	FactorTail	*ft	= new FactorTail();
	// factor-tail:  *  factor  factor-tail
	//			  	 /  factor  factor-tail
	if (scan.getCurrSymb() == TIMES || scan.getCurrSymb() == DIV) {	
		Operator   *op = new Operator(scan.getCurrSymb());
		scan.nextToken();
		rhs		= f->parse(scan);			//get the factor
		op->setRight(rhs);
		subtree = ft->parse(scan);			//get the factor-tail
		if (subtree != NULL) {
			subtree->setLeft(op);
			return subtree;
		} else return  op;
	} //if * or /
	return NULL;
}
