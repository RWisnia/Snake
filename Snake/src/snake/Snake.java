package snake;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Rafkus
 */
public class Snake implements KeyListener, Runnable{
    
    ///////Snake///////
    public static ArrayList<Point> MySnake = new ArrayList<>();
    
    boolean run = true; // if true game is on
    public static Point Feed; // Point of Feed
    int points = 0; // number of points at the beginning
    public static int SnakeWay = 40; // way that snake is go to
    int Size = 40; //size of game panel (if size==2 -> game panel will be 2x2)
    int Speed = 50; // speed of the snake
    
    Random rand;
    JFrame MyFrame = new JFrame("Snake");
    GamePanel MyGamePanel = new GamePanel(Size);
    Statistics ButtomBar = new Statistics(Speed, points);
    
    public static void main(String[] args) {
       new Snake();
    }
    
    public Snake(){
        ////Start settings///
        //snake start position
        MySnake.add(StartingPoint());   
        MySnake.add(new Point((int)((MySnake.get(0)).getX()),(int)((MySnake.get(0)).getY())+1));
        MySnake.add(new Point((int)((MySnake.get(0)).getX()),(int)((MySnake.get(0)).getY())+2));
        //Food start position
        Feed = FeedPoint(); 
        
        MyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyFrame.setVisible(true);
        
        MyFrame.setSize(500,520);
        MyFrame.setLayout(new BorderLayout());
        MyFrame.setLocationRelativeTo(null);
        MyFrame.add(MyGamePanel, BorderLayout.CENTER);
        MyFrame.addKeyListener(this); 

        MyFrame.add(ButtomBar, BorderLayout.SOUTH);
        
        MyFrame.setResizable(false);
        MyFrame.pack();
        
        Thread StartGame = new Thread(this);
        StartGame.start();
    }
    
    ///////Get new random StartingPoint///////
    public Point StartingPoint(){
    rand = new Random();
    int x = (int) (Math.round(rand.nextDouble()*Size));
    if(x < 5) x = x + 5;      //The starting point must be slightly offset from the edge
    if(x > Size - 5) x = x-5;
    
    int y = (int) (Math.round(rand.nextDouble()*Size));
    if(y < 5) y = y+5;      //The starting point must be slightly offset from the edge
    if(y > Size - 5) y = y-5;
    
    return new Point(x,y);
    }
    
    ///////Get new random FeedPoint///////
    public Point FeedPoint(){
    rand = new Random();
    
    int x = (int) (Math.round(rand.nextDouble()*Size));
    int y = (int) (Math.round(rand.nextDouble()*Size));
    
    // if random point proved to be in snake or x,y have Size Point
    while(MySnake.contains(new Point(x,y))==true || x == Size || y == Size){
        x = (int) (Math.round(rand.nextDouble()*Size));
        y = (int) (Math.round(rand.nextDouble()*Size));
    }
        
    return new Point(x,y);
    }
    
    public void GoSnake(){
        int LastX = (int)(MySnake.get(MySnake.size()-1)).getX(); // position of the hose head on the horizontal axis
        int LastY = (int)(MySnake.get(MySnake.size()-1)).getY(); // position of the hose head on the vertical axis
        
        Point FirstElement = MySnake.get(0); // the first element of the list 
        
        // if snake ate food
        boolean scored = false;
        if(new Point(LastX,LastY).equals(Feed)){
            points++;
            scored = true;
            Feed = FeedPoint();
            
            // increasing speed
            if(Speed*1.1< 1000)
                Speed = (int)Math.round(1.1*Speed);
            
            // change values on display
            ButtomBar.ChangePointsDisplay(points);
            ButtomBar.ChangeSpeedDisplay(Speed);
        }     
 
        switch(SnakeWay){
            case 37: //left
                if(LastX-1 < 0) GameOver();
                else    MySnake.add(new Point(LastX-1,LastY));
                if(scored == false) MySnake.remove(FirstElement);
            break;
            case 38: //up
                if(LastY-1 < 0) GameOver();
                else    MySnake.add(new Point(LastX,LastY-1));
                if(scored == false) MySnake.remove(FirstElement);
            break;
            case 39: //right
                if(LastX+1 > Size-1) GameOver();
                else    {
                    MySnake.add(new Point(LastX+1,LastY));
                    if(scored == false) MySnake.remove(FirstElement);
                }
            break;
            case 40: //down
                if(LastY+1 > Size-1) GameOver();
                else    {
                    MySnake.add(new Point(LastX,LastY+1));
                    if(scored == false) MySnake.remove(FirstElement);
                }
            break;
    }

        //the snake enters itself
for(int i=0;i<MySnake.size(); i++){  
    for(int j=1; j<MySnake.size(); j++){
           if(i!=j && MySnake.get(i).equals(MySnake.get(j))){
               GameOver();
           }
    }
}
    
    MyFrame.repaint();   
    }
    
    public void GameOver(){        
        run = false;
        System.out.println("GameOver");
        ButtomBar.setGameOver();
        return;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       SnakeWay = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    
    }

    @Override
    public void run() {
       while(run == true){
            GoSnake();
            try {
                Thread.sleep(1000-Speed);
            } catch (InterruptedException ex) {
                
            }
       }
    }
    
}
