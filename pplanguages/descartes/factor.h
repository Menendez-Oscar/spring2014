/*	For nodes representing numbers
**  Written by Aaron Gordon
*/
#ifndef FACTOR_H
#define FACTOR_H

#include "scanner.h"
#include "expr.h"
class Factor {
		 public: Factor() { };		//constructor
		 public: Expr * parse(Scanner &);	
};
#endif
