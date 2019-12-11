import java.util.ArrayList;
import java.util.HashMap;

public class d10
{
    public static int getQ(pear<Integer,Integer> st,pear<Integer,Integer> en)
    {
        int dx = en.first - st.first;
        int dy = en.second - st.second;
        if (dx>=0)
        {
            if (dy >= 0)
                return 1;
            else
                return 4;
        }
        else
        {
            if (dy>=0)
            {
                return 3;
            }
            else
            {
                return 2;
            }
        }
    }
    public static int gcf(int no1,int no2)
    {
        int n1 = Math.abs(no1);
        int n2 = Math.abs(no2);
        if (n1==0) return n2;
        if (n2==0) return n1;
        int gcd = 1;
        for (int i = 1; i <= n1 && i <= n2; i++)
        {
            if (n1 % i == 0 && n2 % i == 0)
            {
                gcd = i;
            }
        }
        return gcd;
    }
    public static boolean isM(pear<Integer,Integer> e1,pear<Integer,Integer> e2,HashMap<pear<Integer,Integer>,Boolean> m3)
    {
        boolean ret = false;

        int dx = e2.first - e1.first;
        int dy = e2.second - e1.second;
        if (e1.equals(e2)) return true;
//        int g = 0;
//        if (dx==0||dy==0) g=1;
        int g = gcf(dx,dy);

        if ( (dx!=0 && dy!=0 ) && (g ==1))
        {
            return true;
        }

        int incx = dx/g;
        int incy = dy/g;

        // go from e1 to e2.

        while (true)
        {
            pear<Integer,Integer> e3 = new pear<Integer,Integer>(e1.first+incx,e1.second+incy);
            if (e3.equals(e2)) break;
            if (m3.containsKey(e3))
            {
                return false;
            }
            e1 = e3;
        }
        return true;
    }
    public static void main(String []a)
    {
        ArrayList<String> m = d10d.getd5();
        pear<Integer,Integer> p = new pear<Integer,Integer>(3,4);
        HashMap<String,Integer> m2 = new HashMap<>();
        HashMap<pear<Integer,Integer>,Boolean> m3 = new HashMap<>();

        int tot=0;
        for (int i=0;i<m.size();i++)
        {
            String s = m.get(i);
            System.out.println("->" + s);
            for (int j=0;j<s.length();j++)
            {
                if (s.charAt(j)=='#')
                {
                    //System.out.println("A at(" + i + "," + j + ")");
                    p = new pear<>(j,i);
                    m3.put(p,true);
                    tot++;
                }
            }
        }

        int max=0;
        pear<Integer,Integer> trace = new pear<Integer,Integer>(17,22);
        HashMap<pear<Integer,Integer>,Boolean> tracemap = new HashMap<>();

        for (pear<Integer,Integer> e1: m3.keySet())
        {
            int cnt=0;
            for (pear<Integer,Integer> e2: m3.keySet())
            {
                if (isM(e1,e2,m3))
                {
                    //System.out.println(e1 + "Match-" + e2);
                    if (e1.equals(trace)) tracemap.put(e2,true);
                    cnt++;
                }
                else
                {
                    //System.out.println(e1 + " Not match-" + e2);
                    if (e1.equals(trace)) tracemap.put(e2,false);
                }
            }
            if (cnt>=max)
            {
                System.out.println("New Max = " + e1 + "=" + cnt);
                max=cnt;
            }
        }

        for (int i=0;i<m.size();i++)
        {
            String s = m.get(i);
            for (int j=0;j<s.length();j++)
            {
                if (s.charAt(j)=='#')
                {
                    p = new pear<>(j,i);
                    if (tracemap.containsKey(p))
                    {
                        if (p.equals(trace)) System.out.print("O");
                        else if (tracemap.get(p)==true)
                        {
                            System.out.print(getQ(trace,p));
                        }
                        else System.out.print("x");
                    }
                    else
                    {
                        System.out.print("_");
                    }
                    m3.put(p,true);
                    tot++;
                }
                else
                {
                    System.out.print(".");
                }
            }
            System.out.println();
        }


    }
}
