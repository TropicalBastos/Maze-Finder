package maze;

import java.util.Scanner;

public class Maze {

    private char[][] maze;
    private static Scanner input;
    
    //starting coordinates
    private final int STARTX = 0;
    private final int STARTY = 0;
    
    //maze character definitions
    private final char MARKED = '.';
    private final char OPEN = ' ';
    private final char GOAL = 'G';
    
    public Maze(char[][] matrix){
        this.maze=matrix;
    }
    
    public static void main(String[] args) {
        input = new Scanner(System.in);
        
        System.out.println("Enter number of rows within the maze:");
        int rows = Integer.parseInt(input.nextLine());
        
        System.out.println("Enter number of columns:");
        int columns = Integer.parseInt(input.nextLine());
        
        System.out.println("Enter the maze:");
        char[][] temp = new char[rows][columns];
        
        for(int i=0;i<rows;i++){
            temp[i] = input.nextLine().toCharArray();
        }
        
        Maze mazeFinder = new Maze(temp);
        
        //program keeps going until there are no more solutions
        mazeFinder.pathFinder(mazeFinder.STARTY, mazeFinder.STARTX); 
        
        //only called after recursion of all posibilites 
        System.out.println("\nNo more solutions");
        
    }
        
    
    public boolean pathFinder(int y, int x){
        
        //if out of bounds
        if(x==-1 || y==-1 || y>=maze.length || 
                x>=maze[0].length)
            return false;
        //the goal
        if(maze[y][x]==GOAL){
            printMaze();
            moreSolutions();
            return true;
        }
        //if territory has been traversed or if blocked
        if(maze[y][x]!=OPEN)
            return false;
        
        //mark traversed territory
        maze[y][x]=MARKED;
        
        //recurse through each possible path
        pathFinder(y-1,x); //north
        pathFinder(y+1,x); //south
        pathFinder(y,x+1); //east
        pathFinder(y,x-1); //west
        
        maze[y][x]=' '; //unmark the maze for retraversal
        
        return false;
    }
  
    //print solution to screen
    public void printMaze(){
        System.out.println("\n\n\nThe Solution:\n");
            for(int y=0;y<maze.length;y++){
                for(int x=0;x<maze[y].length;x++){
                    System.out.print(maze[y][x]);
                    }
                System.out.println();
                }
    }
    
    //Input for user to see other solutions
    public void moreSolutions(){
            System.out.println(); 
            System.out.println("More solutions? Enter y for more or any other key to quit");
            String response = input.nextLine();
                if(!response.equals("y")){
                    System.out.println("\nGood bye!");
                    System.exit(0);
                }
    }
    
}
