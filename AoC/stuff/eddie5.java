import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class eddie5
{
    private int connectedto = 0;

    // where this one gets its inputs from...
    private boolean manual = false;
    public void setManual(boolean b)
    {
        manual=b;
    }

    private ArrayList<BigInteger> alloutputs = new ArrayList<>();
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
        if (m!=null) m.setDebug(v);
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

    // convo
    private int x(BigInteger I)
    {
        return I.intValue();
    }
    private BigInteger X(int i)
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
    // fossil
    //BigInteger []td;
    //int []td ;
    M m;

    private BigInteger peek(int address)
    {
        return peek(X(address));
    }
    private BigInteger peek(BigInteger address)
    {
        if (m!=null) return m.PEEK(address);
        else return null;
    }
    public void poke(BigInteger address,BigInteger value)
    {
        if (m!=null) m.POKE(address,value);
    }
    private BigInteger get(BigInteger address,int mode)
    {
        if (m!=null) return m.get(address,mode);
        else return null;
    }
    private BigInteger get(int address,int mode)
    {
        return get(X(address),mode); // wrapper to the one above...
    }
    public void put(BigInteger address,BigInteger value,int mode)
    {
        if (m!=null) m.put(address,value,mode);
    }
    public eddie5(int name, String prog)
    {
        nm = name;
        m = new M(prog);
        m.setProg(parseprog(prog).clone());
        log("EDDIE mk5 (c) - READY");
    }

    // input stuff....
    private BufferedReader R = new BufferedReader(new InputStreamReader(System.in));

    // logging.
    private void log(String msg)
    {
        if (!debug) return ;
        System.out.println(nm + ":" + pos + ": LOG:: " + msg );
    }

    public void dump()
    {
        if (m!=null) m.dump();
    }
    // global pointer.
    private BigInteger pos;
    private int []modes;
    private BigInteger Z = new BigInteger("0");
    private BigInteger ONE = new BigInteger("1");
    private BigInteger TWO = new BigInteger("2");
    private BigInteger THREE = new BigInteger("3");

    public int runC(int ip)
    {
        pos=X(ip);

        while (true)
        {
            BigInteger p1=pos.add(ONE);
            BigInteger p2=pos.add(TWO);
            BigInteger p3=pos.add(THREE);

            //m.dump();
            int opcode = x(peek(pos));
            modes = getModes(opcode);
            opcode = modes[0];
            log("");
            log("Processing Instruction -> " + peek(pos) + ":" + opcode + " M1=" + modes[1] + " M2=" + modes[2] + " M3=" + modes[3]);

            int n = 0;
            if (opcode==99)
            {
                log("99: EXITING Nicely");
                dun=true;
                break; // done.
            }

            //System.out.println(td[pos] + "," + td[pos+1] + "," + td[pos+2] + "," + td[pos+3]);
            if (opcode==1)
            {
                log("01: ADD O1=" + peek(p1) + " O2=" + peek(p2));
                n = 4;
                BigInteger o1 = get(p1,modes[1]);
                BigInteger o2 = get(p2,modes[2]);
                BigInteger j  = o1.add(o2);
                //int j=vR(pos+1,modes[1]) + vR(pos+2,modes[2]);
                log("Store -> "+ j + " in " + p3 + " m[" + modes[3] + "]");
                put(p3,j,modes[3]);
            }
            else if (opcode==2)
            {
                log("02: MULTIPLY");
                n = 4;
                BigInteger o1 = get(p1,modes[1]);
                BigInteger o2 = get(p2,modes[2]);
                BigInteger j  = o1.multiply(o2);
                log("Store -> "+ j + " in " + p3 + " m[" + modes[3] + "]");
                put(p3,j,modes[3]);
            }
            else if (opcode==3)
            {
                log("03: INPUT");
                n = 2;
                // 203 error.....
                BigInteger add = peek(p1);
                if (modes[1]==2) add = add.add(m.bass);
                log("Storing in " + add + " mode=" + modes[1] + " base=" + m.bass + " raw=" + peek(p1));
                //BigInteger addr = get(p1,modes[1]);
                if (inputpointer==inputs.length)
                {
                    log("NO INPUTS LEFT -> SUSPENDING AT " + pos + " -->" + this.getlastoutpout() + "<--");
                    inputpointer--;
                    return x(pos);
                }
                BigInteger v = getI();
                put(add,v,1); // store is always mode 1 (Immediate).....
            }
            else if (opcode == 4)
            {
                log("04: OUTPUT mode=" + modes[1] + " pos=" + pos + " = " + peek(p1) + ": Operand=" + peek(p1) + "=" + get(p1,modes[1]));
                n = 2;
                BigInteger val = get(p1,modes[1]);
                lastoutput = val;
                alloutputs.add(val);
                log("OUTPUT::  ->" + lastoutput + "<-");
            }
            else if (opcode == 5) // jump if true.
            {
                n=3;
                log("05: JUMP IF TRUE " + peek(p1) + " " + get(p1,modes[1]));

                BigInteger o1 = get(p1,modes[1]);
                if (!o1.equals(Z))
                {
                    BigInteger npos = get(p2,modes[2]);
                    log("JUMPING to " + npos);
                    // just to try (jumps are never indirect?)
                    pos = npos;
                    continue;
                }
            }
            else if (opcode == 6)
            {
                n=3;
                log("06: JUMP IF FALSE " + peek(p1) + " M=" + modes[1] + " to " + peek(p2));
                BigInteger o1 = get(p1,modes[1]);
                if (o1.equals(Z))
                {
                    BigInteger npos = get(p2,modes[2]);
                    log("JUMPING to " + npos);
                    pos = npos;
                    continue;
                }

            }
            else if (opcode == 7)  // less than
            {
                log("07: LESS THAN " + peek(p1) + " " + peek(p2) + ", result in " + peek(p3));
                n = 4;
                BigInteger res ;
                BigInteger o1 = get(p1,modes[1]);
                BigInteger o2 = get(p2,modes[2]);
                if (  o1.compareTo(o2)==-1 ) res = ONE ;
                //if (  vR(pos+1,modes[1]) < vR(pos+2,modes[2]) ) res = 1 ;
                else res = Z;
                log("LESS THAN " + o1 + " " + o2 + " = " + res);
                put(p3,res,modes[3]);
            }
            else if (opcode == 8)
            {
                log("08: EQUALS " + peek(p1) + " " + peek(p2) + ", result in " + peek(p3) );
                n = 4;
                BigInteger res ;
                BigInteger o1 = get(p1,modes[1]);
                BigInteger o2 = get(p2,modes[2]);
                if (  o1.compareTo(o2)==0 ) res = ONE ;
                else res = Z;
                log("EQUALS " + o1 + "=" + o2 + "=" + res );
                put(p3,res,modes[3]);
            }
            else if (opcode == 9)
            {
                BigInteger o1 = get(p1,modes[1]);
                BigInteger cb = m.bass;
                log("09: SET BASS (" + peek(p1) + ")=" + o1 + " currently=" + m.bass + " now=" + cb.add(o1));
                n = 2;
                m.bass = cb.add(o1);
            }
            else
            {
                System.out.println("ERROR: Opcode not found ->" + opcode + "<-" );
                System.exit(1);
            }

            pos = pos.add(BigInteger.valueOf(n));
            log("POS now " + pos);
        }
        //f(td);

        return x(peek(0));
    }

    private int[] getModes(int opcode)
    {
        String opc = String.format("%05d",opcode);
        int []ret = new int[4];
        ret[0] = Integer.parseInt(opc.substring(3,5));
        ret[1] = Integer.parseInt(opc.substring(2,3));
        ret[2] = Integer.parseInt(opc.substring(1,2));
        ret[3] = Integer.parseInt(opc.substring(0,1));
        //log("GETMODES:: " + ret[0] + ":" + ret[1] + "," + ret[2] + "," + ret[3]);

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
    public void dumpAllOutputs()
    {
        for (BigInteger i:this.alloutputs)
        {
            System.out.print(i + " " );
        }
        System.out.println();
    }
}
