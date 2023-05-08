package bagel;

public class StarfishCollector extends Game 
{

    Sprite water;
    Group starfishGroup;
    Sprite turtle;
    Sprite win;
    
    @Override
    public void initialize()
    {
       setTitle("Starfish Collector");
       setWindowSize(800, 600);
       
       water = new Sprite();
       water.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/water.png"));
       group.add(water);
       
       starfishGroup = new Group();
       Texture starfishTexture = new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/starfish.png");
       int starfishCount = 20;
        for (int i = 0; i < starfishCount; i++) 
        {
            Sprite starfish = new Sprite();
            double x = Math.random() * 600 + 100;
            double y = Math.random() * 450 + 100;
            starfish.setPosition(x, y);
            starfish.setTexture(starfishTexture);
            starfishGroup.add(starfish);
        }
        group.add(starfishGroup);
        
        turtle = new Sprite();
        turtle.setPosition(50, 50);
        turtle.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/turtle.png"));
        group.add(turtle);
        
        win = new Sprite();
        win.setPosition(250,200);
        win.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/youWin.png"));
        win.visible = false;
        group.add(win);
        
    }

    @Override
    public void update() 
    {
        if (win.visible)
        {
            return;
        }
        if (input.isKeyPressed("RIGHT")) 
        {
            turtle.position.addValues(2, 0);
        }
        if (input.isKeyPressed("LEFT")) 
        {
            turtle.position.addValues(-2, 0);
        }
        if (input.isKeyPressed("UP")) 
        {
            turtle.position.addValues(0, -2);
        }
        if (input.isKeyPressed("DOWN")) 
        {
            turtle.position.addValues(0, 2);
        }
        
        for(Entity entity : starfishGroup.getList())
        {
            Sprite starfish = (Sprite)entity;
            if (turtle.overlaps(starfish)) 
            {
                starfishGroup.remove(starfish);
            }
        }
        
        if (starfishGroup.size() == 0) 
            win.visible = true;
        
        turtle.boundToScreen(800, 600);
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
