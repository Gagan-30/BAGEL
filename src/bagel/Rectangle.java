package bagel;

import java.util.Arrays;

public class Rectangle 
{
    double left;
    double top;
    double width;
    double height;
    private double right;
    private double bottom;
    
    public Rectangle()
    {
        setValues(0,0,0,0);
    }
    
    public Rectangle(double left, double top,
                    double width, double height)
    {
        setValues(left, top, width, height);
    }
    
    public void setValues(double left, double top,
                          double width, double height)
    {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.right = left + width;
        this.bottom = top + height;
    }
    
    public void setPosition(double left, double top)
    {
        setValues(left, top, this.width, this.height);
    }
    
    public void setSize(double width, double height)
    {
        setValues(this.left, this.top, width, height);
    }
    
    public boolean overLaps(Rectangle other)
    {
        boolean noOverlap = (other.right < this.left)
                || (this.right < other.left)
                || (other.bottom < this.top)
                || (this.bottom < other.top);
        
        return !noOverlap;
    }
    
    public Vector getMinimumTranslationVector(Rectangle other)
    {
        Vector[] differences =
        {
            new Vector(other.right - this.left, 0),
            new Vector(other.left - this.right, 0),
            new Vector(0, other.bottom - this.top),
            new Vector(0, other.top - this.bottom)
        };
        
        Arrays.sort(differences);
        
        return differences[0];
    }
}
