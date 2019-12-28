
public class d16
{
    public static String n(int x,String s)
    {
        StringBuilder sb = new StringBuilder("");
        for (int i=0;i<x;i++)
        {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String mkX(int max,int []v)
    {
        String ret = "";
        for (int i=0;i<max;i++)
        {
            if (i>0) ret +=",";
            ret+=v[i];
        }
        return ret;
    }
    public static String mkX(int []v)
    {
        String ret = "";
        for (int i=0;i<v.length;i++)
        {
            if (i>0) ret +=",";
            ret+=v[i];
        }
        return ret;
    }
    public static int[] n(int x,int []v)
    {
        int []ret = new int[v.length*x];

        for (int i=0;i<v.length;i++)
        {
            for (int j=0;j<x;j++)
            {
                ret[(x*i)+j]=v[i];
            }
        }
        return ret;
    }
    public static int[] k(int x,int []v)
    {
        int []ret = new int[x*v.length];
        for (int i=0;i<x;i++)
        {
            for (int j=0;j<v.length;j++)
            {
                ret[(v.length*i)+j]=v[j];
            }
        }
        return ret;
    }
    public static int[] toA(String s)
    {
        int []ret = new int[s.length()];
        for (int i=0;i<ret.length;i++)
        {
            int j = Integer.parseInt(s.substring(i,i+1));
            ret[i]=j;
        }
        return ret;
    }
    public static int units(int i)
    {
        int ret = Math.abs(i);
        return ret%10;
    }

    public static int []fft(int []in)
    {
        int []out = new int[in.length];
        for (int i=0;i<in.length;i++)
        {
            int []pt = k(500000,n(i+1,pattern));
            int dig=0;
            for (int j=0;j<in.length;j++)
            {
                dig+=pt[1+j]*in[j];
            }
            int d2 = units(dig);
            out[i]=d2;
        }
        return out;
    }

    public static int []pattern = {0,1,0,-1};

    public static void main(String []args)
    {
        System.out.println("hello world...");

        String inp="03036732577212944063491565474664";
//        String inp="59787832768373756387231168493208357132958685401595722881580547807942982606755215622050260150447434057354351694831693219006743316964757503791265077635087624100920933728566402553345683177887856750286696687049868280429551096246424753455988979991314240464573024671106349865911282028233691096263590173174821612903373057506657412723502892841355947605851392899875273008845072145252173808893257256280602945947694349746967468068181317115464342687490991674021875199960420015509224944411706393854801616653278719131946181597488270591684407220339023716074951397669948364079227701367746309535060821396127254992669346065361442252620041911746738651422249005412940728";
        int []in2=n(10000,toA(inp));
        for (int i=0;i<100;i++)
        {
            in2 = fft(in2);
            System.out.println(i + " -> " + mkX(12,in2));
        }
    }
}
