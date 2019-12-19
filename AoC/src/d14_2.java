import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class d14_2
{
    public static HashMap<String,Integer> mult = new HashMap<>();
    public static HashMap<e,e []> dict2 = new HashMap<>();
    public static HashMap<String,Integer> ores = new HashMap<>();
    public static HashMap<String,Integer> dict3 = new HashMap<>();


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
    public static BigInteger ore2 = new BigInteger("0");
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
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                System.out.println("-->"  + line + "<--");
                String []f =  line.split("=>");
                String []x = f[0].split(",");
                e[]vals = new e[x.length];
                String y = f[1];
                e K = e.Gen(f[1]);
                for (int i=0;i<x.length;i++)
                {
                    vals[i]= e.Gen(x[i]);
                    if (x.length==1 && vals[i].nm.compareTo("ORE")==0)
                    {
                        ores.put(K.nm,vals[i].v);
                    }
                }
                mult.put(K.nm,K.v);
                dict2.put(K,vals);
            }
        }catch (Exception e){e.printStackTrace();}
        System.out.println("dun");
        BigInteger tri = new BigInteger("1000000000000");
        BigInteger ONE = new BigInteger("1");
        e root = e.Gen(1,"FUEL");
        e[] empty = {};
        for (int i=1;i<100000000;i++)
        {
            BigInteger bi = new BigInteger(Integer.toString(i));
            int orespend = ore;
            ore=0;
            double oreperfuel = ore2.doubleValue()/bi.doubleValue();
            BigInteger div = tri.divide(bi);
            make(root,"  ");;
            if (i%1000==0)
                System.out.println(String.format("%07d",i) + " " + ore2 + " Finished " + oreperfuel + " ORE=" + ore2) ;
        }



    }
    public static ArrayList<e> used = new ArrayList<e>();
    public static HashMap<String,Integer> store = new HashMap<String,Integer>();
    public static void make(e r,String pad)
    {
        if (r.nm.compareTo("ORE")==0)
        {
            //System.out.println(pad + " ORE  " + r);
            BigInteger bi = new BigInteger(Integer.toString(r.v));
            ore2 = ore2.add(bi);
            ore+=r.v;
            return;
        }
        int reqd = r.v;
        int n = mult.get(r.nm);
        //System.out.println(pad + r.nm + ": Need -> " + reqd + " " +  "Can make " + n + " at a time");
        int got = (store.containsKey(r.nm))?store.get(r.nm):0;
        int made=0;
        //System.out.println(pad + "In store=" + got);
        if (got>0)
        {
            if (got>=reqd)
            {
                made+=reqd;
                store.put(r.nm,got-reqd);
                //System.out.println(pad + " now got " + made + " store=" + store.get(r.nm) + " " + r.nm + " left in store");
            }
            else
            {
                made+=got;
                //System.out.println(pad + "Used up all in store... Have made " + made);
                store.put(r.nm,0);
            }
        }
        if (made<r.v)
        {
            //System.out.println(pad + "Making " + n + " " + r.nm + " - have  " + made);
            while (made<r.v)
            {
                e mk = e.Gen(n,r.nm);
                e[] elts = dict2.get(mk);
                for (int i=0;i<elts.length;i++)
                {
                    //System.out.println(pad + "Making " + elts[i]);
                    make(elts[i],pad+"  ");
                }
                made+=n;
            }
            if (made>r.v)
            {
                int leftover=made-r.v;
                int nleftover = (store.containsKey(r.nm))?store.get(r.nm):0;
                nleftover+=leftover;
                //System.out.println(pad + " Putting " + nleftover + " " + r.nm + " in store " );
                store.put(r.nm,nleftover);
            }
        }
        //System.out.println(pad + " Finished " + r + " ORE=" + ore + " Store=" + prs());

    }
    public static String prs()
    {
        HashMap<String,Integer> ns = new HashMap<>();
        String ret="";
        for (String s:store.keySet())
        {
            if (store.get(s)>0)
            {
                ns.put(s,store.get(s));
                ret+=store.get(s) + s + " ";
            }
        }
        store=ns;
        return ret;
    }
    public static e[] addOre(e[] rl)
    {
        e[] extras2 = {};
        for (int i=0;i<rl.length;i++)
        {
            if (ores.containsKey(rl[i].nm))
            {
                System.out.println("Adding ->" + rl[i]);
                int d = mult.get(rl[i].nm) - rl[i].v;
                e[] x = {e.Gen(d,rl[i].nm)};
                extras2 = concat(extras2,x);
            }
        }
        return extras2;
    }

    public static e[] addCompound(e[] rl)
    {
        e[] extras = {};
        for (int i=0;i<rl.length;i++)
        {
            if (!ores.containsKey(rl[i].nm))
            {
                int d = mult.get(rl[i].nm) - rl[i].v;
                e[] x = {e.Gen(d,rl[i].nm)};
                System.out.println("Adding ->" + pr(x));
                extras = concat(extras,x);
            }
        }
        return extras;
    }

    private static String gete(String s)
    {
        String nm="";
        String []f = s.split(" ");
        if (f.length!=2 && f.length!=3)
        {
            System.out.println("ERROR: Can't parse \"" + s + "\"" + f.length);
            System.exit(1);
        }
        if (f[0].length()==0)
        {
            nm=f[2];
            int v=Integer.parseInt(f[1]);
        }
        else
        {
            nm=f[1];
            int v=Integer.parseInt(f[0]);
        }
        return nm;
    }
    public static e[] expMineAdd(e[] rl)
    {
        int lastexpander=0;
        int lastadder=0;
        while (true)
        {
            int i=0;
            while (true)
            {
                rl = f2(rl);
                //System.out.println(i + "[" + rl.length + "]" + ": " + pr(rl));
                //rl = mineOre(rl);
                if (rl.length == lastexpander) break;
                lastexpander=rl.length;
                i++;
            }
            rl = adder(rl);
            if (rl.length == lastadder) break;
            lastadder=rl.length;
            System.out.println(rl.length + ": " + pr(rl));
        }
        return rl;
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
