import java.util.ArrayList;

public class P
{
    // day6 planet
    public String nm;
    public P parent;
    public ArrayList<P> o = new ArrayList<P>();
    public P(String n)
    {
        nm=n;
    }
    public String toString()
    {
        return nm;
    }
}
