public class eddie
{
    int []td ;
    public void setProg(int []p)
    {
        td = p;
    }

    public eddie(int []prog)
    {
        setProg(prog);
    }

    private int vR(int p)
    {
        return td[td[p]];
    }
    private void sR(int pos,int val)
    {
        td[td[pos]] = val;
    }
    private int v(int p)
    {
        return td[p];
    }
    public int runC(int noun,int verb)
    {
        td[1] = noun;
        td[2] = verb;
        int pos=0;
        while (true)
        {
            int opcode = td[pos];
            int n = 0;
            if (opcode==99) break; // done.

            //System.out.println(td[pos] + "," + td[pos+1] + "," + td[pos+2] + "," + td[pos+3]);
            if (opcode==1)
            {
                n = 4;
                int j=vR(pos+1) + vR(pos+2);
                sR(pos+3,j);
            }
            else if (opcode==2)
            {
                n = 4;
                int j=vR(pos+1) * vR(pos+2);
                sR(pos+3,j);
            }
            else if (opcode==3)
            {
                n = 2;
            }
            else if (opcode == 4)
            {
                n = 2;
            }
            else
            {
                System.out.println("ERROR: Opcode not found");
                System.exit(1);
            }
            pos+=4;
        }
        //f(td);

        return td[0];
    }
    public void f(int []d)
    {
        System.out.print("{");
        for(int i:d) System.out.print(i+",");
        System.out.println("}");
    }
}
