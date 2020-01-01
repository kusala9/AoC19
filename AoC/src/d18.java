import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class d18
{
    public static void main(String []arg)
    {
        int [][]mz = new int[100][100];
        HashMap<String,pear<Integer,Integer>> keys = new HashMap<>();
        HashMap<String,pear<Integer,Integer>> doors = new HashMap<>();
        System.out.println("hw");
        File file = new File("d18_mz.txt");
        int ox=0,oy=0;
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            int r=0,c=0;
            while ((line = br.readLine()) != null)
            {
                System.out.println("-->"  + line + "<--");
                for (int i=0;i<line.length();i++)
                {
                    char ch = line.charAt(i);
                    mz[r][i] = ch;
                    switch (ch)
                    {
                        case '@':{ox=i;oy=r;break;}
                        case '#':{break;}
                        case '.':{break;}
                        default:{
                            String s = Character.toString(ch);
                            if (Character.isLowerCase(ch)) keys.put(s,new pear<>(i,r));
                            else if (Character.isUpperCase(ch)) doors.put(s,new pear<>(i,r));
                        }
                    }
                }
                r++;
            }
        }catch (Exception e){e.printStackTrace();}
        System.out.println("dun ix=" + ox + " oy=" + oy);
        for (String s:keys.keySet())
        {
            pear<Integer,Integer> k = keys.get(s);
            pear<Integer,Integer> d = doors.get(s.toUpperCase());
            System.out.println("key=" + s + " x=" + k.first + " y=" + k.second + " dx=" + d.first + " dy=" + d.second);
        }
    }
}
