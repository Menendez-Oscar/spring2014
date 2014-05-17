#include <stdlib.h>
#include "factor.h"
#include "Id.h"
#include "number.h"
#include "error.h"
#include <iostream>

using namespace std;

Expr * Factor::parse(Scanner &scan) {
	if (scan.getCurrSymb() == ID) {	// factor  :  ID
		Id   *id = new Id(scan.getCurrName());
		scan.nextToken();
		return  id;
	} else if (scan.getCurrSymb() == NUMBER) {	// factor  :  CONST
		Number *num = new Number(scan.getCurrValue());
		scan.nextToken();
		return num;
	} else if (scan.getCurrSymb() == OPEN) {	// factor :  (  expr  )
		scan.nextToken();
		Expr  ex;
		Expr  *exptr = ex.parse(scan);
		scan.nextToken();
		return exptr;
	} else {
		cerr << "Factor::parse  Unknown expression,  currSymb = " <<
				scan.getCurrSymb() << endl;
		new Error(3);
	}
	return NULL;
}
