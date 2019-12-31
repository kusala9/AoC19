/*
   DOCO:  
*/

import java.math.BigInteger;
import java.util.ArrayList;

public class d17
{
    public static void pr()
    {
        int sm=0;
        System.out.print("    ");
        for (int r1=0;r1<50;r1++) System.out.print(r1%10);
        System.out.println("");
        for (int r=0;r<50;r++)
        {
            System.out.print(String.format("%02d |",r));
            for (int c=0;c<50;c++)
            {
                switch (screen[r][c])
                {
                    case 1:
                        {
                            if ( (r>0&&c>0) && (screen[r][c+1]==1 &&
                                screen[r][c-1]==1 &&
                                screen[r+1][c]==1 &&
                                screen[r-1][c]==1))
                            {
                                System.out.print("O");
                                sm += (r*c);
                            }
                            else
                                System.out.print("#");
                            break;
                        }
                    case 0: {System.out.print(".");break;}
                    default: {System.out.print(".");break;}
                }
            }
            System.out.println("| " + sm);
        }
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

    public static int[] fS(String s)
    {
        int []ret = new int[s.length()+1];
        for (int i=0;i<s.length();i++)
        {
            ret[i]=s.charAt(i);
        }
        ret[s.length()]=10;
        return ret;
    }
    public static BigInteger[] tB(int []in)
    {
        BigInteger []out = new BigInteger[in.length];
        for (int i=0;i<in.length;i++)
        {
            out[i] = BigInteger.valueOf(in[i]);
        }
        return out;
    }
    public static int [][]screen = new int[80][80];

    /*
     after much deliberation....

     A = R,6 L,10 R,10 R,10    L,10 L,12 R,10

     B = R,6 L,10 R,10 R,10                     R,6 L,12 L,10

     C =                       L,10 L,12 R,10   R,6 L,10 L,10

     A,A,B,B,C

     D = R,6 L,10 R,10 R,10
     E = L,10 L,12 R,10
     F = R,6 L,12 L,10

     D,E,D,E,D,F,D,F,E,F

     */
    public static void main(String []arg)
    {
        System.out.println("hwd17...");

        String d17="2,330,331,332,109,3788,1102,1,1182,15,1102,1485,1,24,1002,0,1,570,1006,570,36,1001,571,0,0,1001,570,-1,570,1001,24,1,24,1105,1,18,1008,571,0,571,1001,15,1,15,1008,15,1485,570,1006,570,14,21101,58,0,0,1105,1,786,1006,332,62,99,21101,0,333,1,21102,73,1,0,1106,0,579,1102,1,0,572,1101,0,0,573,3,574,101,1,573,573,1007,574,65,570,1005,570,151,107,67,574,570,1005,570,151,1001,574,-64,574,1002,574,-1,574,1001,572,1,572,1007,572,11,570,1006,570,165,101,1182,572,127,102,1,574,0,3,574,101,1,573,573,1008,574,10,570,1005,570,189,1008,574,44,570,1006,570,158,1106,0,81,21102,1,340,1,1106,0,177,21102,1,477,1,1106,0,177,21101,0,514,1,21101,176,0,0,1106,0,579,99,21101,184,0,0,1105,1,579,4,574,104,10,99,1007,573,22,570,1006,570,165,1001,572,0,1182,21102,1,375,1,21101,0,211,0,1105,1,579,21101,1182,11,1,21102,1,222,0,1105,1,979,21102,388,1,1,21101,0,233,0,1105,1,579,21101,1182,22,1,21102,244,1,0,1105,1,979,21102,401,1,1,21101,0,255,0,1106,0,579,21101,1182,33,1,21101,0,266,0,1106,0,979,21102,1,414,1,21102,277,1,0,1106,0,579,3,575,1008,575,89,570,1008,575,121,575,1,575,570,575,3,574,1008,574,10,570,1006,570,291,104,10,21102,1182,1,1,21102,1,313,0,1105,1,622,1005,575,327,1101,1,0,575,21101,0,327,0,1106,0,786,4,438,99,0,1,1,6,77,97,105,110,58,10,33,10,69,120,112,101,99,116,101,100,32,102,117,110,99,116,105,111,110,32,110,97,109,101,32,98,117,116,32,103,111,116,58,32,0,12,70,117,110,99,116,105,111,110,32,65,58,10,12,70,117,110,99,116,105,111,110,32,66,58,10,12,70,117,110,99,116,105,111,110,32,67,58,10,23,67,111,110,116,105,110,117,111,117,115,32,118,105,100,101,111,32,102,101,101,100,63,10,0,37,10,69,120,112,101,99,116,101,100,32,82,44,32,76,44,32,111,114,32,100,105,115,116,97,110,99,101,32,98,117,116,32,103,111,116,58,32,36,10,69,120,112,101,99,116,101,100,32,99,111,109,109,97,32,111,114,32,110,101,119,108,105,110,101,32,98,117,116,32,103,111,116,58,32,43,10,68,101,102,105,110,105,116,105,111,110,115,32,109,97,121,32,98,101,32,97,116,32,109,111,115,116,32,50,48,32,99,104,97,114,97,99,116,101,114,115,33,10,94,62,118,60,0,1,0,-1,-1,0,1,0,0,0,0,0,0,1,0,12,0,109,4,1202,-3,1,587,20101,0,0,-1,22101,1,-3,-3,21101,0,0,-2,2208,-2,-1,570,1005,570,617,2201,-3,-2,609,4,0,21201,-2,1,-2,1106,0,597,109,-4,2106,0,0,109,5,2101,0,-4,630,20102,1,0,-2,22101,1,-4,-4,21102,0,1,-3,2208,-3,-2,570,1005,570,781,2201,-4,-3,653,20101,0,0,-1,1208,-1,-4,570,1005,570,709,1208,-1,-5,570,1005,570,734,1207,-1,0,570,1005,570,759,1206,-1,774,1001,578,562,684,1,0,576,576,1001,578,566,692,1,0,577,577,21102,1,702,0,1105,1,786,21201,-1,-1,-1,1106,0,676,1001,578,1,578,1008,578,4,570,1006,570,724,1001,578,-4,578,21102,731,1,0,1105,1,786,1106,0,774,1001,578,-1,578,1008,578,-1,570,1006,570,749,1001,578,4,578,21102,756,1,0,1105,1,786,1105,1,774,21202,-1,-11,1,22101,1182,1,1,21101,774,0,0,1105,1,622,21201,-3,1,-3,1105,1,640,109,-5,2106,0,0,109,7,1005,575,802,21002,576,1,-6,20101,0,577,-5,1106,0,814,21101,0,0,-1,21101,0,0,-5,21102,1,0,-6,20208,-6,576,-2,208,-5,577,570,22002,570,-2,-2,21202,-5,49,-3,22201,-6,-3,-3,22101,1485,-3,-3,1202,-3,1,843,1005,0,863,21202,-2,42,-4,22101,46,-4,-4,1206,-2,924,21101,0,1,-1,1105,1,924,1205,-2,873,21101,0,35,-4,1106,0,924,2101,0,-3,878,1008,0,1,570,1006,570,916,1001,374,1,374,2102,1,-3,895,1102,1,2,0,1201,-3,0,902,1001,438,0,438,2202,-6,-5,570,1,570,374,570,1,570,438,438,1001,578,558,921,21001,0,0,-4,1006,575,959,204,-4,22101,1,-6,-6,1208,-6,49,570,1006,570,814,104,10,22101,1,-5,-5,1208,-5,47,570,1006,570,810,104,10,1206,-1,974,99,1206,-1,974,1101,0,1,575,21101,0,973,0,1105,1,786,99,109,-7,2105,1,0,109,6,21102,0,1,-4,21102,0,1,-3,203,-2,22101,1,-3,-3,21208,-2,82,-1,1205,-1,1030,21208,-2,76,-1,1205,-1,1037,21207,-2,48,-1,1205,-1,1124,22107,57,-2,-1,1205,-1,1124,21201,-2,-48,-2,1106,0,1041,21101,0,-4,-2,1105,1,1041,21102,1,-5,-2,21201,-4,1,-4,21207,-4,11,-1,1206,-1,1138,2201,-5,-4,1059,2101,0,-2,0,203,-2,22101,1,-3,-3,21207,-2,48,-1,1205,-1,1107,22107,57,-2,-1,1205,-1,1107,21201,-2,-48,-2,2201,-5,-4,1090,20102,10,0,-1,22201,-2,-1,-2,2201,-5,-4,1103,1202,-2,1,0,1106,0,1060,21208,-2,10,-1,1205,-1,1162,21208,-2,44,-1,1206,-1,1131,1106,0,989,21102,439,1,1,1106,0,1150,21101,477,0,1,1105,1,1150,21101,514,0,1,21101,0,1149,0,1106,0,579,99,21101,0,1157,0,1105,1,579,204,-2,104,10,99,21207,-3,22,-1,1206,-1,1138,1201,-5,0,1176,2101,0,-4,0,109,-6,2105,1,0,26,11,38,1,9,1,18,11,9,1,9,1,18,1,9,1,9,1,9,1,18,1,9,1,9,1,3,11,14,1,9,1,9,1,3,1,5,1,3,1,14,1,9,1,9,1,3,1,5,11,8,1,9,1,9,1,3,1,9,1,5,1,8,1,9,1,1,11,1,1,9,1,5,1,8,1,9,1,1,1,7,1,1,1,1,1,9,1,5,1,8,1,1,13,5,1,1,1,1,1,9,1,5,1,8,1,1,1,7,1,1,1,1,1,5,1,1,1,1,1,9,1,5,1,2,7,1,1,7,11,1,1,1,1,9,1,5,1,10,1,9,1,1,1,7,1,1,1,9,1,5,1,10,1,9,1,1,1,7,13,5,1,10,1,9,1,1,1,9,1,15,1,10,1,9,1,1,11,5,11,10,1,9,1,17,1,20,1,9,11,7,1,20,1,19,1,7,1,20,1,19,1,7,1,40,1,7,1,40,1,7,1,40,1,7,1,40,11,46,1,1,1,46,13,38,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,32,7,3,7,32,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,38,1,9,1,38,11,6";
        for (int i1=0;i1<80;i1++)
        {
            for (int i2=0;i2<80;i2++)
            {
                screen[i1][i2] = 0;
            }
        }
        BigInteger Z = new BigInteger("0");
        BigInteger N = new BigInteger("1");
        mazeDroid G = new mazeDroid(0,d17);

        //String A = "R,6,L,10,R,10,R,10,L,10,L,12,R,10";
        //String B = "R,6,L,10,R,10,R,10,R,6,L,12,L,10";
        //String C = "L,10,L,12,R,10,R,6,L,10,L,10";

        String A = "R,6,L,10,R,10,R,10";
        String B = "L,10,L,12,R,10";
        String C = "R,6,L,12,L,10";
        String main = "A,B,A,B,A,C,A,C,A,C";
        String pmpt = "y";
        int []a = fS(A);
        int []b = fS(B);
        int []c2 = fS(C);
        int []mn = fS(main);
        int []pm = fS(pmpt);
        int []inp = new int[a.length+b.length+ c2.length+ mn.length+pm.length];
        int c3=0;
        int n=0;
        n=0;for (int x:mn) inp[c3++]=mn[n++];
        n=0;for (int x:a) inp[c3++]=a[n++];
        n=0;for (int x:b) inp[c3++]=b[n++];
        n=0;for (int x:c2) inp[c3++]=c2[n++];
        n=0;for (int x:pm) inp[c3++]=pm[n++];

        System.out.println("A=" + mkX(a));
        System.out.println("B=" + mkX(b));
        System.out.println("C=" + mkX(c2));
        System.out.println("PM=" + mkX(pm));
        System.out.println("MN=" + mkX(mn));
        System.out.println("   " + mkX(inp));

        //System.exit(0);
        G.setInputFromOutside(true);
        G.setDebug(false);
        G.debugMem(false);
        G.setManual(true);
        G.setI(tB(inp));

        int pos=0;
        pos = G.runC(pos);
        //pr();
        //G.dumpAllOutputs();
        ArrayList<BigInteger> ret = G.getAlloutputs();
        int r=0;int c=0;
        for (int i=0;i<ret.size();i++)
        {
            int v = ret.get(i).intValue();
            System.out.print(String.format("%c",v));
            switch (v)
            {
                case 35: {
                    //screen[r][c] = 1;
                    break;
                }
                case 10:{r++;c=-1;break;}
            }
            c++;
        }
        System.out.println("=================FINISHED======================");
    }

}
