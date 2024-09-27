import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.border.Border;

public class Game extends JFrame implements KeyListener
{
    private JLabel[] squares;   
    private char key;
    private Border border;
    private Fruit fruit;
    private Snake snake;
    private boolean alive;
    private char old_key;
    private JLabel score_show;

    public Game()
    {
        this.squares = new JLabel[255];
        this.key = 'w';
        this.snake = new Snake();
        this.fruit = new Fruit();

        this.score_show = new JLabel("Score : 0");
        this.score_show.setBounds(770, 0,  70, 60);
        this.score_show.setVisible(true);

        border = BorderFactory.createLineBorder(Color.BLACK, 2);

        this.score_show.setBorder(border);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(850, 790);
        this.setLayout(null);

        this.addKeyListener(this);

        this.initialize_map(this.fruit.get_fruit(), this.snake.get_snake_head());

        this.add(score_show);

        this.setVisible(true);
    }

    public void initialize_map(Coords fruit, Coords head)
    {
        squares = new JLabel[250];
        for(int y = 0;y < 15;y++)
        {
            for(int x = 0;x < 15;x++){
                int pos = (15 * y) + x;
                squares[pos] = new JLabel();
                squares[pos].setBounds(x * 50, y * 50, 50, 50);

                if(fruit.is_collapse(x, y)){
                    squares[pos].setBackground(Color.red);
                }
                else if(head.is_collapse(x, y)){
                    squares[pos].setBackground(Color.gray);
                }
                else{
                    squares[pos].setBackground(Color.white);
                }
                squares[pos].setBorder(border);
                squares[pos].setOpaque(true);
                squares[pos].setVisible(true);
                this.add(squares[pos]);
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        old_key = key;
        key = e.getKeyChar();
    }

    @Override
        public void keyPressed(KeyEvent e) {
    }

    @Override
        public void keyReleased(KeyEvent e) {
    }

    private boolean ate_fruit(Coords snake_head)
    {
        return snake_head.is_collapse(this.fruit.get_fruit());
    }

    private void new_map_changes(Coords snake_head, Coords snake_end, boolean eaten)
    {
        if(eaten){
            Coords fruit;

            this.fruit.set_fruit_status(eaten);

            do{
                fruit = this.fruit.get_fruit();
            }while(this.snake.check_fruit_spawned_on_snake(fruit));

            squares[fruit.get_x() + (fruit.get_y() * 15)].setBackground(Color.red);

            this.snake.grow_snake(snake_end);

            squares[snake_end.get_x() + (snake_end.get_y() * 15)].setBackground(Color.gray);

        }
        else{
            squares[snake_end.get_x() + (snake_end.get_y() * 15)].setBackground(Color.white);
        }
        squares[snake_head.get_x() + (snake_head.get_y() * 15)].setBackground(Color.gray);
    }

    public void start_game()
    {
        Movement move_controller = new Movement();
        this.alive = true;
        boolean eaten_fruit = false;
        short translated_input = 0;
        Coords snake_end;
        this.old_key = 'w';
        short old_input = 0;
        int score = 0;
        while(this.alive)
        {
            old_input = translated_input;
            translated_input = move_controller.get_input(this.key);

            if(old_input - 2 == translated_input || old_input + 2 == translated_input || translated_input == 4){
                key = old_key;
                translated_input = move_controller.get_input(key);
            }

            snake_end = this.snake.move_snake(translated_input);

            this.alive = !this.snake.check_for_collision(this.snake.get_snake_head());

            eaten_fruit = this.ate_fruit(this.snake.get_snake_head());

            if(eaten_fruit){
                score++;
                this.score_show.setText("Score : " + score);
            }

            this.new_map_changes(this.snake.get_snake_head(), snake_end, eaten_fruit);

            try{
                Thread.sleep(350);
            }
            catch(InterruptedException e){
                System.err.println(e.getMessage());
            }
        }

        System.out.println("You lose. Score : " + score);

    }
}
