import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class d14
{
    public static void main(String []a)
    {
        HashMap<String,String> dict = new HashMap<>();
        File file = new File("d14_1.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
            {
                // process the line.
                Pattern p = Pattern.compile("=>");
                Matcher m = p.matcher(line);
                if (m.matches())
                {
                    String part1 = m.group(1);
                    String part2 = m.group(2);
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
