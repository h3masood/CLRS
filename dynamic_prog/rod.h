#include <vector>
using std::vector;

class Rod {

  private:
	int length;

  public:
	// functions
	Rod();
	Rod( int len );

	int get_length() const;
	void set_length( int len );
	int optimum_cuts( vector<int> &p, vector<int> &r );

};
