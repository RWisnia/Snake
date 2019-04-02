package snake;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Rafkus
 */
public class Statistics extends JPanel{
  
    JLabel MyPoints;
    JLabel Speed;
    JLabel GameOver;
    
    public Statistics(int speed,int points){
        MyPoints = new JLabel("Points: "+points);
        Speed = new JLabel("Speed: "+speed);
        
        setLayout(null);
        
        add(MyPoints);
        add(Speed);
        
        MyPoints.setLocation(110,1);
        MyPoints.setSize(100,20);
        
        Speed.setLocation(250,1);
        Speed.setSize(100,20);
        
        setPreferredSize(new Dimension(400, 20));
    }
    @Override
    public void paintComponent(Graphics a) {
	Graphics2D g2d = (Graphics2D) a;
    }
    
    public void ChangePointsDisplay(int points){
        MyPoints.setText("Points: "+points);
    }
    
    public void ChangeSpeedDisplay(int speed){
        Speed.setText("Speed: "+speed);
    }
    
    // show Game Over
    public void setGameOver(){
        MyPoints.setVisible(false);
        Speed.setVisible(false);
        GameOver = new JLabel("Game Over");
        GameOver.setVisible(true);
        add(GameOver);
        
        GameOver.setLocation(170,1);
        GameOver.setSize(100,20);
    }
}
