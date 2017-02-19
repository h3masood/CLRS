#include <string>
#include <vector>

using std::string;
using std::vector;


class Sequence {

  private:
	string seq; // DNA sequence

  public:
	Sequence();
	Sequence( string s );
	void set_seq( string s );
	string get_seq() const;
	int get_length() const;
	char operator[]( unsigned int index ) const;
	int compute_LCS( Sequence sq, vector<vector<int>> &lcs_table, vector<vector<int>> &result );
	
};
