package bagel;

public class Physics 
{
    public Vector position;
    public Vector velocity;
    public Vector acceleration;
    
    public double accelerationValue;
    public double maximumSpeed;
    public double decelerationValue;
    
    public Physics(double accValue,
                   double maxSpeed,
                   double decValue)
    {
        position = new Vector();
        velocity = new Vector();
        acceleration = new Vector();
        accelerationValue = accValue;
        maximumSpeed = maxSpeed;
        decelerationValue = decValue;
    }
    
    public double getSpeed()
    {
        return velocity.getLength();
    }
    
    public void setSpeed(double speed)
    {
        velocity.setLength(speed);
    }
    
    public double getMotionAngle()
    {
        return velocity.getAngle();
    }
    
    public void setMotionAngle(double angleDeg)
    {
        velocity.setAngle(angleDeg);
    }
    
    public void accelerateBy(double amount, double angle)
    {
        Vector a = new Vector();
        a.setLength(amount);
        a.setAngle(angle);
        acceleration.addVector(a);
    }
    
    public void accelerateAtAngle(double angle)
    {
        accelerateBy(accelerationValue, angle);
    }
    
    public void update(double dt)
    {
        //apply acceleration
        velocity.addValues(acceleration.x * dt,
                           acceleration.y * dt);
        
        double speed = getSpeed();
        
        //decrease speed when not acceleration
        if(acceleration.getLength() < 0.001)
        {
            speed -= decelerationValue * dt;
        }
        
        //keep speed in set boundarys
        if(speed < 0)
        {
            speed = 0;
        }
        if(speed > maximumSpeed)
        {
            speed = maximumSpeed;
        }
        
        setSpeed(speed);
        
        position.addValues(velocity.x * dt,
                           velocity.y * dt);
        
        acceleration.s
    }
}
