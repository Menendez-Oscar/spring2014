#include "operator.h"

void  Operator::setLeft(Expr* ptr) {
	if (left == NULL)	left = ptr;
	else				left->setLeft(ptr);
}

void Operator::print(){
    switch(getKind()){
        case PLUS:  cout<<" + "; break;
        case TIMES: cout<<" * "; break;
        case MINUS: cout<<" - "; break;
        default:    cout<<" / "; break;
    }
}

