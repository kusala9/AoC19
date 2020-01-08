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
            //String dr = String.valueOf(mat[row][col]).toLowerCase();
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

    private static boolean isValid(int mat[][], boolean visited[][],int row, int col)
    {
        //System.out.println("IV: " + row + "," + col + " locn=" + mat[row][col] + " v=" + visited[row][col]);
        return (row >= 0) && (row < 100) &&
                (col >= 0) && (col < 100)
                && (mat[row][col] == '.')
                && !visited[row][col];
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
                    //System.out.println("at -> " + ni + "," + nj + " dist=" + dist );
                    visited[i + row[k]][j + col[k]] = true;
                    if (mat[ni][nj]>='A' && mat[ni][nj]<='Z')
                    {
                        //String dx = String.valueOf(mat[ni][nj]);
                        String dx = Character.toString((char)(mat[ni][nj]));
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
            //System.out.println(-1);
            return ret;
            //System.exit(1);
        }

        ret.first = steps;
        //System.out.println(steps);
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
        HashMap<String,Integer> cache = new HashMap<String,Integer>();

        int nsteps=0;
        String st="x";
        String []xt = {"l","x","y","e","n","g",",a","b","w","j","z","t","f"};
        HashSet<String> xtest = new HashSet<String>();
        for (String x:xt) xtest.add(x);
        String []got = {"h","m","o","p","q","r","d","i","k","s","u","v","c"};
        HashSet<String> xgot = new HashSet<String>();
        for (String x:got) xgot.add(x);
//        for (String s:xtest)
//        {
//            // y,x,c,u,s,k,i,d,q,p
//            // from y can reach t and f
//            // h,m,o,p,q,r,d,i,k,s,u,v,c,   l,x,y,e,n,g,a,b,w,j,z,t,f
//
//            pear<Integer, ArrayList<String>> st2 = BFS4("y", s, mz, keys, xgot,true);
//            if (st2.first>0)
//            {
//                System.out.println(s + "->" + st2.first + " with " + mkX(st2.second));
//            }
//            else
//            {
//                System.out.println(s);
//            }
//        }


        withall.remove("@");

        int m = dor4(0,"@",withall,new HashSet<>(),mz,keys,cache,"");
        System.out.println ("=> "+ m);
        for (String s:mp.keySet())
        {
            System.out.println (s + "=" + mp.get(s));
        }


//        StringBuilder sb= new StringBuilder();
//        while (withall.size()>0)
//        {
//            withall.remove(st);
//            int min= Integer.MAX_VALUE;
//            nx="";
//            for (String t:withall)
//            {
//                pear<Integer, ArrayList<String>> st2 = BFS4(st, t, mz, keys, withsome);
//                if (st2.first>0)
//                {
//                    System.out.println("from " + st + "  to  " + t + " = " + st2.first + " with " + mkX(withsome) +  " via (" + mkX(st2.second) + ")");
//
//                    // cache values...
//                    ArrayList<String> ks = new ArrayList(new TreeSet(withsome));
//                    //cache.put(mkX(st+"," + t,st2.second),st2.first);
//                    if (st2.first<min)
//                    {
//                        min=st2.first;
//                        nx=t;
//                    }
//                }
//            }
//            if (withall.contains(nx))
//            {
//                st=nx;
//                nsteps+=min;
//                System.out.println("Selecting " + nx + " (" + min + ") path= " + sb.toString() + " n=" + nsteps);
//                sb.append(nx);
//                withsome.add(nx);
//            }
//            else
//            {
//                System.out.println("done...");
//                break;
//            }
//        }
        //keys.remove("@");


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

    public static String mkX(String k,ArrayList<String> v)
    {
        StringBuilder sb = new StringBuilder(k);
        for (String s:v) sb.append(s);
        return sb.toString();
    }
    /*
    from reddit....

    distanceToCollectKeys(currentKey, keys, cache):

    if keys is empty:
        return 0

    cacheKey := (currentKey, keys)
    if cache contains cacheKey:
        return cache[cacheKey]

    result := infinity
    foreach key in reachable(keys):
       d := distance(currentKey, key) + distanceToCollectKeys(key, keys - key, cache)
       result := min(result, d)

    cache[cacheKey] := result
    return result

     */
    public static HashMap<String,Integer> mp = new HashMap<String,Integer>();
    public static int dor3(int lev,String startfrom,
                           HashSet<String> remainingkeys,
                           HashSet<String> gotKeys,
                           int[][]mz,
                           HashMap<String,pear<Integer,Integer>> allkeys,
                           HashMap<String,Integer> cache,
                           String path)
    {
        int thresh=12;
        if (lev>thresh) System.out.println(lev + ":" + startfrom + ": search for " + mkX(remainingkeys) + ": using " + mkX(gotKeys) + " path=" + path);

        if (remainingkeys.size()==1)
        {
            if (lev<thresh) System.out.println(lev + ": Reached end" );
            return 0;
        }

        int res=1000000;

        ArrayList<String> ks = new ArrayList(new TreeSet(gotKeys));
        String ck = startfrom + "," + mkX(ks);
        if (cache.containsKey(ck))
        {
            if (lev>thresh) System.out.println(lev + ": Cached (" + ck + ")=" + cache.get(ck));
            return cache.get(ck);
        }

        boolean fnd=false;
        for (String t:remainingkeys)
        {
            if (t.compareTo(startfrom)==0) continue;
            HashSet<String> ns = new HashSet<>(remainingkeys);
            HashSet<String> ng = new HashSet<>(gotKeys);
            ns.remove(startfrom);
            ns.remove(t);
            ng.add(startfrom);
            ng.add(t);
            pear<Integer, ArrayList<String>> st2 = BFS4(startfrom, t, mz, allkeys, gotKeys,(lev>thresh)?true:false);
            if (lev>thresh) System.out.println(lev + ":" + startfrom + ": Examining " + t + " =" + st2.first + " (" + mkX(st2.second) + ")");
            if (st2.first>0)
            {
                //if (lev<thresh) System.out.print("(" + t + ")");
                if (lev>thresh) System.out.println(lev + ": REACHABLE: from " + startfrom + "  to  " + t + " = " + st2.first + " with " + mkX(gotKeys) +  " via (" + mkX(st2.second) + ")");
                fnd=true;
                // t is reachable...
                int d = st2.first + dor3(lev+1,t,ns,ng,mz,allkeys,cache,path+startfrom);
                //if (lev<thresh) System.out.println(lev + ": finished  -> " + d + " (" + t +  ")");
                if (d < res)
                {
                    if (lev>thresh) System.out.println(" New m=(" + d + " (" + t +  ")) path=" + path);
                    res=d;
                    mp.put(t,d);
                }
            }
            else
            {
                //if (lev<thresh) System.out.print(t);
            }
        }
        cache.put(ck,res);
        if (!fnd) System.out.println(lev + ":" + startfrom + " no reachable vertices from " + startfrom + " with " + mkX(gotKeys) + " rem=" + mkX(remainingkeys));
        if (lev>thresh) System.out.println(lev + ": Finished " + startfrom + " path=" + path + " rem=" + mkX(remainingkeys) + ", returning -> " + res);
        return res;
    }

    // h,m,o,p,q,r,d,i,k,s,u,v,c,   l,x,y,e,n,g,a,b,w,j,z,t,f
    public static int dor4(int lev,String startfrom,
                           HashSet<String> remainingkeys,
                           HashSet<String> gotKeys,
                           int[][]mz,
                           HashMap<String,pear<Integer,Integer>> allkeys,
                           HashMap<String,Integer> cache,
                           String path)
    {
        int thresh=13;
        if (lev>thresh) System.out.println("DOR4: " + lev + ":" + startfrom + ": search for " + mkX(remainingkeys) + ": using " + mkX(gotKeys) + " path=" + path);

        if (remainingkeys.size()==0)
        {
            if (lev<thresh) System.out.println(lev + ": Reached end" );
            return 0;
        }

        int res=1000000;

        ArrayList<String> ks = new ArrayList(new TreeSet(gotKeys));
        String ck = startfrom + "," + mkX(ks);
        if (cache.containsKey(ck))
        {
            if (lev>thresh) System.out.println(lev + ": Cached (" + ck + ") returning " + cache.get(ck));
            return cache.get(ck);
        }

        boolean fnd=false;
        for (String t:remainingkeys)
        {
            HashSet<String> ns = new HashSet<>(remainingkeys);
            HashSet<String> ng = new HashSet<>(gotKeys);
            ns.remove(startfrom);
            ns.remove(t);
            ng.add(startfrom);
            ng.add(t);
            pear<Integer, ArrayList<String>> st2 = BFS4(startfrom, t, mz, allkeys, gotKeys,false);
            if (lev>thresh) System.out.println(lev + ":" + startfrom + ": Examining " + t + " =" + st2.first + " (" + mkX(st2.second) + ") min=" + res + " path=" + path);
            if (st2.first>0)
            {
                if (lev>thresh) System.out.println(lev + ": REACHABLE: from " + startfrom + "  to  " + t + " = " + st2.first + " with " + mkX(gotKeys) +  " via (" + mkX(st2.second) + ")");
                fnd=true;
                int d = st2.first + dor4(lev+1,t,ns,ng,mz,allkeys,cache,path+startfrom);
                if (d < res)
                {
                    if (lev>thresh) System.out.println(" New m=(" + d + " (" + t +  ")) path=" + path);
                    res=d;
                    mp.put(t,d);
                }
            }
            else
            {
                //if (lev<thresh) System.out.print(t);
            }
        }
        if (lev>thresh) System.out.println(lev + " Caching -> " + ck + "=" + res);
        cache.put(ck,res);
        if (!fnd) System.out.println(lev + ":" + startfrom + " no reachable vertices from " + startfrom + " with " + mkX(gotKeys) + " rem=" + mkX(remainingkeys));
        if (lev>thresh) System.out.println(lev + ": Finished " + startfrom + " path=" + path + " rem=" + mkX(remainingkeys) + ", returning -> " + res);
        return res;
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
