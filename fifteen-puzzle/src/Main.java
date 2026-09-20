import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// entry point: welcome msg, prompts, menu, ties 
class Main{

    /****** HELPER LOAD FILE *******/
    private static String loadFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNext()) {
                sb.append(fileScanner.next().charAt(0));
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        if (sb.length() != 16) return null;
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println("""
               ------------------------FIFTEEN-PUZZLE GAME------------------------------
                                            Welcome!
            Fifteen-puzle game consists of a 4x4 grid of tiles, with 1 empty space.
                        The tiles contain the letters from A through O.
            The goal is to slide tiles around until you get to the solution, which is:
                                            A B C D
                                            E F G H
                                            I J K L
                                            M N O -
                    There will be 2 algorithms used to solve this game: BFS and A*
            You will pick a puzzle + algorithm you wish to solve. Then grab a coffee and sit back.
                            Next thing you know, the game is SOLVED!
            """);

        /****** LOAD FILE *******/

        Scanner userInput = new Scanner(System.in);
        System.out.print("Pick a puzzle file [ test0/2/4/7/11.txt ] : ");
        String fileName = userInput.next().toLowerCase();
        String path = "tests/" + fileName;
        String startState = loadFile(path);
        if (startState == null) {
            System.out.println("Could not load puzzle file.");
            return;
        }

        /****** CHOOSE ALGO *******/
        System.out.print("Pick an algorithm [ bfs / astar ] : ");
        String algo = userInput.next().toLowerCase();
        while(!algo.equals("bfs") && !algo.equals("astar")){
            System.out.println("Pick an algorithm in this list [ bfs / astar ] :");
            algo = userInput.next();
        }

        /****** RUN ALGO *******/
        Solver.Search result;
        switch(algo){
            case "bfs": {
                result = Solver.bfs(startState);    
                break;
            }
            case "astar":{
                result = Solver.bfs(startState);
                break;
            }
            default: result = null;
        }

        /****** PRINT STATE *******/
        if (!result.found) {
            System.out.println("\nNo solution found.");
        } else {
            System.out.println("\nPath from start to goal:");
            for (String state : result.path) {
                Puzzle.print(state);
                System.out.println();
            }
            System.out.println("Path length: " + (result.path.size() - 1) + " moves");
            System.out.println("Nodes expanded: " + result.nodesExpanded);
        }

    }
}

