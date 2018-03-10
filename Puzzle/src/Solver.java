import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * Created by saurabh on 3/13/17.
 */
public class Solver {

    private boolean solvable;
    private Stack<Board> solution;

    public Solver(Board initial) {
        if (initial == null)
            throw new NullPointerException();

        MinPQ<Node> minPQ = new MinPQ<>();
        MinPQ<Node> twinMinPQ = new MinPQ<>();

        minPQ.insert(new Node(initial, null, 0));
        twinMinPQ.insert(new Node(initial.twin(), null, 0));

        Node curNode = minPQ.delMin();
        Node twinNode = twinMinPQ.delMin();

        while (!curNode.board.isGoal() && !twinNode.board.isGoal()) {

            for (Board neighbour : curNode.board.neighbors()) {
                if (curNode.previous != null && neighbour.equals(curNode.previous.board))
                    continue;

                minPQ.insert(new Node(neighbour, curNode, curNode.moves));
            }

            for (Board neighbour : twinNode.board.neighbors()) {
                if (twinNode.previous != null && neighbour.equals(twinNode.previous.board))
                    continue;

                twinMinPQ.insert(new Node(neighbour, twinNode, twinNode.moves));
            }

            curNode = minPQ.delMin();
            twinNode = twinMinPQ.delMin();

            curNode.moves++;
            twinNode.moves++;
        }

        if (curNode.board.isGoal()) {
            solvable = true;

            solution = new Stack<>();
            solution.push(curNode.board);

            while (curNode.previous != null) {
                solution.push(curNode.previous.board);
                curNode = curNode.previous;
            }
        }

    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return solvable ? solution.size() - 1 : -1;
    }

    public Iterable<Board> solution() {
        return solvable ? solution : null;
    }

    private class Node implements Comparable<Node> {

        private Node previous;
        private Board board;
        private int moves;

        Node(Board board, Node previous, int moves) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
        }

        int priority() {
            return board.manhattan() + this.moves;
        }

        @Override
        public int compareTo(Node node) {
            return this.priority() - node.priority();
        }
    }


    public static void main(String[] args) {

    }
}
