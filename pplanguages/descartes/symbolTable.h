// symbol table

#ifndef sybolTable_H
#define symbolTable_H
#include    <stdio.h>
#include    <string>

class symbolTable
{
std::string gid;
double gnum;
public:
  symbolTable(std::string id, double num);
  std::string getId();//get string value from id
  double getNumber();//get double value from the number
};

#endif
