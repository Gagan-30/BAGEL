package bagel;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class Group extends Entity
{
    private ArrayList<Entity> list;
    
    public Group()
    {
        list = new ArrayList<Entity>();
    }
    
    public void add(Entity e)
    {
        list.add(e);
    }
    
    public void remove(Entity e)
    {
        list.remove(e);
    }
    
    public ArrayList<Entity> getList()
    {
        return new ArrayList<Entity>(list);
    }
    
    public int size()
    {
        return list.size();
    }

    @Override
    public void draw(GraphicsContext context) 
    {
        for(Entity e : list)
        {
            e.draw(context);
        }
    }
    
    
}
