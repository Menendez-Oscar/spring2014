/*	For nodes representing numbers
**  Written by Aaron Gordon
*/
#ifndef TERM_H
#define TERM_H

#include "scanner.h"
#include "expr.h"
class Term {
		 public: Term() { };		//constructor
		 public: Expr * parse(Scanner &);	
};
#endif
