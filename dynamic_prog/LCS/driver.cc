#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include "sequence.h"

using std::cout;
using std::cin;
using std::endl;
using std::string;
using std::vector;

string construct_LCS( Sequence sq1, Sequence sq2, vector<vector<int>> &res, int size );


int main( int argc, char **argv )
{
	string s1,s2;
	cout << "Enter the first DNA sequence: " << '\n';
	cin >> s1;
	cout << "Enter the second DNA sequence: " << '\n';
	cin >> s2;
	vector<vector<int>> lcs_table( s1.length() + 1, vector<int>( s2.length() + 1, 0 ) );
	vector<vector<int>> cs( s1.length() + 1, vector<int>( s2.length() + 1, 0 ) );
	Sequence sq1 = Sequence( s1 );
	Sequence sq2 = Sequence( s2 );
	int LCS_length = sq1.compute_LCS( sq2, lcs_table, cs );
	string LCS = construct_LCS( sq1, sq2, cs, LCS_length );
	std::reverse( LCS.begin(), LCS.end() );
	cout << "The length of LCS is " << LCS_length << '\n';
	cout << "The LCS is: " << LCS << '\n';
}

string construct_LCS( Sequence sq1, Sequence sq2, vector<vector<int>> &res, int size )
{
	string LCS;
	int sq1_length = sq1.get_length();
	int sq2_length = sq2.get_length();

	for ( int i=sq1_length ;; ) {
		if ( i == 0 ) break;
		for ( int j=sq2_length ;; ) {
			if ( j == 0 || i == 0 )  {
				i = 0;
				break;
			}
			if ( res[i][j] == 1 ) {
				LCS.push_back( sq1[i-1] );
				j--;
				i--;
				continue;
			}

			if ( res[i][j] == 2 ) {
				i--;
				continue;
			}

			if ( res[i][j] == 3 ) {
				j--;
				continue;
			}
	  
		} //for
	} //for

	return LCS;
}
