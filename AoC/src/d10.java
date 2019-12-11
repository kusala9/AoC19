import java.util.ArrayList;
import java.util.HashMap;

public class d10
{
    public static void main(String []a)
    {
        ArrayList<String> m = d10d.getd();
        pear<Integer,Integer> p = new pear<Integer,Integer>(1,0);
        HashMap<String,Integer> m2 = new HashMap<>();

        for (int i=0;i<m.size();i++)
        {
            String s = m.get(i);
            for (int j=0;i<s.length();j++)
            {
                if (s.substring(j,j+1).compareTo("#")==0)
                {
                    System.out.println("A at(" + i + "," + j);
                }
            }
        }


    }
}
