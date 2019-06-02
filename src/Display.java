
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display extends Canvas
{
	private final JFrame m_Frame;
	
	public Display(int _width, int _height, String _title)
	{
		Dimension size = new Dimension(_width, _height);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		
		this.m_Frame = new JFrame();
		this.m_Frame.add(this);
		this.m_Frame.pack();
		this.m_Frame.setResizable(false);
		this.m_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.m_Frame.setLocationRelativeTo(null);
		this.m_Frame.setTitle(_title);
		this.m_Frame.setVisible(true);
	}
}
