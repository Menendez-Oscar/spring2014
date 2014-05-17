/*	For nodes the superclass for all nodes
**	in the recursive descent tree
**  Written by Aaron Gordon
*/
#ifndef	NODE_h
#define	NODE_h

class Node  {
	 protected:	int nodekind;					//token number
	 public: Node(){};							//constructor
	 		 int getKind() {return nodekind;};	//accessor
};
#endif
