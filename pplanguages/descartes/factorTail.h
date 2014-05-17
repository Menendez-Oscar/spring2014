/*	For nodes representing numbers
**  Written by Aaron Gordon
*/
#ifndef FACTORTAIL_h
#define FACTORTAIL_h

#include "scanner.h"
#include "expr.h"
#include "factor.h"
#include "tokens.h"
#include "operator.h"

class FactorTail {
		 public: FactorTail() { };		//constructor
		 public: Expr * parse(Scanner &);	
};
#endif
