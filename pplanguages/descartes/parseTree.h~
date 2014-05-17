/*	To build the parse tree for Descarte
**  Written by Aaron Gordon
*/
#ifndef  PARSETREE_h
#define  PARSETREE_h

#include	"stmtNode.h"
#include	"scanner.h"

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
