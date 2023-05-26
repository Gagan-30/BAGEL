package bagel;

public class Asteroids extends Game {

    public Sprite background;
    public Sprite spaceship;
    public Group rockGroup;

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

        rockGroup = new Group();
        Texture rockTexture = new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/asteroid.png");
        int rockCount = 6;
        for (int i = 0; i < rockCount; i++) {
            Sprite rock = new Sprite();
            rock.setTexture(rockTexture);
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
    }

    @Override
    public void update() {
        if (input.isKeyPressed("LEFT")) {
            spaceship.rotateBy(-3);
        }
        if (input.isKeyPressed("RIGHT")) {
            spaceship.rotateBy(3);
        }
        if (input.isKeyPressed("UP")) {
            spaceship.physics.accelerateAtAngle(spaceship.angle);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
