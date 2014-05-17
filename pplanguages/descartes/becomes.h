/*	For nodes representing assignment statements
**  Written by Aaron Gordon
*/
#ifndef BECOMES_H
#define BECOMES_H

#include "tokens.h"
#include "stmtnode.h"
#include "scanner.h"
#include "expr.h"
#include "Id.h"

class Becomes : public StmtNode {
	private:	Id		*lhs;		//lefthand side of assignment statement
				Expr	*rhs;		//right hand side of assignment statement
	public:	Becomes (){ nodekind = BECOMES;}; 	//constructor
			Id *getLHS() {return lhs;};			//accessor
			Expr *getRHS() {return rhs;};		//accessor
			void setRHS(Expr *n) {rhs = n;};
			void setLHS(Id *n) {lhs = n;};
			void assignment(Scanner &scan);		//parses assignment stmt
};
#endif
