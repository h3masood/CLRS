import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

class PalindromicSequence {

	private final String str;
	int[][] table; // table used in computing LPS of str

	// constructor(s)
	PalindromicSequence( String s )
	{
		str = s;
	}

        protected int computeLengthOfLPS()
	{
		final int size = str.length();
		table = new int[size][size];
		// Semantics of table[i][j] = length of the LPS of the substring s[i], s[i+1],.....,s[j] of str
		// tmp variables
		int t1 = 0; 
		int t2 = 0; 
		int j = 0;
		char c1 = '0';
		char c2 = '0';

		for ( int i=0 ;; i++ ) { // substrings of length 1 are palindromic
			if ( i == size ) break;
			table[i][i] = 1; // string of length 1 are PS
		} //for
		
		// compute palindromic sequences of substrings of length 2,3,....,str.length in that order i.e. fill
		// table diagonal-wise
		for ( int length=2 ;; length++ ) {
			if ( length == size + 1 ) break;
			for ( int i=0 ;; i++ ) {
				if ( i > size - length ) break;
				j = i + length - 1; 
				t1 = table[i][j-1]; // length of LPS of s[i],s[i+1],....,s[j-1]
				t2 = table[i+1][j]; // length of LPS of s[i+1],....,s[j]
				c1 = str.charAt(i); 
				c2 = str.charAt(j);

				if ( c1 == c2 && length == 2 ) {
					table[i][j] = 2;
					continue;
				}

				if ( str.charAt(i) == str.charAt(j) ) {
					table[i][j] = 2 + table[i+1][j-1];
					continue;
				}

				if ( t1 > t2 ) {
					table[i][j] = t1;
				}
				else {
					table[i][j] = t2;
				}
			} //for
		} //for
		return table[0][size-1]; // return length of LPS of str
	}

	protected String constructLPS()
	{
		final int size = str.length();
		// index vars
		int i = 0;
		int j = size - 1;
		// end
		StringBuffer strBuf = new StringBuffer( "" ); // empty strBuf
		int LPSlength = table[0][size-1];
		try {
			int t1 = 0;		
			int t2 = 0;
			char c1 = '0';
			// traverse table to construct LPS
			// note: ties are broken such that the LPS that "appears first" in str gets constructed,
			// all others are ignored
			while ( true ) {
				if ( j < i ) break; // loop end condition
				if ( LPSlength == 1 ) return str.substring( 0, 1 ); 
				c1 = str.charAt(i);
				t1 = table[i][j-1];
                                t2 = table[i+1][j];
				if ( c1 == str.charAt(j) ) {
					i++;
					j--;
					strBuf.insert( 0, c1 );
					continue;
				} 
				if ( t1 >= t2 ) {
					j--;
				}
				else {
					i++;
				}
			} //while
		}
		catch ( ArrayIndexOutOfBoundsException e ) {
			e.printStackTrace();
		}
		// the above loop only constructs one half of the LPS, the following lines construct other half and merge the two
		StringBuffer tmp = new StringBuffer( strBuf.substring( 1, strBuf.length( ) ) ); /* assume LPS is odd length
												   => duplicate last length - 1 chars only */ 
		if ( LPSlength % 2 == 0 ) { // check if LPS is even length => duplicate all the string
			tmp = new StringBuffer( strBuf.substring( 0, strBuf.length() ) ); // substr the last length - 1 chars
		}
		tmp = tmp.reverse(); // reverse the chars in buffer
		String output = tmp.toString() + strBuf.toString(); // merge first half and second half to produce full LPS
		return output;
	}

	public static void main( String args[] )
	{
		BufferedReader br;
		br = new BufferedReader( new InputStreamReader( System.in ) ); // create buffer for buffered reading from System.in  
		StringBuffer strBuf; // for buffering input string
		EndOfInput:
		try { 
			String input = new String( "" );
			while ( true ) { // compute LPS of all strings until end of input
				System.out.println( "Enter the string for which you would like to compute a LPS. When you are done please enter quit." );
				strBuf = new StringBuffer( br.readLine() ); // read string from input buffer(System.in)
				input = strBuf.toString();
				if ( input.equals( "quit" ) ) break EndOfInput;
				PalindromicSequence ps = new PalindromicSequence( input );
				int LPSlength = ps.computeLengthOfLPS();
				String LPSstring = new String( "" );
				LPSstring = ps.constructLPS();
				System.out.println( "The length of LPS is = " + LPSlength + "\n" + 
						    "The LPS is = " + LPSstring );
			} //while
		}
		catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
