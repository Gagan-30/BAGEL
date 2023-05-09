package bagel;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class Animation 
{
    public ArrayList<Texture> textureList;
    
    public double frameDuration;
    public boolean loop;
    public double elapsedTime;
    
    public Animation()
    {
        textureList = new ArrayList<Texture>();
        frameDuration = 2;
        loop = false;
        elapsedTime = 0;
    }
    
    public Animation(String imageFileName, 
                    int rows,int columns,
                    double frameDuration,
                    boolean loop)
    {
        textureList = new ArrayList<Texture>();
        Image image = new Image (imageFileName);
        double frameWidth = image.getWidth() / columns;
        double frameHeight = image.getHeight() / rows;
        for (int y = 0; y < rows; y++) 
        {
            for (int x = 0; x < columns; x++) 
            {
                Texture tex = new Texture();
                tex.image = image;
                int frameX = x * (int)frameWidth;
                int frameY = y * (int)frameHeight;
                tex.region = new Rectangle(
                        frameX, frameY,
                        frameWidth, frameHeight);
                textureList.add(tex);
            }
        }
        
        this.frameDuration = frameDuration;
        this.loop = loop;
        this.elapsedTime = 0;
    }
    
    public Texture getCurrentTexture()
    {
        int textureIndex = (int)Math.floor(elapsedTime / frameDuration);
        
        if(textureIndex >= textureList.size())
            textureIndex = textureList.size() - 1;
        
        return textureList.get(textureIndex);
    }
    
    public void update(double dt)
    {
        elapsedTime += dt;
        
        if(loop && (elapsedTime > frameDuration * textureList.size()))
            elapsedTime = 0;
    }
}
