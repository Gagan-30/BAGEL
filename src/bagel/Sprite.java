package bagel;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class Sprite extends Entity 
{
    public Vector position;
    public Rectangle boundary;
    public Texture texture;
    public double width;
    public double height;
    public double angle; //degrees
    public boolean visible;
    public boolean mirrored; //x direction
    public boolean flipped; //y direction
    public double opacity;
    
    public Physics physics;
    public Animation animation;
    public ArrayList<Action> actionList;
    
    public Sprite()
    {
        position = new Vector();
        angle = 0;
        texture = new Texture();
        boundary = new Rectangle();
        visible = true;
        mirrored = false;
        flipped = false;
        opacity = 1;
        physics = null;
        animation = null;
        actionList = new ArrayList<Action>();
    }
    
    public void setPosition(double x, double y)
    {
        position.setValues(x,y);
        boundary.setPosition(x, y);
    }
    
    public void moveBy(double dx, double dy)
    {
        position.addValues(dx, dy);
    }
    
    public void setAngle(double a)
    {
        angle = a;
    }
    
    public void rotateBy(double da)
    {
        angle += da;
    }
    
    public void moveAtAngle(double dist, double a)
    {
        //angle a is in degrees
        double A = Math.toRadians(a);
        double dx = dist * Math.cos(A);
        double dy = Math.sin(A);
        moveBy(dx,dy);
    }
    
    public void moveForward(double dist)
    {
        moveAtAngle(dist, angle);
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
    
    public void setPhysics(Physics phys)
    {
        physics = phys;
    }
    
    public void setAnimation(Animation anim)
    {
        animation = anim;
        width = anim.getCurrentTexture().region.width;
        height = anim.getCurrentTexture().region.height;
        boundary.setSize(width, height);
    }
    
    public void addAction(Action a)
    {
        actionList.add(a);
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
    
    public void preventOverlap(Sprite other)
    {
        if(this.overlaps(other))
        {
            Vector mtv = this.getBoundary().getMinimumTranslationVector(other.getBoundary());
            this.position.addVector(mtv);
        }
    }
    
    public void boundToScreen(int screenWidth, int screenHeight)
    {
        if (position.x < width/2) 
            position.x = width/2; 
        if (position.y < height/2) 
            position.y = height/2; 
        if (position.x + width/2 > screenWidth) 
            position.x = screenWidth - width/2; 
        if (position.y + height/2 > screenHeight) 
            position.y = screenHeight - height/2; 
    }
    
    @Override
    public void update(double dt)
    {
        if(physics != null)
        {
            physics.position.setValues(
                    this.position.x, this.position.y);
            physics.update(dt);
            this.position.setValues(
                    physics.position.x, physics.position.y);
        }
        
        if(animation != null)
        {
            animation.update(dt);
            texture = animation.getCurrentTexture();
        }
        
        ArrayList<Action> actionListCopy = new ArrayList<Action>(actionList);
        for (Action a : actionListCopy)
        {
            boolean finished = a.apply(this, dt);
            if (finished)
                actionList.remove(a);
        }
    }
    
    @Override
    public void draw(GraphicsContext context) 
    {
        if(!this.visible)
            return;
        
        double A = Math.toRadians(angle);
        double cosA = Math.cos(A);
        double sinA = Math.sin(A);
        
        double scaleX = 1;
        if(mirrored)
        {
            scaleX = -1;
        }
        
        double scaleY = 1;
        if(flipped)
        {
            scaleY = -1;
        }
        
        context.setTransform(
                scaleX * cosA, scaleX * sinA,
                scaleY * (-sinA), scaleY * cosA, 
                position.x, position.y);
        
        context.setGlobalAlpha(opacity);
        
       context.drawImage(texture.image,
               texture.region.left, texture.region.top,
               texture.region.width, texture.region.height,
               -this.width / 2, -this.height / 2,
               this.width, this.height);
    }

    
}
