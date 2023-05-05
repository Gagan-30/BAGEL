package bagel;

import java.io.File;
import javafx.scene.image.Image;

public class Texture 
{
   public Rectangle region;
   
   public Image image;
   
   public Texture()
   {
       
   }
   
   public static Texture load(String imageFileName)
   {
       Texture tex = new Texture();
       String fileName = new File(imageFileName).toURI().toString();
       tex.image = new Image(fileName);
       tex.region = new Rectangle();
       double width = tex.image.getWidth();
       double height = tex.image.getHeight();
       tex.region.setValues(0, 0, width, height);
       
       return tex;
   }
}
