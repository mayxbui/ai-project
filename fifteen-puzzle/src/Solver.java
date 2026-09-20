import java.util.*;
// bfs(), dfs(), bestFirst(), aStar() 

public class Solver{
    public static class Search{
        public List<String> path;
        public int nodesExpanded;
        public boolean found;
        
        public Search(List<String> path, int nodesExpanded, boolean found){
            this.path = path;
            this.nodesExpanded = nodesExpanded;
            this.found = found;
        }
    }

    private static List<String> buildPath(Node goalNode){
        List<String> path = new ArrayList<>();
        Node current = goalNode;
        while(current != null){
            path.add(0, current.state);
            current = current.parent;
        }
        return path;
    }
    
    public static Search bfs(String start){
        int expanded=0;
        Queue<Node> openList = new LinkedList<>();
        Set<String> closedList = new HashSet<>();
        Set<String> inOpen = new HashSet<>();

        Node startNode = new Node(start, null, ' ', 0,0);
        openList.add(startNode);
        closedList.add(start);

        while (!openList.isEmpty()){
            Node current = openList.poll();
            if (Puzzle.isGoal(current.state)){
                return new Search(buildPath(current), expanded, true);
            }
            expanded++;
            closedList.add(current.state);
            
            for(String adj:Puzzle.getAdjacent(current.state)){
                if(!closedList.contains(adj) && !inOpen.contains(adj)){
                    Node child = new Node(adj, current, ' ', 0,0);
                    openList.add(child);
                    inOpen.add(adj);
                }
            }

        }
        return new Search(null, expanded, false);
    }


    public static Search aStar(String start) {
        int expanded = 0;
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
        Set<String> closedList = new HashSet<>();
        
        Node startNode = new Node(start, null, ' ', 0, Heuristic.manhattan(start));
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            if (Puzzle.isGoal(current.state)) {
                return new Search(buildPath(current), expanded, true);
            }

            if (closedList.contains(current.state)) continue; // stale duplicate, skip
            expanded++;
            closedList.add(current.state);

            for (String adj : Puzzle.getAdjacent(current.state)) {
                if (!closedList.contains(adj)) {
                    int g = current.g + 1;
                    int h = Heuristic.manhattan(adj);
                    Node child = new Node(adj, current, ' ', g, h);
                    openList.add(child);
                }
            }
        }
        return new Search(null, expanded, false);
    }
}