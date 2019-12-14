import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

public class d12
{

    /*
<x=-2, y=9, z=-5>
<x=16, y=19, z=9>
<x=0, y=3, z=6>
<x=11, y=0, z=11>
     */
//    public static int [][]moons1 = {{-1,0,2},{2,-10,-7},{4,-8,8},{3,5,-1} };
    public static int [][]moons1 = {{-2,9,-5},{16,19,9},{0,3,6},{11,0,11} };

    public static int [][]velos = {{0,0,0},{0,0,0},{0,0,0},{0,0,0} };

    public static void main(String []a)
    {
        HashMap<String,Integer> mp = new HashMap<String,Integer>();
        ArrayList<Integer>      nl = new ArrayList<Integer>();

        PrintWriter out=null;
        try
        {
            out = new PrintWriter("d12_e.txt");
        }catch (Exception e){e.printStackTrace();}

        int [][] Morigin = moons1;
        int [][] Vorigin = velos;
        int []buf = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println("hello word");
        int B=20000;
        int B2=10;
        for (int i=0;i<300000;i++)
        {
            vel(0,1);
            vel(0,2);
            vel(0,3);
            vel(1,2);
            vel(1,3);
            vel(2,3);
            V();
            int e = e();
//            int ax = sumAxis(0);
//            nl.add(ax);

            int axis=2;
            if (i>4)
            {
                buf[0]=moons1[0][axis];
                buf[1]=moons1[1][axis];
                buf[2]=moons1[2][axis];
                buf[3]=moons1[3][axis];
            }
            String s = md52(buf);
            String s2="";
            if (mp.containsKey(s))
            {
                s2 = i + " Mtch " + s + "=" + mp.get(s);
                System.out.println(s2);
            }else s2="";
            mp.put(s,i);
            out.println(i + "," + moons1[0][axis] +"," +  moons1[1][axis] + "," + moons1[2][axis] + "," + moons1[3][axis] + "," + "," + s + "," + s2);

            //if ( (i+1)%B == 0) pr(String.format("%03d",i+1));
        }
        out.close();
        //prSeq(32581,nl);

    }

    public static void prSeq(int offs,ArrayList<Integer> vals)
    {
        for (int k=0;k<20;k++)
        {
            System.out.print(vals.get(k+offs) + ",");
        }
    }

    private static String md52(int []seq)
    {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] md5hash = new byte[32];
        try {
            md.update(gb(seq));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(md.digest());
    }
    private static byte[] gb(int []listInt)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (int i = 0; i < listInt.length; i++)
        {
            baos.write(intToByteArray(listInt[i]), 0, 4);
        }
        return baos.toByteArray();
    }
    public static final byte[] intToByteArray(int value)
    {
        return new byte[]  { (byte)(value >>> 24), (byte)(value >> 16 & 0xff), (byte)(value >> 8 & 0xff), (byte)(value & 0xff) };
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

    private static int sumAxis(int i)
    {
        return A(moons1[0][i])+A(moons1[1][i])+A(moons1[2][i])+A(moons1[3][i]);
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

    private static int pr(String msg)
    {
        System.out.println("=============Steps=" + msg + "========================");
        int []e = {0,0,0,0};
        for (int i=0;i<4;i++)
        {
            System.out.println("<" + moons1[i][0] + "\t" + moons1[i][1]+ "\t" + moons1[i][2]+ ">\t\t\t  <" + velos[i][0]+ "\t" + velos[i][1]+ "\t" + velos[i][2]+ ">");
            e[i] = (A(moons1[i][0])+A(moons1[i][1])+A(moons1[i][2]))*
                    (A(velos[i][0])+A(velos[i][1])+A(velos[i][2]));


            //            e[i]=(A(moons1[i][0]*velos[i][0])) +
            //                    A((moons1[i][1]*velos[i][1])) +
            //                    A((moons1[i][2]*velos[i][2]));
        }
        int E = e[0]+e[1]+e[2]+e[3];

        System.out.println("=================E=" + String.format("%04d",E) + "=======================");
        return E;
    }
    private static int e()
    {
        int []e = {0,0,0,0};
        for (int i=0;i<4;i++)
        {
            e[i] = (A(moons1[i][0])+A(moons1[i][1])+A(moons1[i][2]))*
                    (A(velos[i][0])+A(velos[i][1])+A(velos[i][2]));
        }
        return e[0]+e[1]+e[2]+e[3];
    }
    private static pear<Integer,Integer> e2()
    {
        int [][]e = {{0,0,0,0},{0,0,0,0}};
        for (int i=0;i<4;i++)
        {
            e[0][i] = (A(moons1[i][0])+A(moons1[i][1])+A(moons1[i][2]));
            e[1][i] = (A(velos[i][0])+A(velos[i][1])+A(velos[i][2]));
        }
        int spot = e[0][0]+e[0][1]+e[0][2]+e[0][3];
        int skin = e[1][0]+e[1][1]+e[1][2]+e[1][3];
        return new pear<Integer,Integer>(spot,skin);
    }

    private static int A(int i)
    {
        return Math.abs(i);
    }
}

/*

<x=-2, y=9, z=-5>
<x=16, y=19, z=9>
<x=0, y=3, z=6>
<x=11, y=0, z=11>

 */