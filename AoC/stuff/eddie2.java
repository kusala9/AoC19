import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class eddie2
{
    private boolean debug=false;
    private boolean uselastoutput=false;
    private int lastoutput = 0;
    public int getlastoutpout()
    {
        return lastoutput;
    }
    private int []inputs ;
    private int inputpointer = 0;
    public void setI(int []i)
    {
        inputs=i;
    }
    public void setDebug(boolean v)
    {
        debug = v;
    }
    public void setUseLastOutput(boolean v)
    {
        uselastoutput = v;
    }
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

    public eddie2(int []prog)
    {
        M = mode.POSITION;
        setProg(prog);
    }

    private int vR(int p,int mode)
    {
        if (mode ==0)
        {
            log(mode + "   -> " + pos + ":" + mode + " fetching contents of " + td[p] + "=" + td[td[p]]);
            return td[td[p]];
        }
        else
        {
            log(mode + "   -> " + pos + ":" + mode + " Fetching contents of " + p + "=" + td[p]);
            return td[p];
        }
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
        if (!debug) return ;
        System.out.print(pos + ": LOG:: " + msg + "(");
        for (int i=0;i<8;i++)
        {
            if ((pos+i)<td.length)
            {
                int n = pos+i;
                //System.out.print(" "  + n+ "=" + td[pos+i]);
                System.out.print(" "+  td[pos+i]);

                //                if (td[i]<td.length) System.out.print(" [" + td[td[i]] + "],");
//                else System.out.print(",");
            }
        }
        System.out.println(")");
    }
    private int pos;
    private int []modes;

    public int runC(int noun,int verb)
    {
//        td[1] = noun;
//        td[2] = verb;
        pos=0;

        while (true)
        {
            log("Processing Instruction -> " + td[pos]);
            //dump();
            int opcode = td[pos];
            modes = getModes(opcode);
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
                log("Store -> "+ j + " in " + pos+3 + " m[" + modes[3]);
                sR(pos+3,j,modes[3]);
            }
            else if (opcode==2)
            {
                log("MULTIPLY");
                n = 4;
                int j=vR(pos+1,modes[1]) * vR(pos+2,modes[2]);
                log("Store -> "+ j + " in " + pos+3 + " m[" + modes[3]);
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
                log("OUTPUT");
                n = 2;
                int a = v(pos+1,modes[1]);
                lastoutput = v(a,0);
                log("OUTPUT::  ->" + v(a,0) + "<-");
            }
            else if (opcode == 5) // jump if true.
            {
                n=3;
                log("JUMP IF TRUE " + vR(pos+1,modes[1]));
                if (vR(pos+1,modes[1])!=0)
                {
                    log("JUMPING to " + vR(pos+2,modes[2]));
                    // just to try (jumps are never indirect?)
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
                log("LESS THAN");
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
        log("GETMODES:: " + ret[0] + ":" + ret[1] + "," + ret[2] + "," + ret[3]);
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
        if (inputs==null)
        {
            try
            {
                System.out.print("input a num => ");
                String s = R.readLine();
                return Integer.parseInt(s);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.exit(1);
            }
        }
        else
        {
            int v = inputs[inputpointer];
            inputpointer++;
            log("Input ==>" + v + "<==");
            return v;
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
