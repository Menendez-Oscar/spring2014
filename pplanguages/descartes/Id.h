/*	For nodes representing identifiers
**  Written by Aaron Gordon
*/
#ifndef ID_H
#define ID_H
#include "tokens.h"
#include "expr.h"
#include "parseTree.h"

using namespace std;

class Id : public Expr {

		 private: string  printname; 	//the string representation of the identifier
		 public: Id(string s) {nodekind = ID; printname = s;};			//constructor
		 		 string getName() { return printname;};	//accessor method
         public: virtual double getNum(map<string, double> st){
            return st[printname];
         }
};
#endif
