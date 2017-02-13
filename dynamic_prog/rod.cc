#include "rod.h"
#include <iostream>
#include <climits>

using std::vector;
using std::cout;
using std::endl;


Rod::Rod()
{
	length = 0;
}


Rod::Rod( int len )
{
	length = len;
}

int Rod::get_length() const
{
	return length;
}

void Rod::set_length( int len )
{
	length = len;
}

int Rod::optimum_cuts( vector<int> &p, vector<int> &r )
{
	
	vector<int> s( length + 1, 0 );

	/*
	for ( int i=0 ;; i++ ) {
		if ( i == length ) break;
		cout << i << endl;
		cout << "p[i] = " << p[i] << endl;
		cout << "r[i] = " << r[i] << endl;
	} //for
	*/
	// assumption: p and r of length len 
	s[0] = 0;
		
	int max_revenue = INT_MIN;
	for ( int i=1 ;; i++ ) { // index of start of sub-problem
		if ( i == length + 1 ) break;

		for ( int j=1 ;; j++ ) { // index of left cut
			if ( j > i ) break;
			cout << "i: " << i << "j: " << j << endl;
			int result = p[j-1] + r[i-j-1];
			if ( max_revenue < result ) {
				max_revenue = result;
				s[i] = j;
				cout << "s[i] = " << s[i] << endl; 
			} //if
			r[i-1] = max_revenue;
		} //for
	} //for

	cout << "Max revenue is = " << r[length-1] << "\n";
	cout << "The optimum cuts or the rod of length " << length << " are: " << '\n';

	
	int n = length;
	while ( true ) {
		if ( n <= 0 ) break;
		cout << s[n] << " ";
		n = n - s[n];
	} //while
	
	cout << '\n';
	
	return r[length-1];
	
}

