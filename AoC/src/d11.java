/*
   DOCO:  
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class d11
{
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static HashMap<String,Integer> hull = new HashMap<>();
    public static HashMap<String,Integer> painted = new HashMap<>();

    private static BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
    private static void getI()
    {
        try
        {
            System.out.print("input a num => ");
            String s = R.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static String sdir(int d)
    {
        if (d==0) return "^";
        if (d==1) return ">";
        if (d==2) return "V";
        if (d==3) return "<";
        return "X";
    }
    public static Pt pos;
    public static int gp=0;
    public static void main(String []a)
    {
        System.out.println ("hw. d9");
        String prog1 = "1102,34915192,34915192,7,4,7,99,0";
        String prog2 = "104,1125899906842624,99";
        String prog3 = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
        String noddy = "1002,4,3,4,33";
        String d11   = "3,8,1005,8,330,1106,0,11,0,0,0,104,1,104,0,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,1,10,4,10,101,0,8,29,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,0,10,4,10,1001,8,0,51,1006,0,78,2,107,9,10,1006,0,87,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,1,8,10,4,10,1001,8,0,82,2,1103,5,10,1,101,8,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,0,8,10,4,10,101,0,8,112,1006,0,23,1006,0,20,1,2,11,10,1,1007,12,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,1,8,10,4,10,101,0,8,148,3,8,102,-1,8,10,101,1,10,10,4,10,108,1,8,10,4,10,1002,8,1,170,2,101,12,10,2,5,7,10,1,102,10,10,3,8,1002,8,-1,10,1001,10,1,10,4,10,1008,8,1,10,4,10,1001,8,0,205,1,1004,10,10,2,6,13,10,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,0,10,4,10,1001,8,0,235,2,102,4,10,1006,0,16,1006,0,84,1006,0,96,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,0,8,10,4,10,1001,8,0,269,1006,0,49,2,1003,6,10,2,1104,14,10,1006,0,66,3,8,102,-1,8,10,101,1,10,10,4,10,108,0,8,10,4,10,1002,8,1,305,2,1,11,10,101,1,9,9,1007,9,1020,10,1005,10,15,99,109,652,104,0,104,1,21102,838479487744,1,1,21102,1,347,0,1106,0,451,21101,666567967640,0,1,21101,358,0,0,1106,0,451,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,21101,28994219048,0,1,21102,405,1,0,1105,1,451,21102,3375459559,1,1,21101,0,416,0,1106,0,451,3,10,104,0,104,0,3,10,104,0,104,0,21102,838433665892,1,1,21102,1,439,0,1106,0,451,21102,988669698816,1,1,21102,450,1,0,1105,1,451,99,109,2,21201,-1,0,1,21102,1,40,2,21101,482,0,3,21102,472,1,0,1105,1,515,109,-2,2105,1,0,0,1,0,0,1,109,2,3,10,204,-1,1001,477,478,493,4,0,1001,477,1,477,108,4,477,10,1006,10,509,1101,0,0,477,109,-2,2105,1,0,0,109,4,1201,-1,0,514,1207,-3,0,10,1006,10,532,21101,0,0,-3,22102,1,-3,1,21201,-2,0,2,21102,1,1,3,21101,551,0,0,1106,0,556,109,-4,2105,1,0,109,5,1207,-3,1,10,1006,10,579,2207,-4,-2,10,1006,10,579,21201,-4,0,-4,1105,1,647,21201,-4,0,1,21201,-3,-1,2,21202,-2,2,3,21101,0,598,0,1106,0,556,21202,1,1,-4,21102,1,1,-1,2207,-4,-2,10,1006,10,617,21102,0,1,-1,22202,-2,-1,-2,2107,0,-3,10,1006,10,639,22102,1,-1,1,21101,0,639,0,106,0,514,21202,-2,-1,-2,22201,-4,-2,-4,109,-5,2105,1,0";

        BigInteger ONE = new BigInteger("1");
        BigInteger Z   = new BigInteger("0");

        eddie5 E1 = new eddie5(2,d11);
        //E1.setDebug(true);
        //E1.debugMem(true);
        //E1.setManual(true);
       // E1.dump();
        pos = new Pt(0,0);
        hull.put(pos.toString(),0);
        BigInteger []inputs = {ONE};
        E1.setI(inputs);
        int ins=0;
        System.out.println("HPR: Initial = " + pos);

        //for (int z=0;z<20;z++)
        while(true)
        {
            //clearScreen();
            //pr(5);//getI();
            System.out.println("HPR: Position now -> " + pos + " col=" + getC(pos) + "=" + ((getC(pos)==0)?"Black":"White"));
            System.out.println("HPR: Running from MEMORY=" + ins);
            ins = E1.runC(ins);
            if (E1.isDun())
            {
                System.out.println("HPR: Finished -> DONE");
                break;
            }
            ArrayList<BigInteger> o = E1.getAlloutputs();

            if (o.size()!=2)
            {
                System.out.println("Ouch. Not got 2 vals back");
                System.exit(1);
            }

            int col = o.get(0).intValue();
            int mv  = o.get(1).intValue();

            System.out.println("HPR: Received  col instruction =" + col + " turn=" + ((mv==0)?"Left":"Right") + " from=" + sdir(pos.dir));
            paint(pos,col);
            System.out.println("HPR: N=" + gp);
            pos.turn(mv);
            System.out.println("HPR: Direction now = " + sdir(pos.dir));
            pos.mv();
            inputs[0] = BigInteger.valueOf(getC(pos));
            E1.resetOutputs();
        }
        pr(20,80);
//        for (String s:painted.keySet())
//        {
//            System.out.println("-> " + s +" " + ((getCS(s)==0)?"Black":"White") );
//        }
//        System.out.println("TOTAL=" + gp);
//        System.out.println("TOTAL UNIQUE PAINTED=" + painted.keySet().size());
    }

    public static void paint(Pt p,int col)
    {
        gp++;
        Pt cleanPt = new Pt(p.x(),p.y());
        if (hull.containsKey(p))
        {
            hull.remove(cleanPt);
            hull.put(cleanPt.toString(),col);
        }
        else
        {
            hull.put(cleanPt.toString(),col);
        }
        painted.put(cleanPt.toString(),col);
        String s = (col==0)?"Black":"White";
        System.out.println("HPR: Painted " + p + " = " + s);
    }
    public static int getPainted(Pt p)
    {
        Pt cleanPt = new Pt(p.x(),p.y());
        if (painted.containsKey(cleanPt.toString()))
        {
            return painted.get(cleanPt.toString());
        }
        else
        {
            return -1;
        }
    }
    public static int getCS(String s)
    {
        if (hull.containsKey(s)) return hull.get(s);
        else return 0;
    }
    public static int getC(Pt p)
    {
        Pt cleanPt = new Pt(p.x(),p.y());
        boolean b = hull.containsKey(cleanPt.toString());
        if (b)
        {
            return hull.get(cleanPt.toString());
        }
        else
        {
            hull.put(cleanPt.toString(),0);
            return 0;
        }
    }
    public static void pr(int dim,int col)
    {
        for (int y=(dim);y>(0-dim);y--)
        {
            System.out.print(y + ((y>=0)?" ":"") + "|");
            for (int x=(0-col);x<(col);x++)
            {
                Pt p = new Pt(x,y);
                if (p.x() == pos.x() && p.y() == pos.y()) System.out.print("O");
                else if (getPainted(p)==0) System.out.print(".");
                else if (getPainted(p)==-1) System.out.print(" ");
                else System.out.print("#");
            }
            System.out.println();
        }
    }
}
