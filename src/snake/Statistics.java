package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Rafkus
 */
public class Statistics extends JPanel{
  
    JLabel MyPoints;
    JLabel Speed;
    
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
}
