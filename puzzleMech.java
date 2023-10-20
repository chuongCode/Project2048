import java.util.Random;
import java.util.Scanner;

public class puzzleMech {
	int highest = 0;
	static int[][] userArray = {
			{0, 0, 0, 0}, // row 0
			{0, 0, 0, 0}, //  row 1
			{0, 0, 0, 0}, // row 2
			{0, 0, 0, 0} // row 3
	};
	static boolean gameRunning = true;
	static int highNum = 0;
	
	public static void print2DArray(int[][] ary) {
		System.out.println("-\t-\t-\t-\t-\t-\n");
		for (int row = 0; row < ary.length; ++row) {
			System.out.print("|\t");
			for (int col = 0; col < ary.length; ++col) {
				if (ary[row][col] == 0) {
					System.out.print('*' + "\t");
				}
				else {
					System.out.print(ary[row][col] + "\t");
				}
				if (ary[row][col] > highNum) {
                    highNum = ary[row][col];
                }
			}			
			System.out.println("|\n");
		}
		System.out.println("-\t-\t-\t-\t-\t-");
	}
	
	public static int[][] transpose(int[][] board) {
        int tempVar;
        for (int k = 0; k <= 3; k++) {
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 2; j++) {
                    tempVar = board[i][j + 1];

                    if (tempVar == 0) {
                        board[i][j + 1] = board[i][j];
                        board[i][j] = 0;
                    } else {
                        System.out.println();
                    }
                }
            }
        }
        return board;
    }
	public static int[][] moveRight(int[][] board) {
        // This method moves everything to the right of the array so there are no blank spaces (0, 2, 0, 0) - > (0, 0, 0, 2)
        board = transpose(board);
        // Perform all the code below 4 times. The thought process behind it is that at MOST it will take 4 loops to merge all elements if it can be merged 
        // The code below loops through each item in the board and tries to merge it with the item to the right of it (2, 0, 2, 0) - > (4, 0, 0, 0) : Then in the end I use transpose to move 4 to the right	
            // Loop through each col
            for (int i = 0; i <= 3; i++) {
                // Loop through each row
                for (int j = 0; j <= 3; j++) {
                    // This keeps track of if a spot has already been merged with (meaning it shoudnt be merged with again)
                    int[][] tempBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    // Check all items ahead of it for merge possibilities 
                    for (int k = j; k <= 3; k++) {
                        if (board[i][k] != 0) {
                            if (tempBoard[i][j] != -1) {

                                if (board[i][j] == board[i][k] && (j - k == 1 || j - k == -1)) {

                                    board[i][j] = board[i][k] + board[i][j]; 
                                    board[i][k] = 0;

                                    // Indicate the position has been merged with and should not be merged with again
                                    tempBoard[i][j] = -1;
                                }
                            }
                        }
                    }
                }
            }
        
        // Push everything to the right
        board = transpose(board);
        return board;
    }
	private static int randGen(int type) {
		Random rand = new Random();
		int randNum, randNum2 = 0;
		
		if (type == 2) {
			randNum = rand.nextInt(10);
			if (randNum < 8) 
				return 2;
			else 
				return 4;
		}
		else {
			return rand.nextInt(4);
		}
	}
	
	private static void initialArrayPopulation() {
		int row, column, value = 0;
		for (int i = 0; i <= 1; ++i) {
			row  = randGen(1);
			column = randGen(1);
			value = randGen(2);
			
			userArray[row][column] = value;
		}
	}
	
	
	public static void shift(char dir) {
		if (dir == 'w') {
			for (int i = 3; i >= 1; --i) {
	            for (int j = 0; j <= 3; ++j) {
	                if (userArray[i-1][j] == 0) {
	                	userArray[i-1][j] = userArray[i][j];
	                	userArray[i][j] = 0;
	                } 
	                else if (userArray[i-1][j] == userArray[i][j]) {
	                	userArray[i-1][j] += userArray[i][j];
	                	userArray[i][j] = 0;
	                }
	            }
	        }
		}
		else if (dir == 'a') {
			for (int i = 0; i <= 3; ++i) {
	            for (int j = 3; j >= 1; --j) {
	                if (userArray[i][j - 1] == 0) {
	                	userArray[i][j - 1] = userArray[i][j];
	                	userArray[i][j] = 0;
	                } 
	                else if (userArray[i][j-1] == userArray[i][j]) {
	                	userArray[i][j-1] += userArray[i][j];
	                	userArray[i][j] = 0;
	                }
	            }
	        }
		}
		else if (dir == 's') {
			for (int i = 0; i <= 2; ++i) {
	            for (int j = 0; j <= 3; ++j) {
	                if (userArray[i+1][j] == 0) {
	                	userArray[i+1][j] = userArray[i][j];
	                	userArray[i][j] = 0;
	                } 
	                else if (userArray[i+1][j] == userArray[i][j]) {
	                	userArray[i+1][j] += userArray[i][j];
	                	userArray[i][j] = 0;
	                }
	            }
	        }
		}
		else if (dir == 'd') {
			for (int i = 0; i <= 3; ++i) {
	            for (int j = 0; j <= 2; ++j) {
	                if (userArray[i][j + 1] == 0) {
	                	userArray[i][j + 1] = userArray[i][j];
	                	userArray[i][j] = 0;
	                } 
	                else if (userArray[i][j+1] == userArray[i][j]) {
	                	userArray[i][j+1] += userArray[i][j];
	                	userArray[i][j] = 0;
	                }
	            }
	        }
		}
                    
     }
	
	private static void movement() {
		Scanner scnr = new Scanner(System.in);
		char dir = scnr.next().charAt(0);
		
		if (dir == 'w') 
			shift('w');
		else if (dir == 'a') 
			shift('a');
		else if (dir == 's') 
			shift('s');
		else if (dir == 'd') 
			shift('d');
		else if (dir == 'q') 
			System.out.println("Are you sure you want to quit?");
			
			gameRunning = false;
		if (dir == 'r') 
			game();
		
		print2DArray(userArray);
	}
	
	
	private static void game() {
		initialArrayPopulation();
		print2DArray(userArray);
		System.out.println(" ");
		while (gameRunning) 
			movement();
		
	}
	
	public static void main(String[] args) {
		game();
	}

}
