/*
   DOCO:  
*/

public class d7
{
    public static void main(String []a)
    {
        System.out.println("hello world...");
        System.out.println("advent challenge d7");

        int []d = {3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0};
        int []simple_input = new int[]{3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99};
        int []tp = {3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0};
        int []I = {8,2,3};

        eddie2 E = new eddie2(simple_input.clone());
        E.setI(I);

        int ret = E.runC(0,0);


        System.out.println("lastoutput=" + E.getlastoutpout());


    }

}
