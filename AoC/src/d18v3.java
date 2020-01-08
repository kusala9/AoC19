import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class d18v3
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
        if (mat[row][col] >= 'A' && mat[row][col] <= 'Z')
        {
            String dr = Character.toString((char)(mat[row][col])).toLowerCase();
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

    public static int nr=0;

    private static pear<Integer,ArrayList<String>> BFS4(String from,String to, int mat[][],HashMap<String,pear<Integer,Integer>> keys,HashSet<String> with,boolean trace)
    {
        pear<Integer,ArrayList<String>> ret = new pear<>(0,new ArrayList<String>());
        if (from.compareTo(to)==0) return ret;
        // construct a matrix to keep track of visited cells
        boolean[][] visited = new boolean[100][100];
        int steps = 0;
        // create an empty queue
        Queue<nd> q = new ArrayDeque<>();

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

        if (trace) System.out.println("  -> BFS4: Going from " + from + " to " + to + " with (" + mkX(with) + ")") ;

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
                    visited[i + row[k]][j + col[k]] = true;
                    if (mat[ni][nj]>='A' && mat[ni][nj]<='Z')
                    {
                        String dx = Character.toString((char)(mat[ni][nj]));
                        ret.second.add(dx);
                    }
                    q.add(new nd(i + row[k], j + col[k], dist + 1) );
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

    public static void main(String []arg)
    {
        int [][]mz = new int[100][100];
        HashMap<String,pear<Integer,Integer>> keys = new HashMap<>();
        HashMap<String,pear<Integer,Integer>> doors = new HashMap<>();

        System.out.println("hw");
        File file = new File("d18_mz_pt2.txt");
        int []ox = new int[4];
        int []oy = new int[4];
        int oc=0;
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
                            String s = Integer.toString(oc);
                            ox[oc]=i;
                            oy[oc++]=r;
                            mz[r][i] = '.';
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
        System.out.println("dun ix=" + mkX(ox) + " oy=" + mkX(oy) + " nkeys=" + keys.keySet().size());

        HashSet<String> withall = new HashSet<String> (keys.keySet());
        HashSet<String> gotK = new HashSet<>();
        String []srcs = new String[4];
        for (int i=0;i<ox.length;i++)
        {
            String c = Integer.toString(i);
            srcs[i] = c;
            gotK.add(c);
            withall.remove(c);
        }
        HashMap<String,Integer> cache = new HashMap<String,Integer>();
        withall.remove("@");
        int m = dor5(0,srcs,withall,gotK,mz,keys,cache,"");
        System.out.println ("=> "+ m);
        for (String s:mp.keySet())
        {
            System.out.println (s + "=" + mp.get(s));
        }
    }

    public static String mkX(int []v)
    {
        StringBuilder sb = new StringBuilder("");
        for (int s:v) sb.append(s + " " );
        return sb.toString();
    }

    public static HashMap<String,Integer> mp = new HashMap<>();

    public static int dor5(int lev,
                           String []srcs,
                           HashSet<String> remainingkeys,
                           HashSet<String> gotKeys,
                           int[][]mz,
                           HashMap<String,pear<Integer,Integer>> allkeys,
                           HashMap<String,Integer> cache,
                           String path)
    {
        int thresh=10;
        if (lev<thresh) System.out.println(ns(lev) + "DOR5: " + lev + ":(" + mkX(srcs)  + "): search for " + mkX(remainingkeys) + ": using " + mkX(gotKeys) + " path=" + path);


        if (remainingkeys.size()==0) return 0;

        int res=1000000;

        ArrayList<String> ks = new ArrayList(new TreeSet(gotKeys));
        String ck = mkX(srcs) + "," + mkX(ks);
        if (cache.containsKey(ck))
        {
            //System.out.println(ns(lev) + "CACHED=>" + ck + "=" + cache.get(ck));
            return cache.get(ck);
        }

        for (int i=0;i<srcs.length;i++)
        {
            //System.out.println(lev + "Processing Source -> " + i + "=" + srcs[i] + " (" + mkX(remainingkeys) + ")");
            String startfrom=srcs[i];


            boolean fnd=false;
            for (String t:remainingkeys)
            {
                HashSet<String> ns = new HashSet<>(remainingkeys);
                HashSet<String> ng = new HashSet<>(gotKeys);
                ns.remove(startfrom);
                ns.remove(t);
                ng.add(startfrom);
                ng.add(t);

                String []nsrcs = new String[srcs.length];
                for (int j=0;j<nsrcs.length;j++) nsrcs[j]=srcs[j];
                nsrcs[i]=t;

                pear<Integer, ArrayList<String>> st2 = BFS4(startfrom, t, mz, allkeys, gotKeys,false);

                if (st2.first>0)
                {
                    if (lev<thresh) System.out.println(ns(lev) + lev + ": " + i + ": REACHABLE: from " + startfrom + "  to  " + t + " = " + st2.first + " with " + mkX(gotKeys) +  " via (" + mkX(st2.second) + ")");
                    fnd=true;
                    int d = st2.first + dor5(lev+1,nsrcs,ns,ng,mz,allkeys,cache,path+startfrom);
                    if (d < res)
                    {
                        res=d;
                        mp.put(t,d);
                    }
                }
            }

            cache.put(ck,res);
        }
        return res;
    }
    public static String ns(int n)
    {
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<2*n;i++) sb.append(" ");
        return sb.toString();
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
