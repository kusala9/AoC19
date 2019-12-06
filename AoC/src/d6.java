/*
   DOCO:  
*/

import java.util.ArrayList;
import java.util.HashMap;

public class d6
{
    public static HashMap<String,P> k = new HashMap<>();
    public static HashMap<String, ArrayList<String>> orbits = new HashMap<>();
    public static HashMap<String,Integer> scores = new HashMap<>();

    public static boolean gf = false;
    // add N to the planet named nm, starting with root
    public static void adder(P root,String name,P N)
    {
        if (name.compareTo(root.nm)==0)
        {
            //found.
            root.o.add(N);
            System.out.println("Added " + N.nm + " to " + root.nm);
            gf=true;
        }
        else
        {
            for (P c:root.o)
            {
                adder(c,name,N);
            }
        }
    }

    public static void pr(P root,int ns)
    {
        for (int i=0;i<ns;i++) System.out.print(" ");
        System.out.println("(" + root.nm + ")");
        for (P p:root.o)
        {
            pr(p,ns+1);
        }
    }

    public static void pr2(String name,int ns)
    {
        for (int i=0;i<ns;i++) System.out.print(" ");
        P p = k.get(name);
        System.out.print(name + " -> " + p.nm);
        for (P l:p.o)
        {
            pr2(l.nm,ns+1);
        }
    }
    public static void main(String []args)
    {
        System.out.println("Hello world.");
        System.out.println("day6....");
        HashMap<String,Integer> M = new HashMap<>();
//        String []d = ("COM)B" +
//                ",B)C" +
//                ",C)D" +
//                ",D)E" +
//                ",E)F" +
//                ",B)G" +
//                ",G)H" +
//                ",D)I" +
//                ",E)J" +
//                ",J)K" +
//                ",K)L" +
//                ",K)YOU" +
//                ",I)SAN").split(",");
//        String []d = ("COM)B," +
//                "B)C," +
//                "C)D," +
//                "D)E," +
//                "E)F," +
//                "B)G," +
//                "G)H," +
//                "D)I," +
//                "E)J," +
//                "J)K," +
//                "K)L").split(",");
        String []d = d6data.dat.split(",");

        P com = new P("COM");
        k.put("COM",com);
        for (int i=0;i<d.length;i++)
        {

            String []ent = d[i].split("\\)");
            System.out.println(ent[0] + " is orbited by " + ent[1]);
            upscore(ent[1]);
            if (orbits.containsKey(ent[0]))
            {
                ArrayList<String> e = orbits.get(ent[0]);
                e.add(ent[1]);
                orbits.put(ent[0],e);
            }
            else
            {
                ArrayList<String> ne = new ArrayList<String>();
                ne.add(ent[1]);
                orbits.put(ent[0],ne);
            }
        }
        ArrayList<String> roots = fR();
        for (String s:roots)
        {
            adupp2(s,0);
            P root = new P(s);
            printRoute("SAN","COM","",0);
            printRoute("YOU","COM","",0);
            pr(root,0);


        }
        System.out.println(roots.size() + " td=" + td + " id=" + id + "=" + (td+id) );

    }

    private void bldTree(P root)
    {

    }

    private static void printRoute(String srch,String root,String path,int i)
    {
        if (orbits.containsKey(root))
        {
            for (String p:orbits.get(root))
            {
                path+=p + "[" + i + "] ";
                if (p.compareTo(srch)==0) System.out.println("=" + path );
                printRoute(srch,p,path,i+1);
            }
        }
    }

    private static ArrayList<String> fR()
    {
        ArrayList<String> ret = new ArrayList<String>();
        // find roots...
        for (String s:orbits.keySet())
        {
            if (!scores.containsKey(s))
            {
                System.out.println("ROOT->" + s);
                ret.add(s);
            }
        }
        return ret;
    }

    private static void upscore(String s)
    {
        if (scores.containsKey(s)) scores.put(s,scores.get(s)+1);
        else scores.put(s,1);
    }

    private static int td = 0;
    private static int id = 0;

    private static void adupp2(String s,int l)
    {
        //System.out.println(l + ": " + s + " +1 (total)");
        id++;
        if (orbits.containsKey(s))
        {
            for (String p:orbits.get(s))
            {
                //System.out.println("+" + l + " to level");
                td+=l;
                adupp2(p,l+1);
            }
        }
    }

}
