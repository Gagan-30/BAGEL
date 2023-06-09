package bagel;

import javafx.scene.paint.Color;

public class StarfishCollector extends Game {

    Sprite water;
    Group starfishGroup;
    Sprite turtle;
    Group rockGroup;
    Sprite shark;

    Label starfishLabel;
    Label winLabel;

    public void initialize() {
        setTitle("Starfish Collector");
        setWindowSize(800, 600);

        water = new Sprite();
        water.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/water.png"));
        water.setPosition(400, 300);
        group.add(water);

        rockGroup = new Group();
        Texture rockTexture = new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/rock.png");
        int rockCount = 3;
        for (int i = 0; i < rockCount; i++) {
            Sprite rock = new Sprite();
            double x = Math.random() * 600 + 100;
            double y = Math.random() * 400 + 100;
            rock.setPosition(x, y);
            rock.setTexture(rockTexture);
            rockGroup.add(rock);
        }
        group.add(rockGroup);

        shark = new Sprite();
        shark.setPosition(400, 300);
        shark.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/shark.png"));
        group.add(shark);

        starfishGroup = new Group();
        Texture starfishTexture = new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/starfish.png");
        int starfishCount = 20;
        for (int i = 0; i < starfishCount; i++) {
            Sprite starfish = new Sprite();
            double x = Math.random() * 600 + 100;
            double y = Math.random() * 400 + 100;
            starfish.setPosition(x, y);
            starfish.setTexture(starfishTexture);
            starfish.setSize((int) (Math.random() * 20 + 40), (int) (Math.random() * 20 + 40));
            starfish.setAngle(Math.random() * 360);

            boolean rockOverlap;
            do {
                rockOverlap = false;
                x = Math.random() * 600 + 100;
                y = Math.random() * 400 + 100;
                starfish.setPosition(x, y);
                for (Entity entity : rockGroup.getList()) {
                    Sprite rock = (Sprite) entity;
                    if (rock.overlaps(starfish)) {
                        rockOverlap = true;
                    }
                }
            } while (rockOverlap);

            starfish.addAction(
                    Action.forever(
                            Action.rotateBy(360, Math.random() + 2)
                    )
            );

            // data: "has starfish been collected yet?"
            starfish.data = false;

            starfishGroup.add(starfish);
        }
        group.add(starfishGroup);

        turtle = new Sprite();
        turtle.setPosition(90, 90);
        turtle.setTexture(new Texture("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/turtle.png"));
        turtle.setPhysics(new Physics(400, 200, 400));
        turtle.addAction(Action.boundToScreen(800, 600));
        group.add(turtle);

        // optional: add a transparent water layer on top
        //   to create an "underwater" effect
        /*
        Sprite water2 = new Sprite();
        water2.setTexture( new Texture("images/water.png") );
        water2.setPosition(400,300);
        water2.opacity = 0.30;
        group.add( water2 );
         */
        int fishCount = 6;
        Animation fishAnim = new Animation("C:/Users/gghat/Documents/NetBeansProjects/Bagel/src/images/fish.png", 8, 1, 0.1, true);
        for (int i = 0; i < fishCount; i++) {
            Sprite fish = new Sprite();
            fish.setPosition(Math.random() * 800, Math.random() * 600);

            fish.setAnimation(fishAnim.clone());

            fish.setPhysics(new Physics(0, 200, 0));
            double speed = Math.random() * 100 + 100;
            fish.physics.setSpeed(speed);
            double angle = Math.random() * 360;
            fish.physics.setMotionAngle(angle);
            fish.setAngle(angle);

            fish.addAction(Action.wrapToScreen(800, 600));

            group.add(fish);
        }

        winLabel = new Label("Comic Sans MS Bold", 80);
        winLabel.setText("You Win!");
        winLabel.fontColor = Color.LIGHTGREEN;
        winLabel.setBorder(2, Color.DARKGREEN);
        winLabel.setPosition(400, 300);
        winLabel.alignment = "CENTER";
        winLabel.visible = false;
        group.add(winLabel);

        starfishLabel = new Label("Comic Sans MS Bold", 48);
        String text = "Starfish Left: " + starfishGroup.size();
        starfishLabel.setText(text);
        starfishLabel.setPosition(780, 580);
        starfishLabel.alignment = "RIGHT";
        starfishLabel.fontColor = Color.YELLOW;
        starfishLabel.setBorder(2, Color.BLACK);
        group.add(starfishLabel);
    }

    public void update() {
        if (winLabel.visible) {
            return;
        }

        if (input.isKeyPressed("RIGHT")) {
            turtle.physics.accelerateAtAngle(0);
        }

        if (input.isKeyPressed("LEFT")) {
            turtle.physics.accelerateAtAngle(180);
        }

        if (input.isKeyPressed("UP")) {
            turtle.physics.accelerateAtAngle(270);
        }

        if (input.isKeyPressed("DOWN")) {
            turtle.physics.accelerateAtAngle(90);
        }

        if (turtle.physics.getSpeed() > 0) {
            turtle.setAngle(turtle.physics.getMotionAngle());
        }

        if (turtle.position.x < shark.position.x) {
            shark.mirrored = true;
        }

        if (turtle.position.x > shark.position.x) {
            shark.mirrored = false;
        }

        for (Entity entity : starfishGroup.getList()) {
            Sprite starfish = (Sprite) entity;

            if (turtle.overlaps(starfish)
                    && (boolean) (starfish.data) == false) {
                starfish.actionList.clear();
                starfish.addAction(Action.fadeOut(1));

                // indicate that this starfish has been collected
                starfish.data = true;
            }

            if (starfish.actionList.isEmpty()) {
                starfishGroup.remove(starfish);
            }

            String text = "Starfish Left: " + starfishGroup.size();
            starfishLabel.setText(text);
        }

        for (Entity entity : rockGroup.getList()) {
            Sprite rock = (Sprite) entity;
            turtle.preventOverlap(rock);
        }

        if (starfishGroup.size() == 0) {
            winLabel.visible = true;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
