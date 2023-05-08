package bagel;

public class TestVector 
{
    public static void main(String[] args)
    {
        System.out.println("Testing vector");
        
        Vector v = new Vector(2,3);
        System.out.println(v); // < 2 , 3 >
        
        v.multiply(5);
        v.addValues(800, 900);
        System.out.println(v); // < 810 , 915 >
        
        Vector w = new Vector (1,1);
        System.out.println(w.getLength()); //1.4142135623730951
        System.out.println(w.getAngle()); // 45.0 (deg)
        
        v.setLength(1.414); 
        v.setAngle(45);
        System.out.println(v);
    }
}
