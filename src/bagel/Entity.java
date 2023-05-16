package bagel;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {

    public abstract void draw(GraphicsContext context);

    public abstract void update(double dt);
}
