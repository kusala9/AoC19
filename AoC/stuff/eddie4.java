import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;

public class eddie4
{
    private int connectedto = 0;

    // where this one gets its inputs from...
    private boolean manual = false;
    public void setManual(boolean b)
    {
        manual=b;
    }

    HashMap<BigInteger,BigInteger> xM = new HashMap<>();
    private int nm = 0;
    private boolean debug=false;
    private boolean uselastoutput=false;
    private BigInteger lastoutput = new BigInteger("0");
    public BigInteger getlastoutpout()
    {
        return lastoutput;
    }

    private BigInteger []inputs ;
    private int inputpointer = 0;

    public void setI(BigInteger []i)
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
    private boolean dun = false;
    public boolean isDun()
    {
        return dun;
    }

    public BigInteger bass = new BigInteger("0");
    public void setBass(BigInteger i)
    {
        bass = i;
    }
    public enum mode
    {
        IMMEDIATE,POSITION
    } // ha ha, don't need these.... one is the same as the other.

    // convo
    public int x(BigInteger I)
    {
        return I.intValue();
    }
    public BigInteger X(int i)
    {
        return new BigInteger(Integer.toString(i));
    }

    public static BigInteger[]parseprog(String s)
    {
        String []f = s.split(",");
        BigInteger []f2 = new BigInteger[f.length];
        int c=0;
        for (String s2:f)
        {
            f2[c++] = new BigInteger(s2);
        }
        return f2;
    }

    // main memory - stores program.
    BigInteger []td;
    //int []td ;
    private mode M;
    public void setMode(mode m)
    {
        M = m;
    }

    public BigInteger maxAddress = new BigInteger("0");
    public void setProg(BigInteger []p)
    {
        td = p;
        maxAddress = X(td.length);
    }

    public eddie4(int name, String prog)
    {
        nm = name;
        M = mode.POSITION;
        setProg(parseprog(prog).clone());
    }

    // data access functions.
    private BigInteger Get(BigInteger i)
    {
        if (i.compareTo(maxAddress)==1 || i.compareTo(maxAddress)==0)
        {
            if (xM.containsKey(i)) return xM.get(i);
            else
            {
                xM.put(i,new BigInteger("0"));
                //log("GET: Getting value at " + i + "=" + Z);
                return Z;
            }
        }
        else
        {
            //log("GET: Getting value at " + i + "=" + td[x(i)]);
            return td[x(i)];
        }
    }
    private BigInteger get(int i)
    {
        // these are only really used in mode 2...
        return Get(X(i));
    }
    private void p(BigInteger i,BigInteger v)
    {
        log("PUT: value=" + v + " in address=" + i);
        if (i.compareTo(maxAddress)==1 || i.compareTo(maxAddress)==0)
        {
            xM.put(i,v);
        }
        else
        {
            td[x(i)] = v;
        }
    }
    private void put(int addr, BigInteger v)
    {
        p(X(addr),v);
    }

    private BigInteger vR(int p,int mode)
    {
        log(mode + ": Fetching value mode=" + mode + " address=" + p + "=(" + Get(X(p)) +")");
        if (mode ==0)
        {
            BigInteger vv = get(x(get(p)));
            //return td[x(td[p])];
            return vv;
        }
        else if (mode==1)
        {
            return get(p);
        }
        else if (mode==2)
        {
            BigInteger ra = get(p);
            BigInteger address = bass.add(ra);
            BigInteger x = Get(address);
            return x;
        }
        else
        {
            System.out.println("vR: ERROR: Invalid mode=" + mode);
            System.exit(1);
        }
        return null;
    }


    private void sR(int pos,BigInteger val,int mode)
    {
        if (mode==0)
        {
            p(get(pos),val);
        }
        else if (mode==0)
        {
            System.exit(1);
        }
        else
        {
            System.out.println("sR: ERROR: Invalid mode=" + mode);
            System.exit(1);
        }
    }
    private void s(int pos,BigInteger val, int mode)
    {
        if (mode == 0)
        {
            put(pos,val);
        }
        else if (mode == 1){System.exit(1);}
        else
        {
            System.out.println("s: ERROR: Invalid mode=" + mode);
            System.exit(1);
        }
    }

    private BigInteger v(int p, int mode)
    {
        log(mode + "   Now at " + pos + "  M=" + mode + " fetching contents of address " + p + " M=" + mode);
        if (mode ==0)
        {
            log("Simple Fetch: " + p + " m=" + mode);
            BigInteger k = Get(X(p));
            return k;
        }
        else if (mode==1)
        {
            return Get(X(p));
        }
        else if (mode==2)
        {
            BigInteger ra = td[p];
            BigInteger address = bass.add(ra);
            BigInteger x = Get(address);
            return x;
        }
        else
        {
            System.out.println("v: ERROR: Invalid mode=" + mode);
            System.exit(1);
        }
        return null;
    }

    // input stuff....
    private BufferedReader R = new BufferedReader(new InputStreamReader(System.in));


    // logging.
    private void log(String msg)
    {
        if (!debug) return ;
        System.out.println(nm + ":" + pos + ": LOG:: " + msg );
//        for (int i=0;i<8;i++)
//        {
//            if ((pos+i)<td.length)
//            {
//                int n = pos+i;
//                //System.out.print(" "  + n+ "=" + td[pos+i]);
//                System.out.print(" "+  td[pos+i]);
//
//                //                if (td[i]<td.length) System.out.print(" [" + td[td[i]] + "],");
////                else System.out.print(",");
//            }
//        }
        //System.out.println(")");
    }

    // global pointer.
    private int pos;
    private int []modes;
    private BigInteger Z = new BigInteger("0");
    private BigInteger ONE = new BigInteger("1");
    public int runC(int ip)
    {
        pos=ip;

        while (true)
        {
            log("Processing Instruction -> " + td[pos]);
            dump();
            int opcode = x(td[pos]);
            modes = getModes(opcode);
            opcode = modes[0];
            log("Processing Instruction -> " + td[pos] + " M1=" + modes[1] + " M2=" + modes[2] + " M3=" + modes[3]);

            int n = 0;
            if (opcode==99)
            {
                log("EXITING Nicely");
                dun=true;
                break; // done.
            }

            //System.out.println(td[pos] + "," + td[pos+1] + "," + td[pos+2] + "," + td[pos+3]);
            if (opcode==1)
            {
                log("ADD O1=" + td[pos+1] + " O2=" + td[pos+2]);
                n = 4;
                BigInteger o1 = vR(pos+1,modes[1]);
                BigInteger o2 = vR(pos+2,modes[2]);
                BigInteger j  = o1.add(o2);
                //int j=vR(pos+1,modes[1]) + vR(pos+2,modes[2]);
                int y=pos+3;
                log("Store -> "+ j + " in " + y + " m[" + modes[3] + "]");
                sR(y,j,modes[3]);
            }
            else if (opcode==2)
            {
                log("MULTIPLY");
                n = 4;
                BigInteger o1 = vR(pos+1,modes[1]);
                BigInteger o2 = vR(pos+2,modes[2]);
                BigInteger j  = o1.multiply(o2);
                //int j=vR(pos+1,modes[1]) * vR(pos+2,modes[2]);
                log("Store -> "+ j + " in " + pos+3 + " m[" + modes[3] + "]");
                sR(pos+3,j,modes[3]);
                dump();
            }
            else if (opcode==3)
            {
                log("INPUT");
                n = 2;
                BigInteger a = v(pos+1,modes[1]);
                if (inputpointer==inputs.length)
                {
                    log("NO INPUTS LEFT -> SUSPENDING AT " + pos + " -->" + this.getlastoutpout() + "<--");
                    inputpointer--;
                    return pos;
                }
                BigInteger v = getI();
                s(x(a),v,0);
            }
            else if (opcode == 4)
            {
                log("OUTPUT mode=" + modes[1] + " pos=" + pos + " Operand=" + get(pos+1));
                n = 2;
                BigInteger val = vR(pos+1,modes[1]);
                lastoutput = val;
                log("OUTPUT::  ->" + lastoutput + "<-");
            }
            else if (opcode == 5) // jump if true.
            {
                n=3;
                log("JUMP IF TRUE " + vR(pos+1,modes[1]));

                BigInteger o1 = vR(pos+1,modes[1]);
                if (!o1.equals(Z))
                {
                    log("JUMPING to " + vR(pos+2,modes[2]));
                    // just to try (jumps are never indirect?)
                    pos = x(vR(pos+2,modes[2]));
                    continue;
                }
            }
            else if (opcode == 6)
            {
                n=3;
                log("JUMP IF FALSE " + td[pos+1] + " M=" + modes[1] + " to " + td[pos+2]);
                BigInteger o1 = vR(pos+1,modes[1]);
                if (o1.equals(Z))
                {
                    log("JUMPING to " + vR(pos+2,modes[2]));
                    pos = x(vR(pos+2,modes[2]));
                    continue;
                }

            }
            else if (opcode == 7)  // less than
            {
                log("LESS THAN " + td[pos+1] + " " + td[pos+2] + ", result in " + td[pos+3]);
                n = 4;
                BigInteger res ;
                BigInteger o1 = vR(pos+1,modes[1]);
                BigInteger o2 = vR(pos+2,modes[2]);
                if (  o1.compareTo(o2)==-1 ) res = ONE ;
                //if (  vR(pos+1,modes[1]) < vR(pos+2,modes[2]) ) res = 1 ;
                else res = Z;
                sR(pos+3,res,modes[3]);
            }
            else if (opcode == 8)
            {
                log("EQUALS " + td[pos+1] + " " + td[pos+2] + ", result in " + td[pos+3] );
                n = 4;
                BigInteger res ;
                BigInteger o1 = vR(pos+1,modes[1]);
                BigInteger o2 = vR(pos+2,modes[2]);
                if (  o1.compareTo(o2)==0 ) res = ONE ;
                else res = Z;
                sR(pos+3,res,modes[3]);
            }
            else if (opcode == 9)
            {
                BigInteger o1 = vR(pos+1,modes[1]);
                log("SET BASS => " + o1);
                n = 2;
                bass = o1;
            }
            else
            {
                System.out.println("ERROR: Opcode not found ->" + opcode + "<-" );
                System.exit(1);
            }
            pos+=n;
            log("POS now " + pos);
        }
        //f(td);

        return x(td[0]);
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
        BigInteger rt=new BigInteger("0");
        for (int i=0;i<td.length;i++)
        {
            rt= rt.add(td[i]);
            if (i>0) System.out.print(" ");
            System.out.print(td[i]);
            if ((i+1)%20 == 0) System.out.println();
        }
        System.out.println("\n" + rt + "==============DUMP============");
        for (BigInteger x:xM.keySet())
        {
            System.out.print(x + "=" + xM.get(x) + ",");
        }
        System.out.println("==============DUMP============");
    }

    public BigInteger ck()
    {
        BigInteger ret = new BigInteger("0");
        for (BigInteger i:td) ret.add(i);
        return ret;
    }

    private BigInteger getI()
    {
        if (inputs==null)
        {
            try
            {
                System.out.print("input a num => ");
                String s = R.readLine();
                return new BigInteger(s);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.exit(1);
            }
        }
        else
        {
            BigInteger v = inputs[inputpointer];
            inputpointer++;
            log("Input ==>" + v + "<==");
            return v;
        }
        return new BigInteger("0");
    }

    public void f(int []d)
    {
        System.out.print("{");
        for(int i:d) System.out.print(i+",");
        System.out.println("}");
    }
}
