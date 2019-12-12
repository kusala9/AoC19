import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
/*
 234   .    .    .   248  -x-   .   258  263  -x-  267   .   273   .    .    .    .    .    .    .   009   .   017  021   .    .   031  036   .   042   .
 232   .   239  -x-   .   250   .    .   261   .    .   269   .    .    .   280  287   .    .   005  -x-   .   018  022   .   028   .    .    .    .    .
 229   .    .   240  244  -x-  253  -x-   .   -x-   .    .    .    .    .    .   286   .    .   006   .    .    .    .   026   .   035  039  043   .   051
  .    .    .   236   .   245   .   256   .    .    .   268   .    .   276   .    .    .    .    .   012   .    .   024   .    .   038   .   045   .    .
  .   -x-   .    .    .    .    .   252  -x-   .    .   -x-   .    .    .   279   .    .   001   .   -x-   .    .    .   029  034   .    .    .    .    .
 -x-  220   .    .    .    .    .   249   .    .    .    .    .    .    .    .    .    .   002  007   .    .   023   .    .    .    .    .    .   055  059
  .   -x-  221   .    .   -x-  242   .   251   .   260   .    .    .    .   278  285   .    .   -x-  015   .    .    .    .    .    .    .   053   .    .
 217   .   -x-  222   .   -x-  237   .    .    .    .   264  266  271   .    .   284   .    .    .   016  020   .    .    .   040  046   .    .    .    .
 212  -x-   .   -x-   .   228   .    .    .    .    .    .    .    .    .    .    .    .   003  010   .    .   027   .    .    .   050  -x-   .    .   064
 209  211  215   .    .   223  230   .   241  246  255   .    .    .    .    .   283   .   004  011   .    .    .   037  041  047  054   .    .    .    .
  .    .    .   -x-  218   .    .   231   .    .    .   -x-  262   .    .    .   282  -x-   .    .   019   .   032   .    .   -x-   .    .    .   -x-   .
  .    .   207   .   213   .   -x-  224   .   238   .   254   .   265  270  275  281  -x-   .   014   .    .    .    .   049  057  062  063   .   066   .
 202   .    .    .    .    .    .   -x-  225  233   .   247  257   .    .    .    .   -x-   .    .    .   -x-   .    .    .   -x-   .    .    .   -x-  070
  .    .    .    .    .   208   .    .    .   226   .   -x-   .   259   .   274   .    .    .    .   025   .    .    .   060   .    .    .    .    .    .
 197   .    .   201   .   -x-   .   210   .    .   227   .    .    .    .   272   .    .   008   .    .    .   048   .    .   065   .    .   071  -x-   .
 195   .    .    .    .    .   204   .    .   216  -x-   .    .    .    .    .   277   .    .    .   033  044  056   .    .   067   .    .   074   .   077
  .    .    .    .    .   -x-  199  203  -x-   .   214   .    .   243   .    .    .   -x-  013   .    .   052   .    .   068   .    .   075   .    .   079
  .    .   -x-   .    .    .    .    .   200   .   206   .    .    .    .    .    .    .    .   030   .   061   .   069  072   .    .   -x-  080  083  085
  .   -x-   .    .    .    .    .   194  196   .    .   205   .   -x-  235   .    .    .    .    .   058   .    .    .   076  -x-  081   .    .    .    .
 185  187  -x-   .   189  190  191   .    .    .    .   -x-   .    .   -x-   .    .   000   .    .    .    .    .   078  082  086  087  088   .    .    .
 181   .    .   183  184   .   186  -x-   .    .   192  -x-   .   198   .   -x-   .    .    .    .   073   .   084   .    .    .    .    .    .   089   .
  .   178   .    .    .    .   179   .   180  182   .    .   188   .   193   .   219   .    .    .    .    .    .    .   090  091   .    .    .    .    .
 -x-  -x-   .   -x-  -x-   .    .    .   -x-   .    .    .   177   .    .    .    .   -O-  092  -x-   .   -x-   .    .    .    .   -x-   .    .    .    .
  .    .    .    .    .   176   .   175  174  173  172  170  167   .   160   .   142   .    .   106   .    .   096   .   094   .    .    .    .    .   093
  .   -x-   .    .   171   .   169  -x-   .   164   .    .    .    .   144   .   135  128   .    .   110   .   103   .    .    .   097  -x-   .    .   095
  .   168  -x-   .   166  -x-  163   .    .   158   .    .   146   .    .   138   .   -x-  124  121   .    .    .   -x-  104  102  101  099   .   098   .
 165   .    .    .   161   .    .    .    .    .    .    .    .    .    .    .   130   .    .    .    .    .   113   .    .    .    .    .    .    .   100
 162   .    .    .    .   156  153   .   149   .   143   .    .   140  136   .    .    .   126   .    .   120  116  114  111   .    .    .   105   .    .
 159   .   157  155  152  150   .    .    .    .    .    .   141  -x-  -x-  132  129   .    .    .    .    .   119   .   115  112   .    .   108   .    .
  .   154   .    .    .   147   .    .    .    .   -x-   .    .    .   134  131   .    .   127  125   .   123   .   118   .    .    .    .    .    .   107
 151   .    .   148  145  -x-   .    .    .    .    .   139  137   .   133   .    .    .    .    .    .    .   122   .   117  -x-   .    .    .   -x-  109

 */

public class d10
{
    public static int getQ(pear<Integer,Integer> st,pear<Integer,Integer> en)
    {
        int dx = en.first - st.first;
        int dy = en.second - st.second;
        if (dx>=0)
        {
            if (dy >= 0)
                return 2;
            else
                return 1;
        }
        else
        {
            if (dy>=0)
            {
                return 3;
            }
            else
            {
                return 4;
            }
        }
    }
    private static HashMap<Double,pear<Integer,Integer>> seq = new HashMap<Double,pear<Integer,Integer>>();
    public static String getA(pear<Integer,Integer> st,pear<Integer,Integer> en)
    {
        int q = getQ(st,en);
        int dx = en.first - st.first;
        int dy = en.second - st.second;
        double d =  Math.atan2(dy,dx);
        //d = (Math.PI) - d;
        seq.put(d,en);

        int id =  (int)  Math.round(100*d);
        id+=360;
        //id=id%360;
        return String.format("%03d",id);
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

        int cnt=0;
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
                        cnt++;
                        if (p.equals(trace)) System.out.print(" -O- ");
                        else if (tracemap.get(p)==true)
                        {
                            //System.out.print(String.format(" %03d ",cnt));
                            //System.out.print("  " + getQ(trace,p)+ "  ");
                            System.out.print(" " + getA(trace,p)+ " ");

                        }
                        else System.out.print(" -x- ");
                    }
                    else
                    {
                        System.out.print("  _  ");
                    }
                    m3.put(p,true);
                    tot++;
                }
                else
                {
                    System.out.print("  .  ");
                }
            }
            System.out.println();
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
                        cnt++;
                        if (p.equals(trace)) System.out.print(" -O- ");
                        else if (tracemap.get(p)==true)
                        {
                            //System.out.print(String.format(" %03d ",cnt));
                            //System.out.print("  " + getQ(trace,p)+ "  ");
                            System.out.print(" " + getA(trace,p)+ " ");

                        }
                        else System.out.print(" -x- ");
                    }
                    else
                    {
                        System.out.print("  _  ");
                    }
                    m3.put(p,true);
                    tot++;
                }
                else
                {
                    System.out.print("  .  ");
                }
            }
            System.out.println();
        }

        SortedSet<Double> keys = new TreeSet<>(seq.keySet());
        HashMap<Integer,pear<Integer,Integer>> ranked = new HashMap<Integer,pear<Integer,Integer>>();
        HashMap<pear<Integer,Integer>,Integer> iranked = new HashMap<pear<Integer,Integer>,Integer>();
        int rnk=0;
        for (Double key : keys)
        {
            pear<Integer, Integer> value = seq.get(key);
            ranked.put(rnk,value);
            iranked.put(value,rnk);
            rnk++;
        }

        System.out.println("===============================================");

        int acnt=0;
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
                        cnt++;
                        if (p.equals(trace)) System.out.print(" -O- ");
                        else if (tracemap.get(p)==true)
                        {
                            //System.out.print(String.format(" %03d ",cnt));
                            //System.out.print(" " + getA(trace,p)+ " ");
                            int frank = iranked.get(p);
                            frank = frank-110;
                            if (getQ(trace,p)==4) frank+=(110+178);
                            String rk = String.format(" %03d ",frank);
                            System.out.print(rk);
                            acnt++;

                        }
                        else System.out.print(" -x- ");
                    }
                    else
                    {
                        System.out.print("  _  ");
                    }
                    m3.put(p,true);
                    tot++;
                }
                else
                {
                    System.out.print("  .  ");
                }
            }
            System.out.println();
        }

    }
    public static void pr(String s)
    {
        System.out.print(String.format(s, "%05"));
    }
}
