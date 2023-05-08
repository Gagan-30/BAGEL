package bagel;

import javafx.scene.canvas.GraphicsContext;

public class Sprite extends Entity 
{
    public Vector position;
    
    public Rectangle boundary;
    
    public Texture texture;
    
    public double width;
    
    public double height;
    
    public boolean visible;

    public Sprite()
    {
        position = new Vector();
        texture = new Texture();
        boundary = new Rectangle();
        visible = true;
    }
    
    public void setPosition(double x, double y)
    {
        position.setValues(x,y);
        boundary.setPosition(x, y);
    }
    
    public void setTexture(Texture tex)
    {
        texture = tex;
        width = texture.region.width;
        height = texture.region.height;
        boundary.setSize(width, height);
    }
    
    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
        boundary.setSize(width, height);
    }
    
    public Rectangle getBoundary()
    {
        boundary.setPosition(position.x, position.y);
        return boundary;
    }
    
    public boolean overlaps(Sprite other)
    {
        return this.getBoundary().overLaps(other.getBoundary());
    }
    
    @Override
    public void draw(GraphicsContext context) 
    {
        if(!this.visible)
            return;
        
        context.setTransform(1,0, 0,1, 
                position.x, position.y);
        
       context.drawImage(texture.image,
               texture.region.left, texture.region.top,
               texture.region.width, texture.region.height,
               0, 0,
               this.width, this.height);
    }
    
    
}
