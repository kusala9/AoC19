import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;

public class d19
{

    public static int [][]screen = new int[200][200];
    public static BigInteger[] tB(int []in)
    {
        BigInteger []out = new BigInteger[in.length];
        for (int i=0;i<in.length;i++)
        {
            out[i] = BigInteger.valueOf(in[i]);
        }
        return out;
    }
    public static String d19 = "109,424,203,1,21102,11,1,0,1105,1,282,21101,0,18,0,1105,1,259,2102,1,1,221,203,1,21102,1,31,0,1105,1,282,21102,1,38,0,1105,1,259,21002,23,1,2,21201,1,0,3,21101,0,1,1,21102,57,1,0,1105,1,303,2102,1,1,222,21002,221,1,3,21001,221,0,2,21102,1,259,1,21102,80,1,0,1105,1,225,21102,59,1,2,21102,1,91,0,1105,1,303,1202,1,1,223,21001,222,0,4,21102,259,1,3,21102,1,225,2,21101,225,0,1,21101,118,0,0,1105,1,225,21002,222,1,3,21102,1,112,2,21101,0,133,0,1105,1,303,21202,1,-1,1,22001,223,1,1,21101,148,0,0,1105,1,259,1201,1,0,223,20102,1,221,4,21002,222,1,3,21102,1,18,2,1001,132,-2,224,1002,224,2,224,1001,224,3,224,1002,132,-1,132,1,224,132,224,21001,224,1,1,21101,0,195,0,106,0,108,20207,1,223,2,21001,23,0,1,21102,1,-1,3,21102,1,214,0,1105,1,303,22101,1,1,1,204,1,99,0,0,0,0,109,5,2101,0,-4,249,22101,0,-3,1,21202,-2,1,2,21201,-1,0,3,21101,250,0,0,1105,1,225,22101,0,1,-4,109,-5,2105,1,0,109,3,22107,0,-2,-1,21202,-1,2,-1,21201,-1,-1,-1,22202,-1,-2,-2,109,-3,2106,0,0,109,3,21207,-2,0,-1,1206,-1,294,104,0,99,21202,-2,1,-2,109,-3,2105,1,0,109,5,22207,-3,-4,-1,1206,-1,346,22201,-4,-3,-4,21202,-3,-1,-1,22201,-4,-1,2,21202,2,-1,-1,22201,-4,-1,1,22102,1,-2,3,21101,343,0,0,1106,0,303,1105,1,415,22207,-2,-3,-1,1206,-1,387,22201,-3,-2,-3,21202,-2,-1,-1,22201,-3,-1,3,21202,3,-1,-1,22201,-3,-1,2,22102,1,-4,1,21101,384,0,0,1105,1,303,1105,1,415,21202,-4,-1,-4,22201,-4,-3,-4,22202,-3,-2,-2,22202,-2,-4,-4,22202,-3,-2,-3,21202,-4,-1,-2,22201,-3,-2,1,22102,1,1,-4,109,-5,2106,0,0";

    public static int ff(int ix,int y)
    {
        int x=ix;
        while (true)
        {
            if (at(x,y)==1) break;
            x++;
        }
        return x;
    }
    public static int lf(int ix,int y)
    {
        int x=ix;
        while (true)
        {
            if (at(x,y)==0) break;
            x++;
        }
        return x;
    }

    public static boolean tst(int c,int r)
    {
        System.out.println("Testing... c=" + c + "r=" + r);
        for (int ir=0;ir<100;ir++)
        {
            for (int ic=0;ic<100;ic++)
            {
                if (at(c+ic,r+ir)==0)
                {
                    System.out.println("FAIL: " + (c+ic) + "," + (r+ir) + "=" + at(c+ic,r+ir));
                    return false;
                }
            }
            System.out.print(ir);
        }
        System.out.println("Done");
        return true;
    }
    public static String getRow(int r,int st,int n)
    {
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("%04d",r)).append(":(").append(String.format("%03d",st)).append("): ");
        for (int i=st;i<(st+n);i++) sb.append(at(i,r));
        return sb.toString();
    }
    public static void main (String []arg)
    {
        File file = new File("d19.txt");
        FileWriter fr = null;
        BufferedWriter br = null;
        try
        {
            fr = new FileWriter(file);
            for (int i=500;i<850;i++)
            {
                String l=getRow(i,0,1200);
                fr.write(l + "\n");
                System.out.println(l);
                int f1=ff(0,i);
                int lf=lf(f1,i);
                if (at(lf-99,i+99)==1 && at(lf-99,i)==1  && at(lf,i+99)==1)
                {
                    tst(f1,i-99);
                }
            }
        }
        catch (Exception e){e.printStackTrace();}

        int c=1023;int r=730;
        if (tst(c,r))
        {
            System.out.println("found valid 100x100=" + c + "," + r);
        }
//        System.out.println("hello world....");
//
//        int y=0;
//        int x=0;
//        int fx=0;
//        int lx=0;
//        int r=320;
//        while (true)
//        {
//            fx=ff(0,r);
//            lx=lf(fx+1,r);
//            System.out.println("r=" + r + " ff=" + fx + " lx=" + lx + " diff=" + (lx-fx));
//
//            if (lx-fx>=100) break;
//
//            r+=1;
//        }
//
//        System.out.println("first row with >100 1s=" + r + " 1s end at " + lx);
//        System.out.println("searching for rows where 1s start at " + (lx-100));
//        int lc=lx-100;
//        int ir=r;
//        int ic=0;
//        r=720;
//        while (true)
//        {
//
//            fx=ff(400,r);
//            lx=lf(fx+1,r);
//            System.out.print("fx="+ fx + " 1e=" + lx + ": " );
//            System.out.println(getRow(r,900,250));
//            if (at(lx-100,r+100)==1) break; // break if there's a 1 back and down 100 rows...
//            r+=1;
//        }
//        System.out.println("found a match " + r + "," + (lx-100));
//        if (tst(lx-100,r))
//        {
//            System.out.println("found valid 100x100=" + (lx-100) + "," + r);
//        }
//
//        for (int i=725;i<900;i++)
//        {
//            String l=getRow(i,0,1100);
//            fr.write(l);
//            System.out.println(getRow(i,900,250));
//        }

//        for (int i1=0;i1<200;i1++)
//        {
//            for (int i2=0;i2<200;i2++)
//            {
//                screen[i1][i2] = 0;
//            }
//        }
//
//        for (int r=0;r<200;r++)
//        {
//            for (int c=0;c<200;c++)
//            {
//                screen[c][r] = at(c,r);
//            }
//        }
//        int n = pr();
//        System.out.println("=================FINISHED======================" + n);
    }
    public static int at(int x,int y)
    {
        int []inp= new int[2];
        inp[0]=x;
        inp[1]=y;
        mazeDroid2 G = new mazeDroid2(0,d19);
        G.setInputFromOutside(true);
        G.setDebug(false);
        G.debugMem(false);
        G.setManual(false);
        G.setI(tB(inp));
        G.runC(0);
        ArrayList<BigInteger> out = G.getAlloutputs();
        return (out.get(0)).intValue();
    }
    public static int pr() {
        int n1 = 0;
        for (int r = 0; r < 200; r++) {
            System.out.print(String.format("%02d |", r));
            for (int c = 0; c < 200; c++) {
                switch (screen[r][c]) {
                    case 1: {
                        System.out.print("1");
                        n1++;
                        break;
                    }
                    case 0: {
                        System.out.print(".");
                        break;
                    }
                    default: {
                        System.out.print("X");
                        break;
                    }
                }
            }
            System.out.println("| ");
        }
    return n1;
    }
}
