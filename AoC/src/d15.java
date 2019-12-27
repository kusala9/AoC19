import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class d15
{

    public static void mazer(mazeDroid M, Pt p)
    {

    }

    public static mazeDroid G;
    public static int pos=0;
    public static int xoff=1000;
    public static int yoff=1000;
    public static int screen[][] = new int[3000][3000];
    public static int been[][] = new int[3000][3000];
    public static int filled[][] = new int[3000][3000];

    public static int steps = 0;
    public static int x=0,y=0;
    public static int ox=0;
    public static int oy=0;

    public static BigInteger N = new BigInteger("1");
    public static BigInteger S = new BigInteger("2");
    public static BigInteger W = new BigInteger("3");
    public static BigInteger E = new BigInteger("4");
    public static void main(String []ag)
    {
        for (int i1=0;i1<80;i1++)
        {
            for (int i2=0;i2<80;i2++)
            {
                been[i1][i2] = 0;
                screen[i1][i2] = 0;
                filled[i1][i2] = 0;
            }
        }

        System.out.println("D15: hw2)");
        String d15 = "3,1033,1008,1033,1,1032,1005,1032,31,1008,1033,2,1032,1005,1032,58,1008,1033,3,1032,1005,1032,81,1008,1033,4,1032,1005,1032,104,99,1001,1034,0,1039,102,1,1036,1041,1001,1035,-1,1040,1008,1038,0,1043,102,-1,1043,1032,1,1037,1032,1042,1106,0,124,1002,1034,1,1039,101,0,1036,1041,1001,1035,1,1040,1008,1038,0,1043,1,1037,1038,1042,1105,1,124,1001,1034,-1,1039,1008,1036,0,1041,102,1,1035,1040,101,0,1038,1043,1001,1037,0,1042,1106,0,124,1001,1034,1,1039,1008,1036,0,1041,101,0,1035,1040,102,1,1038,1043,1001,1037,0,1042,1006,1039,217,1006,1040,217,1008,1039,40,1032,1005,1032,217,1008,1040,40,1032,1005,1032,217,1008,1039,39,1032,1006,1032,165,1008,1040,3,1032,1006,1032,165,1102,2,1,1044,1105,1,224,2,1041,1043,1032,1006,1032,179,1101,1,0,1044,1105,1,224,1,1041,1043,1032,1006,1032,217,1,1042,1043,1032,1001,1032,-1,1032,1002,1032,39,1032,1,1032,1039,1032,101,-1,1032,1032,101,252,1032,211,1007,0,73,1044,1106,0,224,1102,0,1,1044,1105,1,224,1006,1044,247,101,0,1039,1034,1001,1040,0,1035,102,1,1041,1036,1002,1043,1,1038,102,1,1042,1037,4,1044,1105,1,0,20,63,98,68,77,63,97,8,75,63,86,35,88,55,91,67,88,55,71,51,80,32,34,15,4,65,96,55,96,49,84,51,89,46,10,91,39,72,4,71,88,84,45,30,8,82,65,30,81,17,94,53,56,75,10,65,83,99,23,86,97,15,96,79,84,25,4,87,29,96,51,43,89,97,16,95,93,86,81,28,91,55,93,75,30,82,68,74,19,96,13,82,91,37,77,65,93,50,31,81,99,19,30,96,69,92,95,65,54,86,95,49,84,68,82,49,21,22,20,90,80,10,51,74,12,90,61,84,87,85,2,91,36,33,91,90,48,83,47,46,99,88,64,78,8,66,26,88,53,81,89,45,62,30,87,36,53,78,2,66,96,97,27,32,93,35,90,70,91,58,4,87,75,45,78,28,5,77,97,58,88,72,10,94,36,78,21,54,91,20,76,87,88,39,93,22,18,77,87,70,70,89,81,32,10,25,97,14,72,89,86,43,37,77,11,81,82,90,49,31,93,56,77,84,81,30,92,32,98,38,41,11,38,78,78,1,28,91,1,97,81,57,74,79,76,50,79,29,97,32,90,88,31,29,82,64,97,39,97,92,40,95,8,29,88,79,96,14,81,92,33,28,24,30,87,61,94,83,72,82,69,97,10,41,85,6,66,85,65,14,82,90,29,77,36,20,72,96,42,81,85,94,46,75,10,54,26,42,83,99,95,47,39,81,92,70,69,86,89,48,38,79,75,7,96,92,26,75,92,26,86,10,17,80,63,90,19,37,15,77,63,54,2,88,92,97,98,47,66,85,67,56,82,72,35,90,81,26,17,1,82,23,28,67,99,5,3,83,2,54,93,53,87,92,64,96,89,91,40,93,77,94,37,89,27,95,38,89,93,7,60,45,97,82,63,66,77,87,16,95,55,99,97,54,77,75,34,65,86,76,85,76,40,71,77,68,49,92,59,64,64,76,45,57,91,40,98,68,42,87,41,93,46,85,91,38,75,29,98,96,58,81,30,78,76,78,40,75,31,21,64,84,65,92,76,76,15,81,77,62,84,65,77,72,29,90,97,65,64,15,98,31,77,30,44,92,97,9,82,1,33,95,1,27,88,3,18,76,84,51,85,90,45,38,98,42,53,95,86,68,10,98,28,87,58,46,99,2,77,21,85,1,35,98,80,77,20,19,92,88,60,86,85,40,75,22,99,72,33,99,11,84,90,42,75,86,38,71,81,87,72,78,93,83,45,20,8,94,14,93,83,64,99,47,9,96,55,8,87,44,84,49,20,85,58,88,3,86,69,57,99,23,83,51,78,93,88,18,85,41,85,72,79,24,64,28,79,51,5,84,17,78,18,27,67,95,90,32,58,8,75,83,98,67,96,35,74,65,9,85,69,94,39,74,7,85,51,67,13,84,30,98,93,40,46,88,97,89,46,61,63,83,81,98,63,69,80,90,95,40,30,83,93,42,85,96,34,83,85,31,30,11,98,5,2,86,92,41,78,31,37,96,41,57,98,66,39,75,81,83,26,10,84,88,61,85,15,36,98,24,72,74,33,94,88,79,69,89,59,97,80,66,42,81,74,99,61,34,96,93,23,95,6,85,79,8,36,80,27,92,17,63,98,92,14,20,98,96,74,47,81,31,59,98,19,57,82,23,88,28,92,87,56,64,67,74,85,68,85,15,39,87,32,88,61,91,8,77,97,97,14,86,62,79,42,98,3,75,9,97,99,80,28,93,44,85,39,92,49,99,17,81,97,91,3,76,37,76,49,86,0,0,21,21,1,10,1,0,0,0,0,0,0";
        //String d15 = "3,1033,1008,1033,1,1032,1005,1032,31,1008,1033,2,1032,1005,1032,58,1008,1033,3,1032,1005,1032,81,1008,1033,4,1032,1005,1032,104,99,1001,1034,0,1039,102,1,1036,1041,1001,1035,-1,1040,1008,1038,0,1043,102,-1,1043,1032,1,1037,1032,1042,1106,0,124,1002,1034,1,1039,101,0,1036,1041,1001,1035,1,1040,1008,1038,0,1043,1,1037,1038,1042,1105,1,124,1001,1034,-1,1039,1008,1036,0,1041,102,1,1035,1040,101,0,1038,1043,1001,1037,0,1042,1106,0,124,1001,1034,1,1039,1008,1036,0,1041,101,0,1035,1040,102,1,1038,1043,1001,1037,0,1042,1006,1039,217,1006,1040,217,1008,1039,40,1032,1005,1032,217,1008,1040,40,1032,1005,1032,217,1008,1039,39,1032,1006,1032,165,1008,1040,3,1032,1006,1032,165,1102,2,1,1044,1105,1,224,2,1041,1043,1032,1006,1032,179,1101,1,0,1044,1105,1,224,1,1041,1043,1032,1006,1032,217,1,1042,1043,1032,1001,1032,-1,1032,1002,1032,39,1032,1,1032,1039,1032,101,-1,1032,1032,101,252,1032,211,1007,0,73,1044,1106,0,224,1102,0,1,1044,1105,1,224,1006,1044,247,101,0,1039,1034,1001,1040,0,1035,102,1,1041,1036,1002,1043,1,1038,102,1,1042,1037,4,1044,1105,1,0,20,63,98,68,77,63,97,8,75,63,86,35,88,55,91,67,88,55,71,51,80,32,34,15,4,65,96,55,96,49,84,51,89,46,10,91,39,72,4,71,88,84,45,30,8,82,65,30,81,17,94,53,56,75,10,65,83,99,23,86,97,15,96,79,84,25,4,87,29,96,51,43,89,97,16,95,93,86,81,28,91,55,93,75,30,82,68,74,19,96,13,82,91,37,77,65,93,50,31,81,99,19,30,96,69,92,95,65,54,86,95,49,84,68,82,49,21,22,20,90,80,10,51,74,12,90,61,84,87,85,2,91,36,33,91,90,48,83,47,46,99,88,64,78,8,66,26,88,53,81,89,45,62,30,87,36,53,78,2,66,96,97,27,32,93,35,90,70,91,58,4,87,75,45,78,28,5,77,97,58,88,72,10,94,36,78,21,54,91,20,76,87,88,39,93,22,18,77,87,70,70,89,81,32,10,25,97,14,72,89,86,43,37,77,11,81,82,90,49,31,93,56,77,84,81,30,92,32,98,38,41,11,38,78,78,1,28,91,1,97,81,57,74,79,76,50,79,29,97,32,90,88,31,29,82,64,97,39,97,92,40,95,8,29,88,79,96,14,81,92,33,28,24,30,87,61,94,83,72,82,69,97,10,41,85,6,66,85,65,14,82,90,29,77,36,20,72,96,42,81,85,94,46,75,10,54,26,42,83,99,95,47,39,81,92,70,69,86,89,48,38,79,75,7,96,92,26,75,92,26,86,10,17,80,63,90,19,37,15,77,63,54,2,88,92,97,98,47,66,85,67,56,82,72,35,90,81,26,17,1,82,23,28,67,99,5,3,83,2,54,93,53,87,92,64,96,89,91,40,93,77,94,37,89,27,95,38,89,93,7,60,45,97,82,63,66,77,87,16,95,55,99,97,54,77,75,34,65,86,76,85,76,40,71,77,68,49,92,59,64,64,76,45,57,91,40,98,68,42,87,41,93,46,85,91,38,75,29,98,96,58,81,30,78,76,78,40,75,31,21,64,84,65,92,76,76,15,81,77,62,84,65,77,72,29,90,97,65,64,15,98,31,77,30,44,92,97,9,82,1,33,95,1,27,88,3,18,76,84,51,85,90,45,38,98,42,53,95,86,68,10,98,28,87,58,46,99,2,77,21,85,1,35,98,80,77,20,19,92,88,60,86,85,40,75,22,99,72,33,99,11,84,90,42,75,86,38,71,81,87,72,78,93,83,45,20,8,94,14,93,83,64,99,47,9,96,55,8,87,44,84,49,20,85,58,88,3,86,69,57,99,23,83,51,78,93,88,18,85,41,85,72,79,24,64,28,79,51,5,84,17,78,18,27,67,95,90,32,58,8,75,83,98,67,96,35,74,65,9,85,69,94,39,74,7,85,51,67,13,84,30,98,93,40,46,88,97,89,46,61,63,83,81,98,63,69,80,90,95,40,30,83,93,42,85,96,34,83,85,31,30,11,98,5,2,86,92,41,78,31,37,96,41,57,98,66,39,75,81,83,26,10,84,88,61,85,15,36,98,24,72,74,33,94,88,79,69,89,59,97,80,66,42,81,74,99,61,34,96,93,23,95,6,85,79,8,36,80,27,92,17,63,98,92,14,20,98,96,74,47,81,31,59,98,19,57,82,23,88,28,92,87,56,64,67,74,85,68,85,15,39,87,32,88,61,91,8,77,97,97,14,86,62,79,42,98,3,75,9,97,99,80,28,93,44,85,39,92,49,99,17,81,97,91,3,76,37,76,49,86,0,0,21,21,1,10,1,0,0,0,0,0,0";

        BigInteger Z = new BigInteger("0");
        BigInteger N = new BigInteger("1");
        BigInteger S = new BigInteger("2");
        BigInteger W = new BigInteger("3");
        BigInteger E = new BigInteger("4");
        G = new mazeDroid(0,d15);
        //G.setDebug(true);
        //G.debugMem(false);
        G.setManual(true);
        //G.dump();
        /*

    0: The repair droid hit a wall. Its position has not changed.
    1: The repair droid has moved one step in the requested direction.
    2: The repair droid has moved one step in the requested direction; its new position is the location of the oxygen system.

north (1), south (2), west (3), and east (4)
         */

        BigInteger ai[] = {N,E,W,N,E,W,N,E,W,N,E,N,W,N,S,W,N,S,W,N,S,W,W,N};
        pos = 0;
        been[xoff+0][xoff+0]=1;
        dpr(N,"");
        dpr(S,"");
        dpr(E,"");
        dpr(W,"");

        pr();

        // now want to fill with oxygen....
        oxy(0,ox,oy);
        proxy();
    }

    public static int maxs=0;
    public static void oxy(int s,int x,int y)
    {
        // are we at a dead end....
        System.out.println(s + ": OXY: " + x + "," + y + " max=" + maxs);
        s++;
        if (s>maxs) maxs=s;
        int tx=xoff+x;
        int ty=yoff+y;
        filled[tx][ty] = s;
        if ((been[tx+1][ty])==1 && filled[tx+1][ty]==0) oxy(s,x+1,y);
        if ((been[tx-1][ty])==1 && filled[tx-1][ty]==0) oxy(s,x-1,y);
        if ((been[tx][ty+1])==1 && filled[tx][ty+1]==0) oxy(s,x,y+1);
        if ((been[tx][ty-1])==1 && filled[tx][ty-1]==0) oxy(s,x,y-1);
    }

    public static String d(int n)
    {
        switch (n)
        {
            case 1: return "N";
            case 2: return "S";
            case 3: return "W";
            case 4: return "E";
        }
        return "ZERO";
    }
    public static boolean dpr(BigInteger dir, String pad)
    {
        //pr();

        System.out.println("At " + x  +"," + y  + " moving=" + dir + " " + d(dir.intValue())  + " steps=" + steps);
        //getI();
        //pr();
        BigInteger[]input1 = {dir};
        G.setI(input1);
        G.setInputFromOutside(true);
        pos = G.runC(pos);
        int i = (G.getlastoutpout()).intValue();
        System.out.println("Droid says => " + i);
        if (i==2)
        {
            System.out.println("Found Oxygen. Steps=" + steps);
            ox=x;
            oy=y;
            pr();
            //System.exit(1);
        }

        if (i>0)
        {
            // move position.
            switch (dir.intValue())
            {
                case 1: y++;break;
                case 2: y--;break;
                case 3: x--;break;
                case 4: x++;break;
                default: System.out.println("unknownn......");
            }
            been[xoff+x][yoff+y]=1;
            steps++;
            System.out.println(pad.length() + "  now at " + x + "," + y + " steps=" + steps );
//            System.out.println(pad.length() + "S " + screen[xoff+x][yoff+y-1] + " : " + been[xoff+x][yoff+y-1]);
//            System.out.println(pad.length() + "W " + screen[xoff+x-1][yoff+y] + " : " + been[xoff+x-1][yoff+y]);
//            System.out.println(pad.length() + "E " + screen[xoff+x+1][yoff+y] + " : " + been[xoff+x+1][yoff+y]);
//            System.out.println(pad.length() + "N " + screen[xoff+x][yoff+y+1] + " : " + been[xoff+x][yoff+y+1]);

            // if we're in a new place we should try and go everywhere....
            if (screen[xoff+x][yoff+y-1] == 0 && been[xoff+x][yoff+y-1] == 0)
            {
                dpr(S,pad+"   ");
            }
            if (screen[xoff+x+1][yoff+y] == 0 && been[xoff+x+1][yoff+y] == 0)
            {
                dpr(E,pad+"   ");
            }
            if (screen[xoff+x-1][yoff+y] == 0 && been[xoff+x-1][yoff+y] == 0)
            {
                dpr(W,pad+"   ");
            }
            if (screen[xoff+x][yoff+y+1] == 0 && been[xoff+x][yoff+y+1] == 0)
            {
                dpr(N,pad+"   ");
            }

            // then go back...
            int rev=0;
            switch(dir.intValue())
            {
                case 1: rev=2;y--;break;
                case 2: rev=1;y++;break;
                case 3: rev=4;x++;break;
                case 4: rev=3;x--;break;
                default:
            }
            steps--;
            System.out.println(pad.length() + ": Finished: Going back " + rev + " to " + x + "," + y + " steps=" + steps);
            BigInteger[]input2 = {new BigInteger(Integer.toString(rev))};
            G.setI(input2);
            G.setInputFromOutside(true);
            pos = G.runC(pos); // go back....
            System.out.println(pad.length() + " finished " + input1[0]  +  "-->" + i + "<--" + "(" + x + "," + y + ")" + " " + pos + " steps=" + steps);
            return true; // finished....
        }
        else //hit a wall.
        {
            System.out.println(pad.length() + ": Wall...");
            // mark wall on screen...
            switch (dir.intValue())
            {
                case 1: screen[xoff+x][yoff+y+1] = 1;break;
                case 2: screen[xoff+x][yoff+y-1] = 1;break;
                case 3: screen[xoff+x-1][yoff+y] = 1;break;
                case 4: screen[xoff+x+1][yoff+y] = 1;break;
                default: System.out.println("unknownn......");
            }
            return false;
        }
    }


    public static int dp(BigInteger dir)
    {
        BigInteger[]input1 = {dir};
        G.setI(input1);
        G.setInputFromOutside(true);
        pos = G.runC(pos);
        int i = (G.getlastoutpout()).intValue();

        if (i>0)
        {
            // move position.
            switch (dir.intValue())
            {
                case 1: y++;break;
                case 2: y--;break;
                case 3: x--;break;
                case 4: x++;break;
                default: System.out.println("unknownn......");
            }
            been[xoff+x][yoff+y]=1;
            // if we're in a new place we should try and go everywhere....

        }
        else //hit a wall.
        {
            // mark wall on screen...
            switch (dir.intValue())
            {
                case 1: screen[xoff+x][yoff+y+1] = 1;break;
                case 2: screen[xoff+x][yoff+y-1] = 1;break;
                case 3: screen[xoff+x-1][yoff+y] = 1;break;
                case 4: screen[xoff+x+1][yoff+y] = 1;break;
                default: System.out.println("unknownn......");
            }
        }

        System.out.println(input1[0]  +  "-->" + i + "<--" + "(" + x + "," + y + ")" + " " + pos);
        pr();
        return i;
    }
    public static void pr()
    {
        int px=50;
        int py=50;
        int ooh=0;
        for (int r=yoff+py;r>=yoff-30;r--)
        {
            for (int c=xoff+0-35;c<xoff+px;c++)
            {
                if (c==xoff && r==yoff)
                {
                    System.out.print("X");
                }
                else if (been[c][r] == 1)
                {
                    System.out.print('O');
                    ooh++;
                }
                else if (screen[c][r]==1) System.out.print("_");
                else System.out.print(".");
            }
            System.out.println(String.format("%02d",r));
        }
        System.out.println("ooh=" + ooh);
    }
    public static void proxy()
    {
        int px=50;
        int py=50;
        int ooh=0;
        for (int r=yoff+py;r>=yoff-30;r--)
        {
            for (int c=xoff+0-35;c<xoff+px;c++)
            {
                if (c==xoff && r==yoff)
                {
                    System.out.print("X");
                }
                else if (filled[c][r] > 0)
                {
                    System.out.print(String.format("%02d",filled[c][r]%100));
                    ooh++;
                }
                else if (screen[c][r]==1) System.out.print("__");
                else System.out.print("..");
            }
            System.out.println(String.format("%02d",r));
        }
        System.out.println("ooh=" + ooh);
    }
    private static BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
    private static void getI()
    {

            try
            {
                System.out.print("PK ");
                String s = R.readLine();
                if (s.compareTo("P")==0) pr();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.exit(1);
            }
    }
}
