// state, parent, move, g, h, f

public class Node{
    public String state;
    public Node parent;
    public char move;
    public int g;
    public int h;
    public int f;

    public Node(String state, Node parent, char move, int g, int h){
        this.state = state;
        this.parent = parent;
        this.move = move;
        this.g=g;
        this.h=h;
        this.f=g+h;
    }
}