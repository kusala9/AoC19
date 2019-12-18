import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class d14
{
    public static HashMap<String,Integer> mult = new HashMap<>();
    public static HashMap<e,e []> dict2 = new HashMap<>();


    private static e []adder(e[] o1)
    {
        HashMap<String,Integer>  w = new HashMap<String,Integer>();

        for (int i=0;i<o1.length;i++)
        {
            e x = o1[i];
            int nv = (w.containsKey(x.nm))?w.get(x.nm)+x.v:x.v;
            w.put(x.nm,nv);
        }
        e []ret = new e[w.keySet().size()];
        int i=0;
        for (String s:w.keySet())
        {
            e ne = e.Gen(w.get(s),s);
            ret[i]=ne;
            i++;
        }
        //System.out.println("ADDER:(" + pr(o1) + ") = (" + pr(ret) + ")");
        return ret;
    }
    private static e []adder(e[] o1, e[]o2)
    {
        HashMap<String,Integer>  w = new HashMap<String,Integer>();
        for (int i=0;i<o1.length;i++)
        {
            e x = o1[i];
            int nv = (w.containsKey(x.nm))?w.get(x.nm)+x.v:x.v;
            w.put(x.nm,nv);
        }
        for (int i=0;i<o2.length;i++)
        {
            e x = o2[i];
            int nv = (w.containsKey(x.nm))?w.get(x.nm)+x.v:x.v;
            w.put(x.nm,nv);
        }
        e []ret = new e[w.keySet().size()];
        int i=0;
        for (String s:w.keySet())
        {
            e ne = e.Gen(w.get(s),s);
            ret[i]=ne;
            i++;
        }
        //System.out.println("ADDER:(" + pr(o1) + ") + (" + pr(o2) + ") = (" + pr(ret) + ")");
        return ret;
    }

    public static int getN(String nm)
    {
        return mult.containsKey(nm)?mult.get(nm):0;
    }
    // turn an element into nK + t where n is its atomic number :-) and t is the leftover (<0).
    public static e[] resolve(e elt,String pad)
    {
        if (elt.v<0)
        {
            e[] r={elt};
            return r;
        }

        if (dict2.containsKey(elt))
        {
            return dict2.get(elt);
        }
        ArrayList<e> ret = new ArrayList<>();
        if (mult.containsKey(elt.nm))
        {
            int n = mult.get(elt.nm);
            int c = elt.v;
            while (c>=n)
            {
                ret.add(e.Gen(n,elt.nm));
                c -= n;
            }
            if (c>0)
            {
                ret.add(e.Gen(c,elt.nm));
            }

            e []r2 = new e[ret.size()];
            for (int j=0;j<ret.size();j++)
            {
                r2[j]=ret.get(j);
            }

            //System.out.println(pad + ": RES " + elt + " (" + pr(r2) + ")");
            return r2;
        }
        else if (elt.nm.compareTo("ORE")==0)
        {
            e [] r = {elt};
            return r;
        }
        else
        {
            System.out.println("ERROR: cant find \"" + elt + "\" defn..");
        }
        return null;
    }

    public static String ns(int n)
    {
        String ret = "";
        for (int i=0;i<n;i++)
        {
            ret+=" ";
        }
        return ret;
    }

    public static e[] f2( e[] elts)
    {
        e []empty = {};
        for (e ee:elts)
        {
            if (dict2.containsKey(ee))
                elts = dict2.get(ee);
            else
                elts = resolve(ee," ");
            empty = concat(empty,elts);
        }
        return empty;
    }
    public static e[] f(e elt,int ind)
    {
        String pad = ns(ind);
        e[] r = {elt};
        int n = getN(elt.nm);
        if (n>0 && elt.v<n) return r;

        if (elt.nm.compareTo("ORE")==0 || elt.v <0)
        {
            return r;
        }
        e []empty = {};
        e[] elts = null;

        if (dict2.containsKey(elt))
            elts = dict2.get(elt);
        else
            elts = resolve(elt,pad);

        System.out.println(pad + ": Expanding " + elt + " (" + pr(elts)+ ")");

        for (int i=0;i<elts.length;i++)
        {
            e[] exp = f(elts[i],ind+3);
            empty = concat(empty, exp);
        }
        empty = mineOre(adder(empty));
        System.out.println(pad + ": " + elt + " = (" + pr(empty)+ ")" + " O=" + ore);
        return empty;
    }
    public static e[] concat(e []aa,e []bb)
    {
        e []ret = new e[aa.length+bb.length];
        for (int k=0;k<aa.length;k++)
            ret[k]=aa[k];
        for (int k=0;k<bb.length;k++)
            ret[k+aa.length]=bb[k];
        return ret;
    }
    //extract ore from list...
    public static int ore = 0;
    public static e[] mineOre(e []stuff)
    {
//        ArrayList<e> ret2 = new ArrayList<e>();
//        for (int i=0;i<stuff.length;i++)
//        {
//            e ee = stuff[i];
//            if (dict2.containsKey(ee))
//            {
//                e[] v = dict2.get(ee);
//            }
//            if (ee.nm.compareTo("ORE")==0) ore+=ee.v;
//            else ret2.add(ee);
//        }

        ArrayList<e> ret = new ArrayList<e>();
        for (int i=0;i<stuff.length;i++)
        {
            e ee = stuff[i];
            if (ee.nm.compareTo("ORE")==0) ore+=ee.v;
            else ret.add(ee);
        }
        e []r2 = new e[ret.size()];
        for (int j=0;j<ret.size();j++)
        {
            r2[j]=ret.get(j);
        }
        return r2;
    }

    public static void main(String []a)
    {
        File file = new File("d14_1.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
            {
                System.out.println("-->"  + line + "<--");
                String []f =  line.split("=>");
                String []x = f[0].split(",");
                e[]vals = new e[x.length];
                for (int i=0;i<x.length;i++)
                {
                    vals[i]= e.Gen(x[i]);
                }
                String y = f[1];
                e K = e.Gen(f[1]);
                mult.put(K.nm,K.v);
                dict2.put(K,vals);
            }
        }catch (Exception e){e.printStackTrace();}
        System.out.println("dun");

        e root = e.Gen(1,"FUEL");
        e[] rl = {root};
        e[] empty = {};

        //rl = adder(rl,diff);
        for (int j=0;j<2;j++)
        {
            for (int i=0;i<6;i++)
            {
                rl = f2(rl);
                System.out.println(i + ": " + pr(rl));
                rl = mineOre(rl);
            }
            rl = adder(rl);
            System.out.println(pr(rl));
        }

        //e[] extras = {e.Gen(1,"GPVTF"),e.Gen(2,"DCFZ"),e.Gen(1,"NZVS"),e.Gen(2,"HKGWZ"),e.Gen(8,"QDVJ")};
        e[] extras = {e.Gen(8,"QDVJ"),e.Gen(3,"KHKGT")};
        rl = adder(rl,extras);

        for (int j=0;j<2;j++)
        {
            for (int i=0;i<3;i++)
            {
                rl = f2(rl);
                System.out.println(i + ": " + pr(rl));
                rl = mineOre(rl);
            }
            rl = adder(rl);
            System.out.println(pr(rl));
        }
        e[] extras3 = {e.Gen(5,"DCFZ"),e.Gen(3,"PSHF"),e.Gen(4,"NZVS")};
        rl = adder(rl,extras3);

        for (int j=0;j<2;j++)
        {
            for (int i=0;i<3;i++)
            {
                rl = f2(rl);
                System.out.println(i + ": " + pr(rl));
                rl = mineOre(rl);
            }
            rl = adder(rl);
            System.out.println(pr(rl));
        }
    }



    public static String pr( e[]v)
    {
        String ret="";
        for (int i=0;i<v.length;i++)
        {
            if (i>0) ret+=(" ");
            ret+= (v[i].v<0)?"":"+";
            ret+=v[i].v + v[i].nm;
        }
        return ret + ":   O=" + ore;
    }
}
