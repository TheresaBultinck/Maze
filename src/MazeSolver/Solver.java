package MazeSolver;

/**
 * Solves the maze that is generated.
 * Explanation: 
 * It is a breadth-first search algorithm, but if the if-structures step right and step left were placed 
 * before step up and step down (which isn't the case) then it would be a depth-first search algorithm.
 * In this algorithm (especially the method solveMazeRecursively) you look around for open (free) places in the maze
 * and these free places you assign to 'V' from visited. You continue this until you've found the end of the maze.
 * Then you run back to the beginning and you walk the correct path and there you put 'P' from path. 
 * The algorithm is just like a tree that consists of nodes (places in the maze) and you have to traverse the tree
 * searching for the right leaf by following a path. 
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
		int start = searchEntry();
		grid[start][0] = 'P';
		solveMazeRecursively(1,start);
		return grid;
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
	
	/**
	 * 
	 * @param x coordinate horizontally
	 * @param y coordinate vertically
	 * @return true if the maze is ended and we are at the exit position 
	 * and false if the maze hasn't been solved
	 */
	private boolean solveMazeRecursively(int x, int y){
		boolean ended = false;
		
		if(y == exit && x >= grid.length-1){
			ended = true; //true if he has found the exit
			grid[exit][grid.length-1] = 'P'; //p = path
			return true;
		} else {
			grid[y][x] = 'V'; //v = visited
		}
		if(!ended && grid[y-1][x] == ' '){//step up
			grid[y][x] = 'V';
			grid[y-1][x] = 'V';
			ended = solveMazeRecursively(x,y-2);
			if(ended){
				grid[y][x] = 'P';
				grid[y-1][x] = 'P';
				if(y-2 >= 0)
					grid[y-2][x] = 'P';
			}
		}
		if(!ended && grid[y+1][x] == ' '){ //step down
			grid[y][x] = 'V';
			grid[y+1][x] = 'V';
			ended = solveMazeRecursively(x,y+2);
			if (ended){
				grid[y][x] = 'P';
				grid[y+1][x] = 'P';
				if(y+2 >= grid.length-1)
					grid[y+2][x] = 'P';
			}
		}
		if(!ended && grid[y][x+1] == ' '){//step right
			grid[y][x] = 'V';
			grid[y][x+1] = 'V';
			ended = solveMazeRecursively(x+2,y);
			if(ended){
				grid[y][x] = 'P';
				grid[y][x+1] = 'P';
				if (x+2 <= grid.length-1)
					 grid[y][x+2] = 'P';
			}
		} 
		if(!ended && grid[y][x-1] == ' ') {//step left
			grid[y][x] = 'V';
			grid[y][x-1] = 'V';
			ended = solveMazeRecursively(x-2,y);
			if (ended){
				grid[y][x] = 'P';
				grid[y][x-2] = 'P';
				if (x-2>=0)
					grid[y][x-1] = 'P';
			}
		}
		return ended; 
	}
}
