#include "sequence.h"
#include <iostream>
using std::cerr;
using std::cout;

Sequence::Sequence()
{
	seq = ""; // empty sequence
}

Sequence::Sequence( string s )
{
	seq = s;
}


void Sequence::set_seq( string s )
{
	seq = s;
}


string Sequence::get_seq() const
{
	return seq;
}

int Sequence::get_length() const
{
	return seq.length();
}

char Sequence::operator[]( unsigned int index ) const
{
	if ( index > seq.length() ) cerr << "Invalid index: " << index << '\n';
	return seq[index];
}

int Sequence::compute_LCS( Sequence sq, vector<vector<int>> &lcs_table, vector<vector<int>> &result )
{
	// lcs_table[0......seq.length,0........sq.length]
	// result[0......seq.length,0........sq.length] 
	unsigned int sq1_length = (unsigned int)seq.length();
	unsigned int sq2_length = (unsigned int)sq.get_length();

	for ( unsigned int i=1 ;; i++ ) {
		if ( i == sq1_length + 1 ) break;
		for ( unsigned int j=1 ;; j++ ) {
			if ( j == sq2_length + 1 ) break;
			//cout << "i : " << i << " j : " << j << '\n';
			//cout << "seq[i] = " << seq[i] << " sq[j] = " << sq[j] << '\n';
			if ( seq[i] == sq[j] ) {
				lcs_table[i][j] = lcs_table[i-1][j-1] + 1;
				result[i][j] = 1; // LCS length for seq(i) and sq(j)
				continue;
//cout << "seq[i] == sq[j] : " << " i,j = " << i << "," << j << " : " << lcs_table[i][j] <<'\n';
			}
	
			if ( lcs_table[i-1][j] > lcs_table[i][j-1] ) {
				lcs_table[i][j] = lcs_table[i-1][j];
				result[i][j] = 2;
			
			}
			else {
				lcs_table[i][j] = lcs_table[i][j-1];
				result[i][j] = 3;
				
			} //if
		} //for
	} //for

	return lcs_table[sq1_length][sq2_length];
}	
