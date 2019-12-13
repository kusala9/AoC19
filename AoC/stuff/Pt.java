/*
   DOCO:  
*/

public class Pt extends pear<Integer,Integer>
{
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
        super(x,y);
        dir=0;
    }
    public int getDirection()
    {
        return dir;
    }
    public void turn(int i)
    {
        if (i==0) dir=(4+(dir-1))%4;
        else  dir=(4+(dir+1))%4;
    }
    public void mv()
    {
        if (dir==0) this.second++;
        else if (dir==1) this.first++;
        else if (dir==2) this.second--;
        else if (dir==3) this.first--;
    }

    public static void main(String []a)
    {
        System.out.println("Test...");
        Pt aa = new Pt(0,0);
        System.out.println("a->" + aa.getDirection());
        aa.turn(0);
        System.out.println("a->" + aa.getDirection());
        aa.turn(0);
        System.out.println("a->" + aa.getDirection());
        aa.turn(0);
        System.out.println("a->" + aa.getDirection());
        aa.turn(0);
        System.out.println("a->" + aa.getDirection());
        aa.turn(1);
        System.out.println("a->" + aa.getDirection());
        aa.turn(1);
        System.out.println("a->" + aa.getDirection());
        aa.turn(1);
        System.out.println("a->" + aa.getDirection());
        aa.turn(1);
        System.out.println("a->" + aa.getDirection());
        aa.turn(1);
        System.out.println("a->" + aa.getDirection());
        aa.turn(1);
        System.out.println("a->" + aa.getDirection());
        aa.turn(1);
        System.out.println("a->" + aa.getDirection());
        aa.turn(1);
        System.out.println("a->" + aa.getDirection());
        aa.turn(1);
        System.out.println("a->" + aa.getDirection());
        aa.turn(1);
        System.out.println("a->" + aa.getDirection());
        aa.turn(1);

    }
}
