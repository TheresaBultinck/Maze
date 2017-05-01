package MazeSolver;

/**
 * Solves the maze that is generated.
 * @author Theresa Bultinck
 *
 */
public class Solver {
	
	private char grid[][];
	private int exit;
	
	public Solver(char g[][]){
		grid = g;
		exit = searchExit();
	}
	
	public char[][] solveMaze(){
		solveMazeRecursively(0,searchEntry());
		return null;
	}
	
	/**
	 * Searches for the exit in the right wall of the maze.
	 * @return index of the exit position 
	 */
	private int searchExit(){
		for(int i = 0; i <= grid.length-1; i++){
			if(grid[i][grid.length-1] == ' '){
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * Searches for the entry in the left wall of the maze.
	 * @return index of the entry position
	 */
	private int searchEntry(){
		for(int i = 0; i <= grid.length-1; i++){
			if(grid[i][0] == ' '){
				return i;
			}
		}
		return 0;
	}
	
	private boolean solveMazeRecursively(int x, int y){
		if(y == exit){
			return true;
		}
		if(grid[y-1][x] == ' ')//step up
			solveMazeRecursively(x,y-2); 
		if(grid[y+1][x] == ' ') //step down
			solveMazeRecursively(y+2,x); 
		if(grid[y][x+1] == ' ') //step right
			solveMazeRecursively(x+2,y); 
		if(grid[y][x-1] == ' ') //step left
			solveMazeRecursively(x-2,y); 
	}
}
