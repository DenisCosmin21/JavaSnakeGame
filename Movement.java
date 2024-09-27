public class Movement {

    private short input;

    public Movement()
    {
        this.input = 0;
    }

    private void translate_from_key(char key_pressed)
    {
        switch(key_pressed){
            case 'w':
                this.input = 0;
                break;
            case 'd':
                this.input = 1;
                break;
            case 's':
                this.input = 2;
                break;
            case 'a':
                this.input = 3;
                break;
            default:
                this.input = 4;
                break;
        }
    }

    public short get_input(char key)
    {
        this.translate_from_key(key);
        return this.input;
    }
}
