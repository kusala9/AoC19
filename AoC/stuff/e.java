/*
   DOCO:  
*/
public final class e
{
    public static e Gen(int v,String nm)
    {
        return new e(v,nm);
    }
    public static e Gen(String el)
    {
        return new e(el);
    }
    public String nm;
    public int v;
    private e(int i,String s)
    {
        v=i;
        nm=s;
    }
    private e(String s)
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
    @Override

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        e em = (e) o;
        if (v != em.v) return false;
        return nm != null ? nm.equals(em.nm) : em.nm == null;
    }

    @Override

    public int hashCode()
    {
        int result = (int) (v ^ (v >>> 32));
        result = 31 * result + (nm != null ? nm.hashCode() : 0);
        return result;
    }
}
