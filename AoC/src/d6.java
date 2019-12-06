/*
   DOCO:  
*/

import java.util.HashMap;

public class d6
{
    public static void main(String []args)
    {
        System.out.println("Hello world.");
        System.out.println("day6....");
        HashMap<String,Integer> M = new HashMap<>();
        String []d = ("COM)B," +
                "B)C," +
                "C)D," +
                "D)E," +
                "E)F," +
                "B)G," +
                "G)H," +
                "D)I," +
                "E)J," +
                "J)K," +
                "K)L").split(",");
        //String []d = d6data.dat.split(",");

        for (int i=0;i<d.length;i++)
        {
            String []ent = d[i].split("\\)");
            System.out.println("-> " + ent[0] + ":" + ent[1]);


        }



    }

}
