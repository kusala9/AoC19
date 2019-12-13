/*
   DOCO:  
*/

import java.util.HashMap;
import java.util.Objects;

public class Pt //extends pear<Integer,Integer>
{
    private int first ;
    private int second;
    public int dir = 0;
    public int x()
    {
        return this.first;
    }
    public int y()
    {
        return this.second;
    }
    public Pt(int x,int y)
    {
        first=x;
        second=y;
        //super(x,y);
        dir=0;
    }
    public String toString()
    {
        return "(" + this.first + "," + this.second + ")";
    }
    public int getDirection()
    {
        return dir;
    }
    public void turn(int i)
    {
        if (i==0) this.dir--;
        else  this.dir++;
        if (this.dir<0) this.dir+=4;
        if (this.dir>3) this.dir-=4;
    }
    public void mv()
    {
        if (dir==0) this.second++;
        else if (dir==1) this.first++;
        else if (dir==2) this.second--;
        else if (dir==3) this.first--;
    }
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Pt))
        {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Pt c = (Pt) o;

        return (Integer.compare(c.first, first)==0) && (Integer.compare(c.second, second)==0);
    }
    public static HashMap<Pt,Integer> h = new HashMap<>();

    public static void main(String []a)
    {
        System.out.println("Test...");
        Pt a1 = new Pt(0,0);
        Pt a2 = new Pt(0,0);
        h.put(a1,0);
        h.put(a1,1);
        System.out.println();

    }
}
