/*
   DOCO:  
*/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class M
{
    // data access functions.
    BigInteger []td;
    HashMap<BigInteger,BigInteger> xM = new HashMap<>();

    // consts....
    private BigInteger Z = new BigInteger("0");
    private BigInteger ONE = new BigInteger("1");

    // convo
    private int x(BigInteger I)
    {
        return I.intValue();
    }
    private BigInteger X(int i)
    {
        return new BigInteger(Integer.toString(i));
    }

    public BigInteger bass = new BigInteger("0");
    public void setBass(BigInteger i)
    {
        bass = i;
    }

    public M(String prog)
    {
        ;
    }

    public BigInteger maxAddress = new BigInteger("0");
    public void setProg(BigInteger []p)
    {
        td = p;
        maxAddress = X(td.length);
    }

    // get the thing which is at....
    public BigInteger PEEK(BigInteger address)
    {
        BigInteger ret = null;
        BigInteger v = Get(address);
        return v;
    }
    public void POKE(BigInteger address,BigInteger value)
    {
        Put(address,value);
    }
    public BigInteger Resolve(BigInteger address,int mode)
    {
        log("RESOLVE: Address=" + address + " mode=" + mode);
        if (mode ==0)
        {
            return Get(address);
        }
        else if (mode==1)
        {
            return address;
        }
        else if (mode==2)
        {
            BigInteger thingata = Get(address);
            BigInteger a = bass.add(thingata);
            return a;
        }
        else
        {
            System.out.println("RESOLVE: ERROR: Invalid mode=" + mode);
            System.exit(1);
        }
        return null;
    }

    // this JUST retrives from memory (no mode resolution).
    private BigInteger Get(BigInteger i)
    {
        if (i.compareTo(maxAddress)==1 || i.compareTo(maxAddress)==0)
        {
            if (xM.containsKey(i)) return xM.get(i);
            else
            {
                xM.put(i,new BigInteger("0"));
                log("GET: Getting value at " + i + "=" + Z);
                return Z;
            }
        }
        else
        {
            log("GET: Getting value at " + i + "=" + td[x(i)]);
            return td[x(i)];
        }
    }
    private void Put(BigInteger i,BigInteger v)
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

    // the public interface...
    public void put(BigInteger addr, BigInteger v,int mode)
    {
        BigInteger realaddress = Resolve(addr,mode);
        Put(realaddress,v);
    }
    public BigInteger get(BigInteger addr,int mode)
    {
        BigInteger realaddress = Resolve(addr,mode);
        return Get(realaddress);
    }

    public BigInteger ck()
    {
        BigInteger ret = new BigInteger("0");
        for (BigInteger i:td) ret.add(i);
        return ret;
    }

    public void dump()
    {
        System.out.println("==============RMEM============");
        BigInteger rt=new BigInteger("0");
        System.out.print("000:");
        for (int i=0;i<td.length;i++)
        {
            rt= rt.add(td[i]);
            if (i>0) System.out.print(" ");
            System.out.print(td[i]);
            if ((i+1)%20 == 0) System.out.print(String.format("\n%03d: ",i));
        }
        System.out.println("\n" + String.format("%05d",rt) + "=========XMEM============");
        for (BigInteger x:xM.keySet())
        {
            System.out.print(x + "=" + xM.get(x) + ",");
        }
        System.out.println("==============XMEM============");
    }
    private void log(String msg)
    {
        if (!debug) return ;
        System.out.println("      MEM:: LOG:: " + msg );
    }
    private boolean debug=false;
    public void setDebug(boolean v)
    {
        debug = v;
    }

}
