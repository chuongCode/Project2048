# Project2048
Utilizing array list transversal, replicated the popular sliding tile puzzle game, 2048, in Java

Important thing to note about our program: rather than making frequent use of method parameters and return statements, we used static fields whenever we could. The int[][] userArray field represents the game board, int[][] baseArray (a 4x4 2D array of all 0s) is used for resetting the board, etc.

The program's functions are contained within (Gameplay.java). Main is used for executing the game.

It first prints out a (text-based) decorated 4x4 array (with ‘*’s in place of ‘0’s), with 2 random spaces populated by either a 2 or a 4 (80% chance it’s a 2, 20% chance it’s a 4). It then prompts the user for input: to move in one of 4 directions utilizing ‘wasd’, to restart the game ‘r’, or to quit the game ‘q’ (for the latter 2 options, it asks for confirmation before going through with the action). The restart function sets the userArray to a clear board, then repopulates the board with two 2/4’s in random positions, resets the valid move counter and highest number tracker, then begins the game once more.

The program checks first if a valid move can be made before actually making the move. The validity function operates by checking whether or not a 0 is in the directed path of a non-zero element or an element of equivalent value. If so, a valid move can be made. If the proposed move is invalid, the move is not made and the unchanged board is re-printed. If the proposed move would be valid, the move is made, equal values combine, a new value (2 or 4) appears in a former blank space, the validMoves counter increments, the current maxNum is recalculated, the values of the aforementioned variables are printed for the user, and the newly modified board is re-printed. When a user inputs a movement input, it shifts all numbers in that direction (which is based on how many zeroes there are in that row/column). Then, it merges any elements that were equal to each other, then repeats the shift function once again in that direction. 

The game continues until either the user is no longer able to make any more valid moves (checked using the valid move checkers mentioned in the paragraph above, and if the array has all non-zero values), or if one of the values on the board reaches 2048 (tracked using the maxNum variable).  The main method simply calls the game method (which contains all the other functionalities), which continues into the rest of the gameplay.

