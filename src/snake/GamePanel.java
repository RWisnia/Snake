package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Rafkus
 */
public class GamePanel extends JPanel{
    int Size;
    
    GamePanel(int Size) {
        this.Size = Size;
        setPreferredSize(new Dimension(400, 400));
    }
    
    @Override
    public void paintComponent(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
        
        double WidthOneSquere = getWidth()/Size;
        double HeightOneSquere = getHeight()/Size;
        
        g.setColor(Color.RED);
        g.fillRect((int)((Snake.Feed).getX()*WidthOneSquere),(int)((Snake.Feed).getY()*HeightOneSquere),(int)Math.round(WidthOneSquere), (int)Math.round(HeightOneSquere));
        
        g.setColor(Color.BLACK);
        Snake.MySnake.forEach((i) -> {
            g.fillRect((int)(i.getX()*WidthOneSquere), (int)(i.getY()*HeightOneSquere), (int)Math.round(WidthOneSquere), (int)Math.round(HeightOneSquere));
        });
        
       g.drawLine(0, Size*(int)HeightOneSquere-1, Size*(int)WidthOneSquere, Size*(int)HeightOneSquere-1);
    }   
}
