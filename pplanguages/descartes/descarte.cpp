#include  "parseTree.h"
#include <iostream>
using namespace std;

int main(int argc, char **argv ) {
	ParseTree  ptree;
	string fname="";
	if (argc > 1) {
		fname.append(argv[1]);
	}
	ptree.init(fname);
	ptree.build();
	ptree.execute();
}
