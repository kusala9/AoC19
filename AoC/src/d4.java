/*
   DOCO:  
*/

import java.util.ArrayList;

public class d4
{
    public pear<ArrayList<Integer>,ArrayList<Integer>> nDubz(String s)
    {


        pear<ArrayList<Integer>, ArrayList<Integer>> ret = null;
        ret.first = new ArrayList<Integer>();
        ret.second = new ArrayList<Integer>();


        return ret;
    }
    public static int rep(String s)
    {
        int dbl=-1;
        for (int i=0;i<s.length()-1;i++)
        {
            String thiscar = s.substring(i,i+1);
            String nextcar = s.substring(i+1,i+2);
            int comp = nextcar.compareTo(thiscar);
            //System.out.println("[" + s + "." + i + "] :" + thiscar + "," +nextcar);
            if (comp==0) dbl=i;
            if (comp < 0)
            {
                //System.out.println("   ==: " + thiscar + "," + nextcar);
                return -1;
            }
        }
        //System.out.println("[D:" + s + "]");
        return dbl;
    }

    public static boolean chktriples(String s)
    {
        boolean tpl=false;
        boolean pure_dbl = false;

        for (int i=0;i<s.length()-1;i++)
        {
            String thiscar = s.substring(i,i+1);
            String nextcar = s.substring(i+1,i+2);
            String nextbutone ="";
            String thecharbefore = "";

            int comp1 = nextcar.compareTo(thiscar);


            if (i==0) thecharbefore="A"; else thecharbefore = s.substring(i-1,i);
            if (i == s.length()-2) nextbutone="A"; else nextbutone = s.substring(i+2,i+3);
            int comp2 = nextbutone.compareTo(nextcar);
            int comp0 = thecharbefore.compareTo(thiscar);



            //System.out.println("[" + s + "." + i + "] :" + thiscar + "," +nextcar);

            if (comp1==0)
            {
                // we have a double if the next character isn't a double and the one before isn't as well...
                if (comp2 != 0 && comp0 !=0) pure_dbl=true;
            }


        }

        //System.out.println("    " + s);
        return (pure_dbl);
    }

    public static void main(String []a)
    {
        System.out.println("Hello world");
        System.out.println("DAy4....");

        //so.....
        int st = 246515;
        int en = 739105;

        //st = 1234;
        //en = 1345;

        int cnt=0;
        for (int i=st;i<=en;i++)
        {
            String s = Integer.toString(i);
            int y = rep(s);
            if (y>=0)
            {
                //System.out.println("m=" + s);
                if (chktriples(s))
                {
                    System.out.println("      tr=" + s);
                    cnt++;
                }
                System.out.println("fl=" + s);
            }
        }
        System.out.println("-> " + cnt);
    }

}
