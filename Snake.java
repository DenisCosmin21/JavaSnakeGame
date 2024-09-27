import java.util.LinkedList;
import java.util.Iterator;

public class Snake {

    private LinkedList<Coords> snake_body = new LinkedList<>();

    public Snake()
    {
        this.snake_body.add(new Coords(7, 7));
    }

    public void grow_snake(Coords head)
    {
        this.snake_body.addLast(head);
        /*Coords last_element = this.snake_body.getLast();
        if(last_element.is_collapse_y(14)){
            this.find_grow_position(last_element);
        }
        else{
            this.snake_body.addLast(new Coords(last_element.get_x(), last_element.get_y() + 1));
        }*/
    }

    /*private void find_grow_position(Coords last_element)
    {
        if(last_element.is_collapse_x(0)){
            if(last_element.is_collapse_x(14)){
                this.snake_body.addLast(new Coords(last_element.get_x(), last_element.get_y() - 1));
            }
            else{
                this.snake_body.addLast(new Coords(last_element.get_x() + 1, last_element.get_y()));
            }
        }
        else{
            this.snake_body.addLast(new Coords(last_element.get_x() - 1, last_element.get_y()));
        }
    }*/

    public boolean check_for_collision(Coords head)
    {
        Iterator<Coords> i = this.snake_body.iterator();
        i.next();
        while(i.hasNext()){
            if(head.is_collapse(i.next()) == true){
                return true;
            }
        }
        return false;
    }

    private void check_wall_collision(Coords head)
    {
        if(head.is_collapse_x(-1)){
            head.change_x(14);
        }
        else if(head.is_collapse_x(15)){
            head.change_x(0);
        }
        else if(head.is_collapse_y(-1)){
            head.change_y(14);
        }
        else if(head.is_collapse_y(15)){
            head.change_y(0);
        }
    }

    public Coords move_snake(short input)
    {

        Coords snake_head = this.get_snake_head();

        Coords head = new Coords(snake_head.get_x(), snake_head.get_y());

        Coords end = this.get_snake_end();

        switch(input){
            case 0:
                head.change_y(head.get_y() - 1);
                break;
            case 1:
                head.change_x(head.get_x() + 1);
                break;
            case 2:
                head.change_y(head.get_y() + 1);
                break;
            case 3:
                head.change_x(head.get_x() - 1);
                break;
            default :
                break;
        }

        this.snake_body.addFirst(head);
        this.snake_body.removeLast();

        this.check_wall_collision(head);
        return end;
    }

    public Coords get_snake_head()
    {
        return this.snake_body.getFirst();
    }

    public Coords get_snake_end()
    {
        return this.snake_body.getLast();
    }

    public boolean check_fruit_spawned_on_snake(Coords fruit)
    {
        Iterator<Coords> i = this.snake_body.iterator();
        while(i.hasNext())
        {
            if(i.next().is_collapse(fruit)){
                return true;
            }
        }
        return false;
    }

    public boolean no_valid_input(short translated_input)
    {
        Coords snake_head = this.get_snake_head();
        switch(translated_input){
            case 0:
                return snake_head.is_collapse_y(snake_head.get_y() - 1);
            case 1:
                return snake_head.is_collapse_x(snake_head.get_x() + 1);
            case 2:
                return snake_head.is_collapse_y(snake_head.get_y() + 1);
            case 3:
                return snake_head.is_collapse_x(snake_head.get_x() - 1);
            default:
                return true;
        }
    }
}
