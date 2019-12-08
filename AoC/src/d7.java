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

        int []d = {3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0};
        int []simple_input = new int[]{3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};

        int []tp = {3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0};
        int []inps2 = {0,1,2,3,4};

        int []tp2 = {3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0};
        int []inps = {4,3,2,1,0};

        System.out.println(runE(tp2,inps2));

        par(5,inps);
        int []tp3 = {3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0};
        int []inps3_1 = {1,0,4,2,3};
        int []inps3_2 = {2,0,4,3,1};
        int []inps3_3 = {1,0,4,3,2};
        System.out.println(runE(tp3.clone(),inps3_1.clone()));
        System.out.println(runE(tp3.clone(),inps3_2.clone()));
        System.out.println(runE(tp3.clone(),inps3_3.clone()));


//        int []inps3_1 = {0,0,0,0,5};
//        int []inps3_2 = {0,0,0,0,5};
//        runE(d7.clone(),inps3_1.clone());
//        runE(d7.clone(),inps3_2.clone());


        int []phases = {3,1,2,4,0};
        int max=0;
        int min=100000;
        int maxi = 0;
        int mini = 0;
        for (int c=0;c<perms.size();c++)
        {
            int []iv = perms.get(c);
            int ret = runE(d7.clone(),iv.clone());
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

