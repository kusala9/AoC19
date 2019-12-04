public class d2
{
    /*

    A module of mass 14 requires 2 fuel. This fuel requires no further fuel (2 divided by 3 and rounded down is 0, which would call for a negative fuel), so the total fuel required is still just 2.
    At first, a module of mass 1969 requires 654 fuel. Then, this fuel requires 216 more fuel (654 / 3 - 2). 216 then requires 70 more fuel, which requires 21 fuel, which requires 5 fuel, which requires no further fuel. So, the total fuel required for a module of mass 1969 is 654 + 216 + 70 + 21 + 5 = 966.
    The fuel required by a module of mass 100756 and its fuel is: 33583 + 11192 + 3728 + 1240 + 411 + 135 + 43 + 12 + 2 = 50346.

     */
    public static int f(int j,int rt)
    {
        int n = (j/3)-2;
        System.out.println("   " + n + ":" + rt);
        if (n<0) return rt;
        else return f(n,(n+rt));
    }

    public static void main(String []a)
    {
        int []data = {53035, 84950, 100012, 75128, 96658, 80583, 72951, 131866, 99675, 115923, 65140, 59430, 81288, 53129, 96172, 58912, 138055, 62920, 122353, 59176, 149310, 105061, 58808, 103111, 128538, 61366, 53480, 94427, 121742, 143966, 63092, 92543, 67136, 81575, 131971, 71040, 57035, 114448, 101314, 123688, 137916, 68612, 122453, 98141, 61137, 97628, 126791, 111826, 50630, 67829, 126285, 97331, 88587, 64552, 111221, 89639, 72177, 132711, 51062, 98061, 57919, 57166, 134565, 58677, 62505, 85253, 147337, 84791, 114516, 95597, 139271, 83561, 68285, 100684, 86803, 85887, 74554, 113749, 81899, 107220, 110959, 118220, 52743, 71651, 74775, 106517, 132133, 56919, 129699, 137357, 75781, 59409, 134589, 131438, 101641, 105503, 104371, 145308, 75777, 107333} ;
        //int []td = {12,14,1969,100756};
        System.out.println("hello world...");
        System.out.println("advent challenge 2");

        int m=0;
        for (int i:data)
        {
            int j= f(i,0);
            System.out.println("=" + j);
            m+=j;
        }
        System.out.println("=" + m);
    }
}
