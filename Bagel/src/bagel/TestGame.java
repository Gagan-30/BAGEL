package bagel;

public class TestGame extends Game
{

    @Override
    public void initialize()
    {
        setTitle("Test Game");
        setWindowSize(800, 600);
        System.out.println("Hello, world");
        
    }
    
    public void update()
    {
        
    }
    
    public static void main(String[] args) 
    {
       launch(args);
    }
    
}
