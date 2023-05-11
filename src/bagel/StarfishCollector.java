package bagel;

import javafx.scene.paint.Color;

public class StarfishCollector extends Game 
{

    Sprite water;
    Group starfishGroup;
    Sprite turtle;
    Group rockGroup;
    Sprite shark;
    Sprite fish;
    Label starfishLabel;
    Label winLabel;
     
    
    @Override
    public void initialize()
    {
       setTitle("Starfish Collector");
       setWindowSize(800, 600);
       
       water = new Sprite();
       water.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/water.png"));
       water.setPosition(400, 300);
       group.add(water);

       rockGroup = new Group();
       Texture rockTexture = new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/rock.png");
       int rockCount = 3;
        for (int i = 0; i < rockCount; i++) 
        {
            Sprite rock = new Sprite();
            double x = Math.random() * 600 + 100;
            double y = Math.random() * 450 + 100;
            rock.setPosition(x, y);
            rock.setTexture(rockTexture);
            rockGroup.add(rock);
        }
        group.add(rockGroup);
        
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
            
            boolean rockOverlap = false;
            do 
            {  
                rockOverlap = false;
                x = Math.random() * 600 + 100;
                y = Math.random() * 450 + 100;
                starfish.setPosition(x, y);
                for(Entity entity : rockGroup.getList())
                {
                    Sprite rock = (Sprite) entity;
                    if(rock.overlaps(starfish))
                    {
                        rockOverlap = true;
                    }
                }
            } 
            while (rockOverlap);
            
            starfish.addAction(Action.rotateBy(360, 4));
            starfish.addAction(Action.moveBy(50, 0, 2));
            
            starfishGroup.add(starfish);
        }
        group.add(starfishGroup);
        
        turtle = new Sprite();
        turtle.setPosition(50, 50);
        turtle.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/turtle.png"));
        turtle.setPhysics(new Physics(600,200,600));
        group.add(turtle);
        
        shark = new Sprite();
        shark.setPosition(400, 300);
        shark.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/shark.png"));
        group.add(shark);
        
        fish = new Sprite();
        fish.setPosition(100, 400);
        Animation fishAnim = new Animation("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/fish.png",
                                            8, 1, 0.1, true);
        fish.setAnimation(fishAnim);
        group.add(fish);
       
        
        
        starfishLabel = new Label("Comic Sans MS", 48);
        String text = "Starfish left: " + starfishGroup.size();
        starfishLabel.setText(text);
        starfishLabel.setPosition(780, 580);
        starfishLabel.alignment = "RIGHT";
        starfishLabel.fontColor = Color.YELLOW;
        starfishLabel.setBorder(2, Color.BLACK);
        group.add(starfishLabel);
        
        winLabel = new Label("Comic Sans MS", 80);
        winLabel.setText("You Win!");
        winLabel.fontColor = Color.GREEN;
        winLabel.setBorder(2, Color.BLACK);
        winLabel.setPosition(400, 300);
        winLabel.alignment = "CENTER";
        winLabel.visible = false;
        group.add(winLabel);
               
    }

    @Override
    public void update() 
    {
        if (winLabel.visible)
            return;
        
        if (input.isKeyPressed("RIGHT")) 
            turtle.physics.accelerateAtAngle(0);
        
        if (input.isKeyPressed("LEFT")) 
            turtle.physics.accelerateAtAngle(180);
        
        if (input.isKeyPressed("UP")) 
            turtle.physics.accelerateAtAngle(270);
        
        if (input.isKeyPressed("DOWN")) 
            turtle.physics.accelerateAtAngle(90);
        
        if(turtle.physics.getSpeed() > 0)
            turtle.setAngle(turtle.physics.getMotionAngle());
        
        if(turtle.position.x < shark.position.x)
            shark.mirrored = true;
        
        if(turtle.position.x > shark.position.x)
            shark.mirrored = false;

        for(Entity entity : starfishGroup.getList())
        {
            Sprite starfish = (Sprite)entity;
            if (turtle.overlaps(starfish)) 
            {
                starfishGroup.remove(starfish);
                String text = "Starfish left: " + starfishGroup.size();
                starfishLabel.setText(text);
            }
        }
        
        for(Entity entity : rockGroup.getList())
        {
            Sprite rock = (Sprite)entity;
            turtle.preventOverlap(rock); //switch turtle and rock to push rocks
        }
        
        if (starfishGroup.size() == 0) 
        {
            winLabel.visible = true;
            fish.animation.paused = true;
        }

        turtle.boundToScreen(800, 600);
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
