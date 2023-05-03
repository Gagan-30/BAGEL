package bagel;

public class Rectangle 
{
    private double left;
    private double top;
    private double width;
    private double height;
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
    
    public boolean overLaps(Rectangle other)
    {
        boolean noOverlap = (other.right < this.left)
                || (this.right < other.left)
                || (other.bottom < this.top)
                || (this.bottom < other.top);
        
        return !noOverlap;
    }
}
