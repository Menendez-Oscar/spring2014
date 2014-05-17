/*	For nodes representing statements 
**  Written by Aaron Gordon
*/
#ifndef STMTNODE_h
#define STMTNODE_h

#include "stdlib.h"		//need this for NULL
#include "node.h"

class StmtNode : public Node {
	private:	StmtNode	*next;			//the next statement
	public: StmtNode (){ next = NULL;}; 	//constructor
	public: StmtNode *getNext() {return next;};		//accessor
	public: void setNext(StmtNode *n) {next = n;};
};
#endif
