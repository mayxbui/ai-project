import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    // treat the grid as an array of characters
    public static final String GOAL = "ABCDEFGHIJKLMNO-";

    public static boolean isGoal(String state){
        return state.equals(GOAL);
    }

    public static List<String> getAdjacent(String state){
        // get adjacent nodes & swap with blank node to move 
        List<String> adj = new ArrayList<>();
        int blank = state.indexOf('-');
        int row = blank/4;
        int col = blank%4;
        if (row>0) adj.add(swap(state, blank, blank-4));      // move up
        if (row<3) adj.add(swap(state, blank, blank+4));      // move down
        if (col>0) adj.add(swap(state, blank, blank-1));      // move left
        if (col<3) adj.add(swap(state, blank, blank+1));      // move right
        return adj;
    }

    // swap blank with the next 
    private static String swap(String state, int i, int j){
        char[] chars = state.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);   // convert back to String

    }

    public static void print(String state) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                System.out.print(state.charAt(row * 4 + col) + " ");
            }
            System.out.println();
        }
    }
}
