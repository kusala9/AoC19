import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class d14
{
    public static HashMap<String,Integer> mult = new HashMap<>();
    public static HashMap<e,e []> dict2 = new HashMap<>();

    private static class e
    {
        String nm;
        int v;
        public e(int i,String s)
        {
            v=i;
            nm=s;
        }
        public e(String s)
        {
            String []f = s.split(" ");
            if (f.length!=2 && f.length!=3)
            {
                System.out.println("ERROR: Can't parse \"" + s + "\"" + f.length);
                System.exit(1);
            }
            if (f[0].length()==0)
            {
                nm=f[2];
                v=Integer.parseInt(f[1]);
            }
            else
            {
                nm=f[1];
                v=Integer.parseInt(f[0]);
            }
        }
        public String toString()
        {
            return v + nm;
        }
    }

    // turn an element into nK + t where n is its atomic number :-) and t is the leftover (<0).
    public static e[] resolve(e elt)
    {
        if (dict2.containsKey(elt))
        {
            return dict2.get(elt);
        }
        ArrayList<e> ret = new ArrayList<>();
        if (mult.containsKey(elt.nm))
        {
            int n = mult.get(elt.nm);
            while (elt.v>0)
            {
                ret.add(new e(n,elt.nm));
                elt.v -= n;
            }
            if (elt.v<0)
            {
                ret.add(new e(elt.v,elt.nm));
            }
            e []r2 = new e[ret.size()];

            for (int j=0;j<ret.size();j++)
            {
                r2[j]=ret.get(j);
            }

            return r2;
        }
        else
        {
            System.out.println("ERROR: cant find \"" + elt + "\" defn..");
        }
        return null;
    }


    
    public static String f(e elt)
    {
        if (dict2.containsKey(elt))
        {
            e[] elts = dict2.get(elt);

        }
        return "";
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
    public static void main(String []a)
    {
        File file = new File("d14_1.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
            {
                String []f =  line.split("=>");
                String []x = f[0].split(",");
                e[]vals = new e[x.length];
                for (int i=0;i<x.length;i++)
                {
                    vals[i]= new e(x[i]);
                }
                String y = f[1];
                e K = new e(f[1]);
                mult.put(K.nm,K.v);
                dict2.put(K,vals);
            }
        }catch (Exception e){e.printStackTrace();}
        System.out.println("dun");
        f(new e(1,"FUEL"));
        e []b8 = resolve(new e(8,"B"));
        e []b3 = resolve(new e(3,"B"));

        System.out.println("Done");
    }
}
