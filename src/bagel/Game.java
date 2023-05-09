package bagel;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class Game extends Application implements Screen
{
    public Canvas canvas;
    public GraphicsContext context;
    public Group group;
    public Stage stage;
    public Input input;
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Game");
        primaryStage.setResizable(false);
        
        Pane root = new Pane();
        Scene primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.sizeToScene();
        
        canvas = new Canvas(512, 512);
        context = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        
        group = new Group();
        
        input = new Input(primaryScene);
        
        //class containing update nethod
        Game self = this;     
        AnimationTimer gameloop = new AnimationTimer()
        {
            @Override
            public void handle(long nanotime) 
            {
                input.update();
                //update game state
                self.update();
                //update entity phsyics
                self.group.update(1/60.0);
                
                //clear canvas
                self.context.setFill(Color.GRAY);
                self.context.fillRect(0,0,
                        self.canvas.getWidth(), self.canvas.getHeight());
                //render game objects
                self.group.draw(self.context);
            }
        };
        primaryStage.show();
        
        //reference required for set methods
        stage = primaryStage;
        initialize();
        gameloop.start();

    }
    
        public void setTitle(String title)
        {
            stage.setTitle(title);
        }
        
        public void setWindowSize(int width, int height)
        {
            canvas.setWidth(width);
            canvas.setHeight(height);
            stage.sizeToScene();
        }
}
