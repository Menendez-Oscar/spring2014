/*	To build the parse tree for Descarte
**  Written by Aaron Gordon
*/

//-------------------------
#include    <stdio.h>
#include    "tokens.h"
#include	"node.h"
#include	"parseTree.h"
#include	"becomes.h"
#include	"Id.h"
#include	"error.h"
#include	"operator.h"
#include	"number.h"
#include    "parseTree.h"
#include <iostream>
#include <string>
#include <map>


using namespace std;

//void getNum (Operator *&op); //takes operator

map<string, double> symbols; //symbol Table
double result = 0;

ParseTree::ParseTree(){
	root = NULL;
}//constructor

void ParseTree::init(string fname) {
	scan.init(fname);
}

void ParseTree::execute(){
	StmtNode *curr = root;
	Number *num;
	Operator *op;
	Node *n;
	Id *newId;
	while (curr != NULL ) {
		cout << "\n\n\nParseTree::executing" << endl;
		if (curr->getKind() != BECOMES) {
			string msg = "BECOMES (:=) expected";
			new Error(101, msg);
		}
		Becomes  *be = (Becomes *) curr;
		n = be->getLHS();
		Id  *id = (Id *)n;
		cout <<  id->getName()  << "  := " ;
		n = be->getRHS();
		switch (n->getKind()) {
		case NUMBER:  	num = (Number *) n;
                            symbols[id->getName()] = num->getValue();
                            result = num->getValue();
						break;
		case PLUS:      op = (Operator *) n;
                        result = op->getLeft()->getNum(symbols) + op->getRight()->getNum(symbols);
                        symbols[id->getName()] = result;
                   //     op->print();
                        break;
		case MINUS:     op = (Operator *) n;
                        result = op->getLeft()->getNum(symbols) - op->getRight()->getNum(symbols);
                        symbols[id->getName()] = result;
                  //      op->print();
                        break;
		case DIV:       result = op->getLeft()->getNum(symbols) / op->getRight()->getNum(symbols);
                        symbols[id->getName()] = result;
                   //     op->print();
						break;
		case TIMES:		op = (Operator *) n;
						result = op->getLeft()->getNum(symbols) * op->getRight()->getNum(symbols);
                        symbols[id->getName()] = result;
                   //     op->print();
                        break;
        case ID:        newId = (Id *) n;
                        symbols[id->getName()] = symbols[newId->getName()];
                        cout << symbols[id->getName()];
                        break;
		default:		cout << "  OTHER  "; break;
		}//switch
        cout << result;
		cout << endl;
		curr = curr->getNext();
	}//while
}//execute

void ParseTree::build( ) {  /* prog : stmt stmt-tail */
    stmt(root);				//root points to first statement node after call
    stmtTail(*root);		//pass in node where rest of pgm is to be attached
	cout << "time to end, token is " << scan.getCurrSymb() << endl;
    if (scan.getCurrSymb() != PERIOD) new Error(6, " PERIOD expected");
} //build

void ParseTree::stmtTail (StmtNode &current) {
/*  Current is the end of a chain of statements.  If there are more
    statements, tack them on the end. */
	StmtNode *nextStmt;
    if (scan.getCurrSymb() == SEMICOLON)  { // stmt-tail : SEMICOLON stmt stmt-tail
	  cout << "stmtTail: " << endl;
	  result = 0;
	  scan.nextToken();
      stmt(nextStmt);				//create the statement node for the next statement
      current.setNext(nextStmt);	//attach it to the list of nodes
      stmtTail(*nextStmt);			//build up the rest of the program
    } // else stmt-tail is empty; do nothing
} //stmtTail

void ParseTree::stmt (StmtNode *&current) {//create a statement node and have current point to it
	int symb = scan.getCurrSymb();
	switch (symb) {
	case ID: Becomes *be;
			 be = new Becomes();
			 current = be;
			 be->assignment(scan);	//find an assignment statement
			 break;					// stmt : ID := expr
	default: string msg2="Unrecognized statement: ";
			 new Error(4,msg2.append( scan.getCurrName()));
	}
} //ParseTree

