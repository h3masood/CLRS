import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;



class Knapsack {
	
	private final int KNAPSACK_SIZE;
	private final int TOTAL_ITEMS;
	private int[][] items;

	Knapsack( int size, int total )
	{
		KNAPSACK_SIZE = size;
		TOTAL_ITEMS = total;
	}
	
       
	protected int getSize()
	{
		return KNAPSACK_SIZE;
	}
       
	protected int getNumItems()
	{
		return TOTAL_ITEMS;
	}

	protected void setItems( int[][] itemsArray )
	{ 
		items = itemsArray;
	}

	protected int computeOptimalValue()
	{
		int[][] table = new int[TOTAL_ITEMS+1][KNAPSACK_SIZE+1];
			
		for ( int i=0 ;; i++ ) {
			if ( i == KNAPSACK_SIZE + 1 ) {
				break;
			} 
			table[0][i] = 0; // optimal solution to empty prefix is 0
		} //for

		try {
			int t1 = 0; // tmp1
			int t2 = 0; // tmp2
			for ( int i=1 ;; i++ ) { 
				if ( i == TOTAL_ITEMS + 1 ) {
					break;
				}
				for ( int j=0 ;; j++ ) {
					if ( j == KNAPSACK_SIZE + 1 ) {
						break;
					}
					t1 = table[i-1][j]; // optimum solution to prefex of length i-1 with weight constraint j
					t2 = 0;
					if ( items[i][1] <= j ) {
						t2 = table[i-1][j-items[i][1]] + items[i][0];
					}
				     	
					// set the optimum solution to prefix of length i with weight constraint j as the max of the two computed solution
					if ( t1 > t2 ) {
						table[i][j] = t1;
						continue;
					}
					table[i][j] = t2;
				} //for
			} //for
			
		}
		catch ( ArrayIndexOutOfBoundsException e ) {
			e.printStackTrace();
		}

		return table[TOTAL_ITEMS][KNAPSACK_SIZE];
	}


	public static void main( String []args )
	{
		System.out.println( "Enter Knapsack size and weight constraint in that order\n" );
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		try {
			//System.out.println( "HERE 1" );
			//  System.out.println( "HERE 2 " + " values: " + totalItems + " " + knapsackSize );
			StringBuffer strBuff = new StringBuffer( br.readLine() );
			int knapsackSize = strBuff.indexOf( " " );
			int totalItems = Integer.valueOf( strBuff.substring( knapsackSize + 1, strBuff.length() ) );
			knapsackSize = Integer.valueOf( strBuff.substring( 0, knapsackSize ) );
			Knapsack knapsack = new Knapsack( knapsackSize, totalItems );
			int t1 = 0;
			int t2 = 0;
			int[][] items  = new int[totalItems+1][2];
			items[0][0] = 0; // insert a dummy entry
			items[0][1] = 0; 
			for ( int i=1 ;; i++ ) {
				if ( i == totalItems + 1 ) {
					break;
				}
				strBuff = new StringBuffer( br.readLine() );
				t1 = strBuff.indexOf( " " );
				t2 = Integer.valueOf( strBuff.substring( t1 + 1, strBuff.length() ) );
				t1 = Integer.valueOf( strBuff.substring( 0, t1 ) );
				items[i][0] = t1; // items[i][0] is the value of ith item                                                                                                                        
				items[i][1] = t2; // items[i][1] is the weight of the ith item                                                                                                                    
			}//for                 
			knapsack.setItems( items );
			int optimumValue = knapsack.computeOptimalValue();
			System.out.println( "optimumValue is = " + optimumValue );
		}
		catch ( IOException e ) {
			e.printStackTrace();
		} 
		
	}
	
	// Methods for debugging
	private void print()
	{
		for ( int i=0 ;; i++ ) {
			if ( i == TOTAL_ITEMS ) break;
			System.out.println( i + " : " + items[i][0] + " " + items[i][1] + '\n' );
			
		} //for
	}
			
}
