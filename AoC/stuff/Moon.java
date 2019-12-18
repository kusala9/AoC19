public class Moon
{
    public int x = 0;
    public int y = 0;
    public int z = 0;

    public int vx = 0;
    public int vy = 0;
    public int vz = 0;

    public Moon(int _x,int _y,int _z)
    {
        x = _x;y = _y; z = _z;
    }

    public String toString()
    {
        return "(" + this.x + "," + this.y + "," + this.z + ":" + this.vx + "," + this.vy + "," + this.vz + ")";
    }
}
