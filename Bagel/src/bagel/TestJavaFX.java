package bagel;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestJavaFX extends Application
{
    public double x;
    public double y;
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("My JavaFX Application");
        
        Pane root = new Pane();
        
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setScene(scene);
        
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext context = canvas.getGraphicsContext2D();
        
        Image ball = new Image("C:\\Users\\gghat\\Documents\\NetBeansProjects\\Bagel\\images\\basketball.png",
                100, 100, true, true);
        
        x = 10;
        y = 10;
        
        root.getChildren().add(canvas);
        
        AnimationTimer timer = new AnimationTimer()
        {
            @Override
            public void handle(long nanoTime) 
            {
                //clear canvas
                context.setFill(Color.WHITE); // background color
                context.fillRect(0,0, 800,600);
                
                x += 2;
                y += 1;
                context.drawImage(ball, x, y);
            }
            
        };
        
        timer.start();
        primaryStage.show();
    }
    
    public static void main(String[] args)
    {
        try 
        {
            launch(args);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
        }
        finally 
        {
            System.exit(0);
        }
    }
}
