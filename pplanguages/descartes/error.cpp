#include "error.h"
#include <iostream>
using namespace std;

Error::Error(int num) {
	cerr << "ERROR:  number " << num << endl;
	exit(num);
}

Error::Error(int num, std::string msg) {
	cerr << "ERROR:  number " << num << ":  " << msg << endl;
	exit(num);
}
