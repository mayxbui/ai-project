public class Heuristic {
    private static final String GOAL = "ABCDEFGHIJKLMNO-";

    public static int manhattan(String state) {
        int total = 0;
        for (int i = 0; i < state.length(); i++) {
            char tile = state.charAt(i);
            if (tile == '-') continue; // don't count the blank

            int goalIndex = GOAL.indexOf(tile);
            int currRow = i / 4, currCol = i % 4;
            int goalRow = goalIndex / 4, goalCol = goalIndex % 4;

            total += Math.abs(currRow - goalRow) + Math.abs(currCol - goalCol);
        }
        return total;
    }
}