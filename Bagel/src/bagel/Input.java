package bagel;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Input 
{
    public ArrayList<String> justPressedQueue;
    public ArrayList<String> justReleasedQueue;
    
    public ArrayList<String> justPressedList;
    public ArrayList<String> stillPressedList;
    public ArrayList<String> justReleasedList;
    
    public Input(Scene listeningScene)
    {
        justPressedQueue = new ArrayList<String>();
        justReleasedQueue = new ArrayList<String>();
        justPressedList = new ArrayList<String>();
        stillPressedList = new ArrayList<String>();
        justReleasedList = new ArrayList<String>();
        
        listeningScene.setOnKeyPressed((KeyEvent event) ->
            {
                String keyName = event.getCode().toString();
                justPressedQueue.add(keyName);
            }
        );
        
        listeningScene.setOnKeyReleased((KeyEvent event) ->
            {
                String keyName = event.getCode().toString();
                justReleasedQueue.add(keyName);
            }
        );
        
    }
    
    public void update()
    {
        //clear data from previous discrete event
        justPressedList.clear();
        justReleasedList.clear();
        
        //update current events based on data from queues
        for (String keyName : justPressedQueue)
        {
            if(!stillPressedList.contains(keyName))
            {
                justPressedList.add(keyName);
                stillPressedList.add(keyName);
            }
        }
        
        for (String keyName :justReleasedQueue)
        {
            stillPressedList.remove(keyName);
            justReleasedList.add(keyName);
        }
        
        //clear queues
        justPressedQueue.clear();
        justReleasedQueue.clear();
    }
    
    public boolean isKeyJustPressed(String keyName)
    {
        return justPressedList.contains(keyName);
    }
    
    public boolean isKeyPressed(String keyName)
    {
        return stillPressedList.contains(keyName);
    }
    
    public boolean isKeyJustReleased(String keyName)
    {
        return justReleasedList.contains(keyName);
    }
}
