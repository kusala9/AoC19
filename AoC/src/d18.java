import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class d18
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
    };

    private static final int row[] = { -1, 0, 0, 1 };
    private static final int col[] = { 0, -1, 1, 0 };

    private static boolean isValid2(int[][] mat, boolean[][] visited, int row, int col, HashSet<String> with)
    {
        //System.out.println("IV: " + row + "," + col + " locn=" + mat[row][col] + " v=" + visited[row][col]);
        if (mat[row][col] >= 'A' && mat[row][col] <= 'Z')
        {
            String dr = Character.toString(mat[row][col]).toLowerCase();
            if (with.contains(dr))
            {
                return (row >= 0) && (row < 100) &&
                    (col >= 0) && (col < 100)
                    && !visited[row][col];
            }
            else return false;
        }
        else
        {
            return (row >= 0) && (row < 100) &&
                    (col >= 0) && (col < 100)
                    && (mat[row][col] == '.')
                    && !visited[row][col];
        }
    }

    private static boolean isValid(int mat[][], boolean visited[][],int row, int col)
    {
        //System.out.println("IV: " + row + "," + col + " locn=" + mat[row][col] + " v=" + visited[row][col]);
        return (row >= 0) && (row < 100) &&
                (col >= 0) && (col < 100)
                && (mat[row][col] == '.')
                && !visited[row][col];
    }
    public static int nr=0;

    private static pear<Integer,ArrayList<String>> BFS4(String from,String to, int mat[][],HashMap<String,pear<Integer,Integer>> keys,HashSet<String> with)
    {
        pear<Integer,ArrayList<String>> ret = new pear<>(0,new ArrayList<String>());
        if (from.compareTo(to)==0) return ret;
        // construct a matrix to keep track of visited cells
        boolean[][] visited = new boolean[100][100];
        int steps = 0;

        // create an empty queue
        Queue<nd> q = new ArrayDeque<>();

        // get dest..
        pear<Integer,Integer> fm = keys.get(from);
        int i=fm.second;
        int j=fm.first;
        pear<Integer,Integer> dest = keys.get(to);
        int x=dest.second;
        int y=dest.first;

        //System.out.println("BFS4: Going from " + i + "," + j + " to " + x +"," + y + "=" + fd) ;

        // mark source cell as visited and enqueue the source node
        visited[i][j] = true;
        q.add(new nd(i, j, 0));

        // stores length of longest path from source to destination
        int min_dist = Integer.MAX_VALUE;

        // run till queue is not empty
        while (!q.isEmpty())
        {
            // pop front node from queue and process it
            nd node = q.poll();

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
                if (isValid2(mat, visited, i + row[k], j + col[k],with))
                {
                    // mark next cell as visited and enqueue it
                    int ni=i+row[k];
                    int nj=j+col[k];
                    //System.out.println("at -> " + ni + "," + nj + " dist=" + dist );
                    visited[i + row[k]][j + col[k]] = true;
                    if (mat[ni][nj]>='A' && mat[ni][nj]<='Z')
                    {
                        String dx = Character.toString(mat[ni][nj]);
                        ret.second.add(dx);
                        //System.out.println("Door-> " + dx);
                    }
                    q.add(new nd(i + row[k], j + col[k], dist + 1) );
                }
                else
                {
                    int ni=i+row[k];
                    int nj=j+col[k];
                    //System.out.println("No path to " + ni + "," + nj + " from " + i + "," + j);
                }
            }
        }

        if (min_dist != Integer.MAX_VALUE)
        {
            //System.out.println("Shortest path = " + min_dist);
            steps+=min_dist;
        }
        else
        {
            //System.out.println("(" + x + "," + y + ") Can't be reached from source");
            ret.first=-1;
            return ret;
            //System.exit(1);
        }

        ret.first = steps;
        return ret;
    }

    private static int BFS2(String []s,int mat[][], int i, int j,HashMap<String,pear<Integer,Integer>> keys,HashMap<String,pear<Integer,Integer>> doors)
    {
        // construct a matrix to keep track of visited cells
        boolean[][] visited = new boolean[100][100];
        int steps = 0;

        // create an empty queue
        Queue<nd> q = new ArrayDeque<>();

        for (int ind=0;ind<s.length;ind++)
        {
            String fd = s[ind];
            // get dest..
            pear<Integer,Integer> dest = keys.get(fd);
            int x=dest.second;
            int y=dest.first;
            // clear visited...
            for (int cx=0;cx<100;cx++)
            {
                for (int cy=0;cy<100;cy++)
                {
                    visited[cy][cx] = false;
                }
            }
            q.clear();
            //System.out.println("Going from " + i + "," + j + " to " + x +"," + y + "=" + fd) ;

            // mark source cell as visited and enqueue the source node
            visited[i][j] = true;
            q.add(new nd(i, j, 0));

            // stores length of longest path from source to destination
            int min_dist = Integer.MAX_VALUE;

            // run till queue is not empty
            while (!q.isEmpty())
            {
                // pop front node from queue and process it
                nd node = q.poll();

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
                    if (isValid(mat, visited, i + row[k], j + col[k]))
                    {
                        // mark next cell as visited and enqueue it
                        int ni=i+row[k];
                        int nj=j+col[k];
                        //System.out.println("at -> " + ni + "," + nj);
                        visited[i + row[k]][j + col[k]] = true;
                        q.add(new nd(i + row[k], j + col[k], dist + 1));
                    }
                    else
                    {
                        int ni=i+row[k];
                        int nj=j+col[k];
                        //System.out.println("No path to " + ni + "," + nj + " from " + i + "," + j);
                    }
                }
            }

            if (min_dist != Integer.MAX_VALUE)
            {
                //System.out.println("Shortest path = " + min_dist);
                String dn = fd.toUpperCase();
                if (doors.containsKey(dn))
                {
                    //System.out.println("Unlocking door -> " + dn);
                    int dx = doors.get(dn).first;
                    int dy = doors.get(dn).second;
                    mat[dy][dx] = '.';
                }
                steps+=min_dist;

                // we're only interested in steps which are less than the minimum....
                if (steps>mins) return 0;
                //System.out.println("Total Steps=" + steps);
                //pr(mat,i,j);
            }
            else
            {
                //System.out.println("(" + x + "," + y + ") Can't be reached from source");
                return -1;
                //System.exit(1);
            }
        }
        return steps;
    }

    public static void main(String []arg)
    {
        int [][]mz = new int[100][100];
        HashMap<String,pear<Integer,Integer>> keys = new HashMap<>();
        HashMap<String,pear<Integer,Integer>> doors = new HashMap<>();
        System.out.println("hw");
        File file = new File("d18_mz.txt");
        int ox=0,oy=0;
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            int r=0,c=0;
            while ((line = br.readLine()) != null)
            {
                nr++;
                System.out.println("-->"  + line + "<--");
                for (int i=0;i<line.length();i++)
                {
                    char ch = line.charAt(i);
                    mz[r][i] = ch;
                    switch (ch)
                    {
                        case '@':{
                            ox=i;
                            oy=r;
                            mz[r][i] = '.';
                            String s = Character.toString(ch);
                            keys.put(s,new pear<>(i,r));
                            break;
                        }
                        case '#':{break;}
                        case '.':{break;}
                        default:{
                            String s = Character.toString(ch);
                            if (Character.isLowerCase(ch)) {
                                keys.put(s,new pear<>(i,r));
                                mz[r][i] = '.'; // keys are valid sqs.
                            }
                            else if (Character.isUpperCase(ch)) doors.put(s,new pear<>(i,r));
                        }
                    }
                }
                r++;
            }
        }catch (Exception e){e.printStackTrace();}
        System.out.println("dun ix=" + ox + " oy=" + oy + " nkeys=" + keys.keySet().size());



        //
//        String from="@";
//        String to="r";
//        pear<Integer, ArrayList<String>> st2 = BFS4(from, to, mz, keys, withall);
//        System.out.println("from " + from + " to " + to + " = " + st2.first + " via (" + mkX(st2.second) + ")");

//        for (String to:keys.keySet())
//        {
//            pear<Integer, ArrayList<String>> st2 = BFS4(from, to, mz, keys, withall);
//            System.out.println("from " + from + " to " + to + " = " + st2.first + " via (" + mkX(st2.second) + ")");
//        }
//
//        ArrayList<String> in = new ArrayList<String>();
//        ArrayList<String> i2 = new ArrayList<String>();
//

        HashSet<String> withnone = new HashSet<>();
        HashSet<String> withall = new HashSet<String> (keys.keySet());
        HashSet<String> withsome = new HashSet<String> ();

        int nsteps=0;
        String st="@";
        String nx="";
        StringBuilder sb= new StringBuilder();
        while (withall.size()>0)
        {
            withall.remove(st);
            int min= Integer.MAX_VALUE;
            nx="";
            for (String t:withall)
            {
                pear<Integer, ArrayList<String>> st2 = BFS4(st, t, mz, keys, withsome);
                if (st2.first>0)
                {
                    System.out.println("from " + st + "  to  " + t + " = " + st2.first + " with " + mkX(withsome) +  " via (" + mkX(st2.second) + ")");
                    if (st2.first<min)
                    {
                        min=st2.first;
                        nx=t;
                    }
                }
            }
            if (withall.contains(nx))
            {
                st=nx;
                nsteps+=min;
                System.out.println("Selecting " + nx + " (" + min + ") path= " + sb.toString() + " n=" + nsteps);
                sb.append(nx);
                withsome.add(nx);
            }
            else break;
        }

        //        withsome.add("r");
//        withsome.add("h");
//        for (String s:keys.keySet())
//        {
//            for (String t:keys.keySet())
//            {
//                pear<Integer, ArrayList<String>> st2 = BFS4(s, t, mz, keys, withsome);
//                if (st2.first>0) System.out.println("from " + s + "  to  " + t + " = " + st2.first + " via (" + mkX(st2.second) + ")");
//            }
//        }
    }

    public static String mkX(HashSet<String> v)
    {
        StringBuilder sb = new StringBuilder();
        for (String s:v)
        {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String mkX(ArrayList<String> v)
    {
        StringBuilder sb = new StringBuilder();
        for (String s:v)
        {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void doR(String []bi,int [][]mz,int oy,int ox,Set<String> ktt,HashMap<String,pear<Integer,Integer>> keys, HashMap<String,pear<Integer,Integer>> doors)
    {
        String []inp;
        if (ktt.size()==0) return;
        for (String s:ktt)
        {
            Set<String> ns = new HashSet<String>(ktt);
            ns.remove(s);

            pear<Integer,Integer> d = new pear<Integer,Integer>(0,0);
            pear<Integer,Integer> k = keys.get(s);
            if (doors.containsKey(s.toUpperCase())) d = doors.get(s.toUpperCase());

            // expand the list of keys by one.
            int len = bi.length+1;
            inp = new String [len];
            for (int ind=0;ind<bi.length;ind++) inp[ind]=bi[ind];
            inp[bi.length]=s;

            int st = BFS2(inp,mz,oy,ox,keys,doors);

            if (st>0)
            {
                if (st<mins && inp.length==26)
                {
                    mins=st;
                    minss=mkX(inp);
                    System.out.println("NEWMIN=" + mins + " s=" + minss);
                }
                System.out.println(bi.length + ":" + ktt.size() + " Processed key(s)=" + mkX(inp) + " steps=" + st + " min=" + mins + ":" + minss);
                doR(inp,mz,oy,ox,ns,keys,doors);
            }
            else
            {
                //System.out.println(bi.length  + ": SKIP: key=" + mkX(inp) + " x=" + k.first + " y=" + k.second + " dx=" + d.first + " dy=" + d.second + " steps=" + st + " min=" + mins);
            }
        }
    }

    public static int mins = 6000;   // my best estimate (earlier try)....

    public static String minss = "";

    public static String mkX(String []v)
    {
        String ret = "";
        for (int i=0;i<v.length;i++)
        {
            if (i>0) ret +=",";
            ret+=v[i];
        }
        return ret;
    }

    public static void pr(int [][]m,int i,int j)
    {
        for (int r=0;r<nr;r++)
        {
            System.out.print(String.format("%02d",r)+":");
            for (int c=0;c<90;c++)
            {
                if (r==i && c==j)
                {
                    System.out.print("@");
                }
                else
                {
                    switch (m[r][c])
                    {
                        case '.': {System.out.print(String.format("%c",m[r][c]));break;}
                        case '#': {System.out.print(String.format("%c",m[r][c]));break;}
                        case 0: {System.out.print(" ");break;}
                        default:{System.out.print(String.format("%c",m[r][c]));break;}
                    }
                }
            }
            System.out.println ("");
        }
    }
}
