/*	For nodes representing numbers
**  Written by Aaron Gordon
*/
#ifndef TERMTAIL_h
#define TERMTAIL_h

#include "scanner.h"
#include "expr.h"
#include "term.h"
#include "tokens.h"
#include "operator.h"

class TermTail {
		 public: TermTail() { };		//constructor
		 public: Expr * parse(Scanner &);	
};
#endif
