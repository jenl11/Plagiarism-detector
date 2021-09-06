import java.util.Arrays;
/**
 * The class <b>Solution</b> is used
 * to store a (partial) solution to the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class Solution {

    public int width, height;
    private int rows = 0;
    private int columns = -1;
    private boolean[][] board;


    /**
     * Constructor. Creates an instance of Solution
     * for a board of size <b>widthxheight</b>. That
     * solution does not have any board position
     * value explicitly specified yet.
     *
     * @param width
     *  the width of the board
     * @param height
     *  the height of the board
     */
    public Solution(int width, int height) {
        this.width = width;
        this.height = height;
        board = new boolean[height][width];

    }

   /**
     * Constructor. Creates an instance of Solution
     * wich is a deep copy of the instance received
     * as parameter.
     *
     * @param other
     *  Instance of solution to deep-copy
     */
     public Solution(Solution other) {
        this.width = other.width;
        this.height = other.height;
        this.board = new boolean[this.height][this.width];
        this.rows = other.rows;
        this.columns = other.columns;
        for(int i = 0; i < this.height; i++){
          this.board[i] = Arrays.copyOf(other.board[i], other.board[i].length);
        }

    }


    /**
     * returns <b>true</b> if and only the parameter
     * <b>other</b> is referencing an instance of a
     * Solution which is the ``same'' as  this
     * instance of Solution (its board as the same
     * values and it is completed to the same degree)
     *
     * @param other
     *  referenced object to compare
     */

    public boolean equals(Object other){
      if(other instanceof Solution){
        Solution sol = (Solution) other;
        boolean a = (this.rows == sol.rows && this.columns == sol.columns);
        boolean b = (this.width == sol.width && this.height == sol.height);
        if(a == true && b == true){
          for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
              if(this.board[j][i] != sol.board[j][i]){
                return false;
              }
            }
          }
          return true;
        }
      }
      return false;
    }

    /**
    * returns <b>true</b> if the solution
    * has been entirely specified
    *
    * @return
    * true if the solution is fully specified
    */
    public boolean isReady(){
        return (rows == (height - 1) && columns == (width - 1));
    }

    /**
    * specifies the ``next'' value of the
    * solution.
    * The first call to setNext specifies
    * the value of the board location (1,1),
    * the second call specifies the value
    *  of the board location (1,2) etc.
    *
    * If <b>setNext</b> is called more times
    * than there are positions on the board,
    * an error message is printed out and the
    * call is ignored.
    *
    * @param nextValue
    *  the boolean value of the next position
    *  of the solution
    */
    public void setNext(boolean nextValue) {
      if(rows == (height-1) && columns == (width-1)){
        System.out.println("Error: exceeded number of times method can be called.");
      }
      if(rows != height-1 || columns != width-1){
        columns++;
        if(columns < width){
          board[rows][columns] = nextValue;
        }
        else if(rows != height-1 && columns == width){
          columns = 0;
          rows++;
          board[rows][columns] = nextValue;
        }
      }
    }

    /**
    * returns <b>true</b> if the solution is completely
    * specified and is indeed working, that is, if it
    * will bring a board of the specified dimensions
    * from being  entirely ``off'' to being  entirely
    * ``on''.
    *
    * @return
    *  true if the solution is completely specified
    * and works
    */
    public boolean isSuccessful(){
        if(isReady()){
          for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
              int a = 0;
              if(board[i][j] == true){
                a++;
              }
              if((i-1 >= 0) && (board[i-1][j] == true)){
                a++;
              }
              if((i+1 < height) && (board[i+1][j] == true)){
                a++;
              }
              if((j-1 >= 0) && (board[i][j-1] == true)){
                a++;
              }
              if((j+1 < width) && (board[i][j+1] == true)){
                a++;
              }
              if(a%2 == 0){
                return false;
              }
            }
          }
          return true;
        }
        return false;

    }


    /**
     * returns a string representation of the solution
     *
     * @return
     *      the string representation
     */
    public String toString() {
        String str_rep = "[";
        for(int i = 0; i < board.length; i++){
          if(i < board.length-1){
            str_rep += Arrays.toString(board[i]) + ",\n";
          }
          else{
            str_rep += Arrays.toString(board[i]) + "]";
          }
        }
        return str_rep;
    }

}
