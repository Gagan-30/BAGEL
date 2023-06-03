package bagel;

public class Asteroids extends Game {
    
    public Sprite background;
    public Sprite spaceship;
    public Sprite shields;
    public Group rockGroup;
    public Group laserGroup;
    public Texture laserTex;
    public Texture rockTex; 
    public int rockCount = 6;
    public Animation explosionAnim;
    
    @Override
    public void initialize() {
        
        setTitle("Asteroids");
        setWindowSize(800, 600);
        
        background = new Sprite();
        background.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/space.png"));
        background.setPosition(400, 300);
        group.add(background);
        
        spaceship = new Sprite();
        spaceship.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/spaceship.png"));
        spaceship.setPosition(400, 300);
        spaceship.setPhysics(new Physics(200, 200, 20));
        spaceship.addAction(Action.wrapToScreen(800, 600));
        group.add(spaceship);
        
        shields = new Sprite();
        shields.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/shields.png"));
        group.add(shields);
        
        rockGroup = new Group();
        rockTex = new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/asteroid.png");

        for (int i = 0; i < rockCount; i++) {
            Sprite rock = new Sprite();
            rock.setTexture(rockTex);
            rock.setSize(100, 100);
            double angle = 2 * Math.PI * Math.random();
            double x = spaceship.position.x + 250 * Math.cos(angle);
            double y = spaceship.position.y + 250 * Math.sin(angle);
            rock.setPosition(x, y);
            
            double moveSpeed = 30 * Math.random() + 90;
            rock.setPhysics(new Physics(0, 100, 0));
            rock.physics.setSpeed(moveSpeed);
            rock.physics.setMotionAngle(Math.toDegrees(angle));
            
            double rotateSpeed = 2 * Math.random() + 1;
            rock.addAction(Action.wrapToScreen(800, 600));
            rock.addAction(Action.forever(Action.rotateBy(360, rotateSpeed)));
            rockGroup.add(rock);
        }
        group.add(rockGroup);
        
        laserGroup = new Group();
        group.add(laserGroup);
        laserTex = new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/laser.png");
        explosionAnim = new Animation("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/explosion.png", 6, 6, 0.05, false);
        
    }
    
    @Override
    public void update() {
        shields.alignToSprite(spaceship);
        shields.setAngle(spaceship.angle);
        
        if (input.isKeyPressed("LEFT")) {
            spaceship.rotateBy(-3);
        }
        if (input.isKeyPressed("RIGHT")) {
            spaceship.rotateBy(3);
        }
        if (input.isKeyPressed("UP")) {
            spaceship.physics.accelerateAtAngle(spaceship.angle);
        }
        if (input.isKeyJustPressed("SPACE")) {
            Sprite laser = new Sprite();
            laser.setTexture(laserTex);
            laser.alignToSprite(spaceship);
            laser.setPhysics(new Physics(0, 400, 0));
            laser.physics.setSpeed(400);
            laser.physics.setMotionAngle(spaceship.angle);
            laser.addAction(Action.wrapToScreen(800, 600));
            laserGroup.add(laser);
            laser.addAction(Action.delayFadeRemove(1, 0.5));
        }
        
        for (Entity rockE : rockGroup.getList()) {
            Sprite rock = (Sprite) rockE;
            if (shields.overlaps(rock) && shields.opacity > 0) {
                rock.removeSelf();
                shields.opacity -= 0.25;
            }
            for (Entity laserE : laserGroup.getList()) {
                Sprite laser = (Sprite) laserE;
                if (rock.overlaps(laser)) {
                    rock.removeSelf();
                    laser.removeSelf();
                    Sprite explosion = new Sprite();
                    explosion.setAnimation(explosionAnim.clone());
                    explosion.alignToSprite(rock);
                    explosion.addAction(Action.animateThenRemove());
                    group.add(explosion);
                    if (rock.width == 100) {
                        for (int i = 0; i < rockCount; i++) {
                            Sprite rockSmall = new Sprite();
                            rockSmall.setTexture(rockTex);
                            rockSmall.setSize(50, 50);
                            rockSmall.alignToSprite(rock);
                            rockSmall.setPhysics(new Physics(0, 300, 0));
                            rockSmall.physics.setSpeed(2 * rock.physics.getSpeed());
                            rockSmall.physics.setMotionAngle(rock.physics.getMotionAngle() + 90 * Math.random() - 45);
                            rockGroup.add(rockSmall);
                        }
                        
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
