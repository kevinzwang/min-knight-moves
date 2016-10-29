import java.util.ArrayList;
import java.util.Arrays;


public class Answer {
    
    static int minMoves = Integer.MAX_VALUE;
    static int[][] movement = {
        {1, 2}, 
        {1, -2}, 
        {-1, 2}, 
        {-1, -2}, 
        {2, 1}, 
        {2, -1}, 
        {-2, 1}, 
        {-2, -1}
    };
    
    public static int answer(int s, int d) { 
        Point src = new Point(s);
        Point dest = new Point(d);

        int increment = 0;
        ArrayList<Point> prevMoves = new ArrayList<Point>();

        recursion(src, dest, increment, prevMoves);

        return minMoves;
    }
    
    private static void recursion(Point src, Point dest,  int increment, ArrayList<Point> prevMoves) {
        if (src.equals(dest)) {
            minMoves = increment;
            System.out.println("minMoves updated to: " + minMoves);
        } else if (increment <= minMoves) {
            prevMoves.add(src);
            for (int i = 0; i < movement.length; i++) {
                Point move = src.translate(movement[i]);
                if (move.isValidPoint() && !prevMoves.contains(move)) {
                    System.out.println("Moving from point " + src + " to point " + move);
                    recursion(move, dest, increment + 1, prevMoves);
                }
            }
        }
    }

    public static void main(String[] args) {
        int destination = Integer.parseInt(args[0]);
        int source = Integer.parseInt(args[1]);
        int answer = answer(source, destination);
        System.out.println("final answer is: " + answer);
    }


}

class Point {
    int x;
    int y;

    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point (int place) {
        x = place % 8;
        y = place / 8;
    }

    public Point translate(int[] arr) {
        return new Point(x + arr[0], y + arr[1]);
    }

    public boolean isValidPoint() {
        return x < 8 && x >= 0 && y < 8 && y >= 0;
    }

    @Override
    public int hashCode() {
        return x + y * 8;
    }

    @Override
    public boolean equals(Object a) {
        if (a instanceof Point) {
            Point p = (Point)a;
            return p.x == x && p.y == y;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}




