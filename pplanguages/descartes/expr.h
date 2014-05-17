/*	For nodes representing expressions
**  Written by Aaron Gordon
*/
#ifndef EXPR_H
#define EXPR_H

#include  "node.h"
#include  "scanner.h"
#include  <iostream>
#include  <map>
#include  <string>


using namespace std;

class Expr : public Node {

	public:	Expr (){ }; 	//constructor
	public:	Expr * parse(Scanner &scan);
	public: virtual void setLeft(Expr *) {cout << "expr.setLeft" << endl;  };
	public: virtual double getNum(map<string, double> st) {cout << "expr.setLeft" << endl;  return -1;};
};
#endif
