public class d12
{

    /*
        <x=-1, y=0, z=2>
        <x=2, y=-10, z=-7>
        <x=4, y=-8, z=8>
        <x=3, y=5, z=-1>
     */
    public static int [][]moons1 = {{-1,0,2},{2,-10,-7},{4,-8,8},{3,5,-1} };
    public static int [][]velos = {{0,0,0},{0,0,0},{0,0,0},{0,0,0} };

    public static void main(String []a)
    {
        System.out.println("hello word");
        for (int i=0;i<3;i++)
        {
            pr(moons1);
            pr(velos);
            vel(0,1);
            vel(0,2);
            vel(0,3);
            vel(1,2);
            vel(1,3);
            vel(2,3);
            V();
        }

    }

    private static void vel(int i, int j)
    {
        for (int n=0;n<3;n++)
        {
            if (moons1[i][n] < moons1[j][n])
            {
                velos[i][n]++;
                velos[j][n]--;
            }
            else if (moons1[i][n] > moons1[j][n])
            {
                velos[j][n]++;
                velos[i][n]--;
            }
        }
    }

    // fold velocity back into position.
    private static void V()
    {
        for (int i=0;i<4;i++) {
            for (int j = 0; j < moons1[i].length; j++)
            {
                moons1[i][j]+=velos[i][j];
            }
        }
    }

    private static void pr(int [][]M)
    {
        for (int i=0;i<4;i++)
        {
            int []m=M[i];
            for (int j=0;j<m.length;j++)
            {
                if (j>0) System.out.print("\t");
                System.out.print(m[j]);
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }
}

/*

<x=-2, y=9, z=-5>
<x=16, y=19, z=9>
<x=0, y=3, z=6>
<x=11, y=0, z=11>

 */