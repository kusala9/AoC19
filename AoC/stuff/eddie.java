import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class eddie
{
    public enum mode
    {
        IMMEDIATE,POSITION
    } // ha ha, don't need these.... one is the same as the other.

    int []td ;
    private mode M;
    public void setMode(mode m)
    {
        M = m;
    }
    public void setProg(int []p)
    {
        td = p;
    }

    public eddie(int []prog)
    {
        M = mode.POSITION;
        setProg(prog);
    }

    private int vR(int p,int mode)
    {
        if (mode ==0) return td[td[p]];
        else return td[p];
    }
    private void sR(int pos,int val,int mode)
    {
        if (mode==0) td[td[pos]] = val;
        else td[pos] = val;
    }
    private void s(int pos,int val, int mode)
    {
        if (mode == 0) td[pos] = val;
    }
    private int v(int p, int mode)
    {
        if (mode ==0) return td[p];
        else return p;
    }


    private BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
    private void log(String msg)
    {
        System.out.println("LOG:: " + msg);
    }

    public int runC(int noun,int verb)
    {
//        td[1] = noun;
//        td[2] = verb;
        int pos=0;
        while (true)
        {
            //dump();
            int opcode = td[pos];
            int []modes = getModes(opcode);
            opcode = modes[0];

            int n = 0;
            if (opcode==99)
            {
                log("EXITING Nicely");
                break; // done.
            }

            //System.out.println(td[pos] + "," + td[pos+1] + "," + td[pos+2] + "," + td[pos+3]);
            if (opcode==1)
            {
                log("ADD");
                n = 4;
                int j=vR(pos+1,modes[1]) + vR(pos+2,modes[2]);
                sR(pos+3,j,modes[3]);
            }
            else if (opcode==2)
            {
                log("MULTIPLY");
                n = 4;
                int j=vR(pos+1,modes[1]) * vR(pos+2,modes[2]);
                sR(pos+3,j,modes[3]);
            }
            else if (opcode==3)
            {
                log("INPUT");
                n = 2;
                int a = v(pos+1,modes[1]);
                int v = getI();
                s(a,v,0);
            }
            else if (opcode == 4)
            {
                log("LOG");
                n = 2;
                int a = v(pos+1,modes[1]);
                System.out.println("OUTPUT::  ->" + v(a,0) + "<-");
            }
            else if (opcode == 5) // jump if true.
            {
                n=3;
                log("JUMP IF TRUE " + vR(pos+1,modes[1]));
                if (vR(pos+1,modes[1])!=0)
                {
                    log("JUMP" + vR(pos+2,modes[2]));
                    pos = vR(pos+2,modes[2]);
                    continue;
                }
            }
            else if (opcode == 6)
            {
                n=3;
                log("JUMP IF FALSE " + vR(pos+1,modes[1]));
                if (vR(pos+1,modes[1])==0)
                {
                    log("JUMP" + vR(pos+2,modes[2]));
                    pos = vR(pos+2,modes[2]);
                    continue;
                }

            }
            else if (opcode == 7)  // less than
            {
                n = 4;
                int res ;
                if (  vR(pos+1,modes[1]) < vR(pos+2,modes[2]) ) res = 1 ;
                else res = 0;
                sR(pos+3,res,modes[3]);
            }
            else if (opcode == 8)
            {
                log("EQUALS");
                n = 4;
                int res ;
                if (  vR(pos+1,modes[1]) == vR(pos+2,modes[2]) ) res = 1 ;
                else res = 0;
                sR(pos+3,res,modes[3]);
            }
            else
            {
                System.out.println("ERROR: Opcode not found" + opcode);
                System.exit(1);
            }
            pos+=n;
            log("POS now " + pos);
        }
        //f(td);

        return td[0];
    }

    private int[] getModes(int opcode)
    {
        String opc = String.format("%05d",opcode);
        int []ret = new int[4];
        ret[0] = Integer.parseInt(opc.substring(3,5));

        ret[1] = Integer.parseInt(opc.substring(2,3));
        ret[2] = Integer.parseInt(opc.substring(1,2));
        ret[3] = Integer.parseInt(opc.substring(0,1));
        System.out.println("PRC:: " + ret[0] + ":" + ret[1] + "," + ret[2] + "," + ret[3]);
        return ret;
    }

    public void dump()
    {
        System.out.println("==============DUMP============");
        for (int i=0;i<td.length;i++)
        {
            if (i>0) System.out.print(" ");
            System.out.print(td[i]);
            if ((i+1)%20 == 0) System.out.println();
        }
        System.out.println("\n==============DUMP============");
    }

    private int getI()
    {
        System.out.print("input a num => ");
        try
        {
            String s = R.readLine();
            return Integer.parseInt(s);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return 0;
    }

    public void f(int []d)
    {
        System.out.print("{");
        for(int i:d) System.out.print(i+",");
        System.out.println("}");
    }
}
