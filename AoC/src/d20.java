import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class d20
{
    // queue node used in BFS
    static class nd
    {
        int x, y, dist;
        nd(int x, int y, int dist)
        {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
        public String toString()
        {
            return this.x + "," + this.y;
        }
    };

    public static String nxt(int c,int r, int[][]mz)
    {
        for (int k = 0; k < 4; k++)
        {
            int cn = mz[r+row[k]][c+col[k]];
            if (cn>='A' || cn<='Z')
            {
                String s = Character.toString((char) cn);
                return s;
            }
        }
        return null;
    }

    public static nd nxtV(int c,int r, int[][]mz)
    {
        for (int k = 0; k < 4; k++)
        {
            int cn = mz[r+row[k]][c+col[k]];
            if (cn == '.') return new nd(r+row[k],c+col[k],0);
        }
        return null;
    }

    // what are the valid neighbours at a point....
    public static ArrayList<nd> neigbs(int c,int r, int[][]mz)
    {
        ArrayList<nd> ret = new ArrayList<>();
        for (int k = 0; k < 4; k++)
        {
            int cn = mz[r+row[k]][c+col[k]];
            if (cn == '.') ret.add(new nd(r+row[k],c+col[k],0));
            else if (cn>='A' || cn<='Z')
            {
                String c1 = Character.toString((char)cn);
                String c2 = nxt(r+row[k],c+col[k],mz);
                String add = (c1.compareTo(c2)<=0)?(c1+c2):(c2+c1);
                nd n1 = nxtV(r+row[k],c+col[k],mz);
                nd n2 = nxtV(r+row[k],c+col[k],mz);
                nd nv = (n1==null)?n2:n1;
                if (nv==null) System.out.println("Couldn't find a valid step next to " + add);
            }
        }
        return ret;
    }

    private static pear<Integer, ArrayList<String>> BFS4(String from, String to, int mat[][], HashMap<String,pear<Integer,Integer>> keys, boolean trace)
    {
        pear<Integer,ArrayList<String>> ret = new pear<>(0,new ArrayList<String>());
        if (from.compareTo(to)==0) return ret;
        // construct a matrix to keep track of visited cells
        boolean[][] visited = new boolean[100][100];
        int steps = 0;
        // create an empty queue
        Queue<d18v3.nd> q = new ArrayDeque<>();

        // get dest..
        if (!keys.containsKey(from) || !keys.containsKey(to))
        {
            System.out.println("ERROR: Can't find key. Currently have " + keys.keySet() + " Searching for " + from + "," + to );
        }
        pear<Integer,Integer> fm = keys.get(from);
        int i=fm.second;
        int j=fm.first;
        pear<Integer,Integer> dest = keys.get(to);
        int x=dest.second;
        int y=dest.first;

        if (trace) System.out.println("  -> BFS4: Going from " + from + " to " + to + " with ("  + ")") ;

        // mark source cell as visited and enqueue the source node
        visited[i][j] = true;
        q.add(new d18v3.nd(i, j, 0));

        // stores length of longest path from source to destination
        int min_dist = Integer.MAX_VALUE;

        // run till queue is not empty
        while (!q.isEmpty())
        {
            // pop front node from queue and process it
            d18v3.nd node = q.poll();

            // (i, j) represents current cell and dist stores its
            // minimum distance from the source
            i = node.x;
            j = node.y;
            int dist = node.dist;

            // if destination is found, update min_dist and stop
            if (i == x && j == y)
            {
                min_dist = dist;
                break;
            }

            // check for all 4 possible movements from current cell
            // and enqueue each valid movement
            for (int k = 0; k < 4; k++)
            {
                // check if it is possible to go to position
                // (i + row[k], j + col[k]) from current position
                if (isValid2(mat, visited, i + row[k], j + col[k]))
                {
                    // mark next cell as visited and enqueue it
                    int ni=i+row[k];
                    int nj=j+col[k];
                    visited[i + row[k]][j + col[k]] = true;
                    if (mat[ni][nj]>='A' && mat[ni][nj]<='Z')
                    {
                        String dx = Character.toString((char)(mat[ni][nj]));
                        ret.second.add(dx);
                    }
                    q.add(new d18v3.nd(i + row[k], j + col[k], dist + 1) );
                }
                else
                {
                    int ni=i+row[k];
                    int nj=j+col[k];
                }
            }
        }

        if (min_dist != Integer.MAX_VALUE)
        {
            steps+=min_dist;
        }
        else
        {
            ret.first=-1;
            return ret;
        }

        ret.first = steps;
        //System.out.println(steps);
        return ret;
    }

    private static final int row[] = { -1, 0, 0, 1 };
    private static final int col[] = { 0, -1, 1, 0 };

    private static boolean isValid2(int[][] mat, boolean[][] visited, int row, int col)
    {
        if (mat[row][col] >= 'A' && mat[row][col] <= 'Z')
        {
            return false;
        }
        else
        {
            return (row >= 0) && (row < 100) &&
                    (col >= 0) && (col < 100)
                    && (mat[row][col] == '.')
                    && !visited[row][col];
        }
    }

    public static void main(String []arg)
    {
        System.out.println("hw");
        int [][]mz = new int[100][100];
        HashMap<String,pear<Integer,Integer>> doors = new HashMap<>();

        System.out.println("hw");
        File file = new File("d20_mz_pt1.txt");
        int []ox = new int[4];
        int []oy = new int[4];
        int oc=0;
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            int r=0,c=0;
            while ((line = br.readLine()) != null)
            {
                System.out.println("-->"  + line + "<--");
                for (int i=0;i<line.length();i++)
                {
                    char ch = line.charAt(i);
                    mz[r][i] = ch;
                }
                r++;
            }
        }catch (Exception e){e.printStackTrace();}

        // have maze. Parse out doors...
        for (int r=0;r<100;r++)
        {
            for (int c=0;c<100;c++)
            {
                int cc = mz[r][c];
                if (cc<'Z' && cc>'A')
                {
                    System.out.println("found -> "+ cc);
                }
            }
        }
    }


}
