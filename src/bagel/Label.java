package bagel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Label extends Entity {

    public String fontName;
    public int fontSize;
    public Font font;
    public Color fontColor;
    public String text;
    public Vector position;
    public String alignment;
    public boolean drawBorder;
    public Color borderColor;
    public int borderSize;
    public boolean visible;

    public Label(String fontName, int fontSize) {
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.font = new Font(fontName, fontSize);
        this.fontColor = Color.BLACK;
        this.text = "...";
        this.position = new Vector();
        this.alignment = "LEFT";
        this.drawBorder = false;
        this.borderColor = Color.BLACK;
        this.borderSize = 1;
        this.visible = true;
    }

    public void setPosition(double x, double y) {
        position.setValues(x, y);
    }

    public void setText(String t) {
        text = t;
    }

    public void setBorder(int size, Color color) {
        drawBorder = true;
        borderSize = size;
        borderColor = color;
    }

    @Override
    public void draw(GraphicsContext context) {
        if (!visible) {
            return;
        }

        context.setFont(font);
        context.setFill(fontColor);

        if (alignment.equals("LEFT")) {
            context.setTextAlign(TextAlignment.LEFT);
        } else if (alignment.equals("CENTER")) {
            context.setTextAlign(TextAlignment.CENTER);
        } else if (alignment.equals("RIGHT")) {
            context.setTextAlign(TextAlignment.RIGHT);
        }

        context.setTransform(1, 0, 0, 1, 0, 0);
        context.setGlobalAlpha(1);
        context.fillText(text, position.x, position.y);

        if (drawBorder) {
            context.setStroke(borderColor);
            context.setLineWidth(borderSize);
            context.strokeText(text, position.x, position.y);
        }
    }

    @Override
    public void update(double dt) {

    }

}
