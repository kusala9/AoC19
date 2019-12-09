/*
   DOCO:  
*/

import java.util.ArrayList;
import java.util.Set;

public class d7
{
    private static ArrayList<int []> perms = new ArrayList<>();

    public static void par(int n, int[] elements)
    {
        if (n == 1)
        {
            //printArray(elements, delimiter);
            //System.out.println("->" );
            perms.add(elements.clone());
        }
        else
            {
                for(int i = 0; i < n-1; i++)
                {
                    par(n - 1, elements);
                    if(n % 2 == 0)
                     swap(elements, i, n-1);
                 else
                    swap(elements, 0, n-1);
                }
                par(n - 1, elements);
            }
    }
    private static void swap(int[] input, int a, int b)
    {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    public static int[] ivfromI(int i)
    {
        String s = String.format("%05d",i);
        int []iv = {Integer.parseInt(s.substring(0,1)),
                Integer.parseInt(s.substring(1,2)),
                Integer.parseInt(s.substring(2,3)),
                Integer.parseInt(s.substring(3,4)),
                Integer.parseInt(s.substring(4,5)),
        };
        return iv;
    }

    public static String pr(int []v)
    {
        String ret="";
        for (int i=0;i<v.length;i++)
        {
            ret+=v[i];
        }
        return ret;
    }

    public static int runE(int []prog, int []phases)
    {
        int ret =0;
        int []inps = {0,0};
        for (int i=0;i<phases.length;i++)
        {
            inps[0] = phases[i];
            eddie2 E = new eddie2(i,prog.clone());
            //E.dump();
            //E.setDebug(true);
            E.setI(inps.clone());
            E.runC(0,0);
            inps[1] = E.getlastoutpout();
            ret = E.getlastoutpout();
        }
        return ret;
    }

    public static int runA(int []prog, int []phases)
    {
        int ret =0;
        int []inps = {0,0};
        int []runfrom = {0,0,0,0,0};

        int []inps1 = {phases[0],0};
        int []inps2 = {phases[1],0};
        int []inps3 = {phases[2],0};
        int []inps4 = {phases[3],0};
        int []inps5 = {phases[4],0};
        eddie3 amp1 = new eddie3(1,prog.clone());
        eddie3 amp2 = new eddie3(2,prog.clone());
        eddie3 amp3 = new eddie3(3,prog.clone());
        eddie3 amp4 = new eddie3(4,prog.clone());
        eddie3 amp5 = new eddie3(5,prog.clone());

//        amp1.setDebug(true);
//        amp2.setDebug(true);
//        amp3.setDebug(true);
//        amp4.setDebug(true);
//        amp5.setDebug(true);

        for (int i=0;i<20;i++)
        {
            amp1.setI(inps1);
            runfrom[0] = amp1.runC(runfrom[0]);
            inps2[1]   = amp1.getlastoutpout();

            amp2.setI(inps2);

            runfrom[1] = amp2.runC(runfrom[1]);
            inps3[1]   = amp2.getlastoutpout();

            amp3.setI(inps3);

            runfrom[2] = amp3.runC(runfrom[2]);
            inps4[1]   = amp3.getlastoutpout();

            amp4.setI(inps4);

            runfrom[3] = amp4.runC(runfrom[3]);
            inps5[1]   = amp4.getlastoutpout();

            amp5.setI(inps5);

            runfrom[4] = amp5.runC(runfrom[4]);
            inps1[1]   = amp5.getlastoutpout();

            System.out.println("FINISHED INTERATION" + " " + amp1.ck() +" " +  amp2.ck() + " " + amp3.ck() +" " +  amp4.ck() + " " + amp5.ck());

            if (amp1.isDun()&&amp2.isDun()&&amp3.isDun()&&amp4.isDun()&&amp5.isDun())
            {
                System.out.println("FINISHED ALL AMPS => " + amp5.getlastoutpout());
                ret = amp5.getlastoutpout();
                break;
            }
        }
        return ret;
    }
    public static void main(String []a)
    {
        System.out.println("hello world...");
        System.out.println("advent challenge d7");

        int []d7 = {3,8,
                1001,8,10,8,
                105,1,0,0,21,
                42,55,76,89,114,195,276,357,438,99999,
                3,9,
                1001,9,3,9,
                1002,9,3,9,
                1001,9,3,9,
                1002,9,2,9,
                4,9,99,3,9,102,2,9,9,101,5,9,9,4,9,99,3,9,102,3,9,9,101,5,9,9,1002,9,2,9,101,4,9,9,4,9,99,3,9,102,5,9,9,1001,9,3,9,4,9,99,3,9,1001,9,4,9,102,5,9,9,1001,9,5,9,1002,9,2,9,101,2,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,99};

        int []d72 = {3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5};
        int []snip = {5,6,7,8,9};

        par(5,snip);

        int max=0;
        int min=100000;
        int maxi = 0;
        int mini = 0;
        for (int c=0;c<perms.size();c++)
        {
            int []iv = perms.get(c);
            int ret = runA(d7.clone(),iv.clone());
            if (ret>max)
            {
                maxi=c;
                max=ret;
            }
            if (ret<min)
            {
                mini=c;
                min=ret;
            }
            System.out.println(String.format("%05d",c) + ": CODE=>" + ret);
        }
        System.out.println("max=" + max + " min=" + min + " maxc=" + pr(perms.get(maxi)));
    }

}

