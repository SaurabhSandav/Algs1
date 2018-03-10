import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

/**
 * Created by saurabh on 3/4/17.
 */
public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<>();

        for (String s : StdIn.readAllStrings()) {
            queue.enqueue(s);
        }

        Iterator<String> iterator = queue.iterator();

        for (int i = k; i > 0 && iterator.hasNext(); i--) {
            System.out.println(iterator.next());
        }
    }
}
