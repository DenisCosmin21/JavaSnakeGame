
class Fruit
{
    private Coords fruit_coords;
    private boolean eaten = true;

    public Fruit()
    { 
        this.fruit_coords = new Coords(0, 0);
        this.generate_fruit();
    }

    private void generate_fruit()
    {
        int x, y;

        do{
            x = (int)(Math.random() * 15);
            y = (int)(Math.random() * 15);
        }while((x == 8) && (y == 8));
        this.fruit_coords.change_x(x);
        this.fruit_coords.change_y(y);
        eaten = false;
    }

    public Coords get_fruit()
    {
        if(eaten == true)
        {
            this.generate_fruit();
        }
        
        return this.fruit_coords;
    }

    public void set_fruit_status(boolean fruit_eaten)
    {
        this.eaten = fruit_eaten;
    }

}