import java.util.Arrays;

public class Coords {
    private short x;
    private short y;

    public Coords(int x, int y)
    {
        this.x = (short)x;
        this.y = (short)y;
    }

    public boolean is_collapse(Coords obstacle_coords)
    {
        return (this.x == obstacle_coords.get_x()) && (this.y == obstacle_coords.get_y());
    }
    
    public boolean is_collapse(int x, int y)
    {
        return (this.x == x) && (this.y == y);
    }

    public boolean is_collapse_x(int x)
    {
        return this.x == x;
    }

    public boolean is_collapse_y(int y)
    {
        return this.y == y;
    }

    public short get_x()
    {
        return this.x;
    }

    public short get_y()
    {
        return this.y;
    }

    public void show_coords()
    {
        short coords[] = new short[]{this.x, this.y};
        System.out.println(Arrays.toString(coords));
    }

    public static void show_coords(Coords to_show_coords)
    {
        to_show_coords.show_coords();
    }

    public void change_x(int x)
    {
        this.x = (short)x;
    }

    public void change_y(int y)
    {
        this.y = (short)y;
    }
}
