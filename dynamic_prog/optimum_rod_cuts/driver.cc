#include <iostream>
#include "rod.h"
#include <vector>
using std::vector;
using std::endl;
using std::cout;
using std::cin;


int main()
{
	int rod_length = 10;
	vector<int> cut_price( rod_length );
	vector<int> revenue( rod_length );
	cout << "Enter the price of each of the cuts for a rod of length " << rod_length << endl;

	int price = 0;
	for ( int i=0 ;; i++ ) {
		if ( i == rod_length ) break;
		cin >> price;
		revenue[i] = 0;
		cut_price[i] = price;
		//cout << i << endl;
		//cout << "revenue[i] is: " << revenue[i] << endl;
		//cout << "cut_price[i] is: " << cut_price[i] << endl;
	} //for

/*
	for ( int i=0 ;; i++ ) {
		if ( i == rod_length ) break;
		cout << i << endl;
		cout << "revenue[i] is: " << revenue[i] << endl;
		cout << "cut_price[i] is: " << cut_price[i] << endl;
	} //for
*/
	
	Rod rod( rod_length );
	rod.optimum_cuts( cut_price, revenue );

}
