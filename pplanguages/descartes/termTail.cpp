#include <stdlib.h>
#include "termTail.h"

Expr * TermTail::parse(Scanner &scan) {
	Expr		*subtree;				//the expression subtree
	Expr		*rhs;					//lhs of the above subtree
	Term		*t	= new Term();		
	TermTail	*tt	= new TermTail();
	// term-tail:  +  term  term-tail
	//			   -  term  term-tail
	if (scan.getCurrSymb() == PLUS || scan.getCurrSymb() == MINUS) {	
		Operator   *op = new Operator(scan.getCurrSymb());
		scan.nextToken();
		rhs		= t->parse(scan);			//get the term
		op->setRight(rhs);
		subtree = tt->parse(scan);			//get the term-tail
		if (subtree != NULL) {
			subtree->setLeft(op);
			return subtree;
		} else return  op;
	} //if * or /
	return NULL;
}
