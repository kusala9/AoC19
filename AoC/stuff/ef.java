/*
   DOCO:  
*/

public final class ef
{
    public static ef Gen(int v,String nm)
    {
        return new ef(v,nm);
    }

    private ef(int v,String nm)
    {
        this.v = v;
        this.nm = nm;
    }

    private int v;
    private String nm;
}