// symbolTable.cpp
#include "symbolTable.h"

symbolTable::symbolTable(string id, double num)
{
    gid = id;
    gnum = num;

}

string symbolTable::getId()
{
    return gid;
}

double symbolTable::getNumber(){
    return gnum;
}
