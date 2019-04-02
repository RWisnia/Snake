package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static snake.Snake.MySnake;

/**
 *
 * @author Rafkus
 */
public class GamePanel extends JPanel{
    int Size;
    ImageIcon SnakeHeadDown = new ImageIcon("\\SnakeHeadDown.png");
    ImageIcon SnakeHeadUp = new ImageIcon("\\SnakeHeadUp.png");
    ImageIcon SnakeHeadLeft = new ImageIcon("\\SnakeHeadLeft.png");
    ImageIcon SnakeHeadRight = new ImageIcon("\\SnakeHeadRight.png");
    ImageIcon SnakeBody = new ImageIcon("\\body.png");
    ImageIcon Cherry = new ImageIcon("\\Cherry.png");
    
    GamePanel(int Size) {
        this.Size = Size;
        setPreferredSize(new Dimension(400, 400));
    }
    
    @Override
    public void paintComponent(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
        
        double WidthOneSquere = getWidth()/Size;
        double HeightOneSquere = getHeight()/Size;
    
        int LastX = (int)(MySnake.get(MySnake.size()-1)).getX();
        int LastY = (int)(MySnake.get(MySnake.size()-1)).getY();
        
        // Display cherry 
        Cherry.paintIcon(this, g, (int)((Snake.Feed).getX()*WidthOneSquere),(int)((Snake.Feed).getY()*HeightOneSquere));
      
        g.setColor(Color.BLACK);
        Snake.MySnake.forEach((i) -> {
            if(LastX==i.getX() && LastY==i.getY()) {
                    // Display Snake Head
                if (Snake.SnakeWay==40)
                    SnakeHeadDown.paintIcon(this, g, LastX*(int)WidthOneSquere, LastY*(int)HeightOneSquere);
                else if(Snake.SnakeWay==38)
                    SnakeHeadUp.paintIcon(this, g, LastX*(int)WidthOneSquere, LastY*(int)HeightOneSquere);
                else if(Snake.SnakeWay==37)
                    SnakeHeadLeft.paintIcon(this, g, LastX*(int)WidthOneSquere, LastY*(int)HeightOneSquere);
                else if(Snake.SnakeWay==39)
                    SnakeHeadRight.paintIcon(this, g, LastX*(int)WidthOneSquere, LastY*(int)HeightOneSquere);      
            }   else    SnakeBody.paintIcon(this, g, (int)(i.getX()*WidthOneSquere), (int)(i.getY()*HeightOneSquere));
        });
        
       g.drawLine(0, Size*(int)HeightOneSquere-1, Size*(int)WidthOneSquere, Size*(int)HeightOneSquere-1);
    }   
}
