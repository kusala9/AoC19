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
                String []f =  line.split("=>");
                Pattern p = Pattern.compile("=>");
                
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
