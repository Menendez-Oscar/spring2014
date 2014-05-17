/*	To build the parse tree for Descarte
**  Written by Aaron Gordon
*/
#ifndef  PARSETREE_h
#define  PARSETREE_h

#include	"stmtnode.h"
#include	"scanner.h"
#include    <map>
#include    <string>
#include    "operator.h"
#include    "expr.h"
using namespace std;

class ParseTree {
	private:	StmtNode *root;		//points to recursive descent tree
				Scanner  scan;		//Scanner object to retrieve tokens

	public:		ParseTree();		//constructor
				void build();
				void execute();
				void stmtTail (StmtNode &current);
				void stmt (StmtNode  *&current);
				void init(std::string fname);

};
#endif
