/*	To act as a front end to the scan function
**  Written by Aaron Gordon
*/

#include	<string>
#include	<ctype.h>
#include	"scanner.h"
#include	"tokens.h"
#include	<stdlib.h>
#include	<iostream>
#include    <fstream>

using namespace std;

fstream fin;		//input file
void scan(int &, char *, double &);
string keyword[] = 	{"IF","THEN","ELSE","FI","LOOP","REPEAT","BREAK",
					 "PRINT","READ","OR","AND"};
int  tokenNum[] =	{ IF, THEN, ELSE, FI, LOOP, REPEAT, BREAK, PRINT,
					  READ, OR, AND};

void openInputFile(string arg) {
	string fname = arg;
	if (fname.length() == 0) {
		cout << "Enter file name: ";
		cin >> fname;
	}
	while (1) {			//prompt for file name and open file
		fin.open(fname.c_str());
		if (fin.fail()) {
			cout << "\n\n" << "file not found,  try again\n\n";
			cout << "Enter file name: ";
			cin >> fname;
		} else break;
	}
}

void Scanner::init(string fname) {
	openInputFile(fname);
	this->nextToken();
}

int	 Scanner::getCurrSymb() {
	return currSymb;
}

string	Scanner::getCurrName() {
 return  currName;
}

double	Scanner::getCurrValue() {
 return  currValue;
}

void  Scanner::nextToken() {	//move on to next token
	char  theWord[128];
	cout << "Scanner: nextToken() " << endl;
	scan(currSymb, theWord, currValue);
	currName = theWord;
}

void getWord(int &token, char word[], double &value) {
	int spot=0;
	cout << "Scanner:getWord " << endl;
	do {
		fin.get(word[++spot]);
	}  while(isalnum(word[spot]));
	fin.putback(word[spot]);
	word[spot] = '\0';
	for (int i=0; i<11; i++) {  // keyword or id?
		if (keyword[i].compare(word) == 0) {
			token = tokenNum[i];
			return;
		}
	}//for
	token = ID;
} //getWord

void getNumber(int &token, char word[], double &value) {
	int spot=0;
	cout << "Scanner:getNumber " << endl;
	do {
		fin.get(word[++spot]);
	}  while(isdigit(word[spot]));
	if (word[spot] == '.') {
		do {
			fin.get(word[++spot]);
		}  while(isdigit(word[spot]));
	}
	fin.putback(word[spot]);
	word[spot] = '\0';
	value = atof(word);
	token = NUMBER;
} //getNumber

void scan(int &token, char word[], double &value) {
	cout << "Scanner: scan() " << endl ;
	do {								//skip white space
		fin.get(word[0]);
	}  while(isspace(word[0]));
	if (isalpha(word[0]))	{			// id or keyword
		getWord(token, word, value);
	} else if (isdigit(word[0]))	{	// number
		getNumber(token, word, value);
	} else {							// other symbol / operator
		switch (word[0]) {
		case '.':	token = PERIOD;		word[1] = '\0';
				break;
		case ';':	token = SEMICOLON;		word[1] = '\0';
				break;
		case ':':	if (fin.peek() == '=') {
						fin.get(word[1]);
						word[2] = '\0';
						token = BECOMES;
					} else {
					  token = COLON;		word[1] = '\0';
					}
				break;
		case ',':	token = COMMA;		word[1] = '\0';
				break;
		case '<':	char ch2;
					fin.get(ch2);
					if (ch2 == '=') { 			//	<=
						token = LE;
						word[1] = ch2;
						word[2] = '\0';
					} else if (ch2 == '>') { 	//	<>
						token = NE;
						word[1] = ch2;
						word[2] = '\0';
					} else {					// <
						fin.putback(ch2);
						word[1] = '\0';
						token = LT;
					}
				break;
		case '=':	token = EQ;  		word[1] = '\0';
				break;
		case '>':	//char ch2;
					fin.get(ch2);
					if (ch2 == '=') { 			//	>=
						token = GE;
						word[1] = ch2;
						word[2] = '\0';
					} else {					// >
						fin.putback(ch2);
						word[1] = '\0';
						token = GT;
					}
				break;
		case '+':	token = PLUS;		word[1] = '\0';
				break;
		case '-':	token = MINUS;		word[1] = '\0';
				break;
		case '*':	token = TIMES;		word[1] = '\0';
				break;
		case '/':	token = DIV;		word[1] = '\0';
				break;
		case '(':	token = OPEN;		word[1] = '\0';
				break;
		case ')':	token = CLOSE;		word[1] = '\0';
				break;
		case '"':	int spot;
					spot = -1;
					token = STRING;
					do {
						fin.get(word[++spot]);
					} while (word[spot] != '"');
					word[spot] = '\0';
					break;
		default:	cerr << "scan: Unknown character [" << word[0]<<"] found in file" << endl;
					exit (12);
					break;
		} //switch
		return;
	}//else
} //scan
