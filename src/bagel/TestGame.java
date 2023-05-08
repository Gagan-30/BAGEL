package bagel;

public class TestGame extends Game
{
    
    Sprite ball;

    @Override
    public void initialize()
    {
        setTitle("Test Game");
        setWindowSize(800, 600);
        
        ball = new Sprite();
        ball.setPosition(10, 10);
        ball.setTexture(Texture.load("images/basketball.png"));
        ball.setSize(50, 50);
        group.add(ball);
        
    }
    
    @Override
    public void update()
    {
       // ball.position.addValues(2, 1);
        if(input.isKeyPressed("RIGHT"))
           ball.position.addValues(2, 0);
        if(input.isKeyPressed("LEFT"))
           ball.position.addValues(-2, 0);
        if(input.isKeyPressed("UP"))
           ball.position.addValues(0, -2);
        if(input.isKeyPressed("DOWN"))
           ball.position.addValues(0, 2);
        if(input.isKeyPressed("Z"))
           ball.position.addValues(0, 0);
    }
    
    public static void main(String[] args) 
    {
       launch(args);
    }
    
}
