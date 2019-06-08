
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.JFrame;

public class Display extends Canvas
{
	private final JFrame 			m_FrameCurrent;
	private final RenderContext 	m_FrameBuffer;
	private final BufferedImage 	m_DisplayImage;
	private final byte[]			m_DisplayBytes;
	private final BufferStrategy 	m_BufferStrategy;
	private final Graphics			m_Graphics;
	
	public Display(int _width, int _height, String _title)
	{
		Dimension size = new Dimension(_width, _height);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		
		this.m_FrameBuffer = new RenderContext(_width, _height);
		this.m_DisplayImage = new BufferedImage(_width, _height, BufferedImage.TYPE_3BYTE_BGR);
		Object dataBuffer = this.m_DisplayImage.getRaster().getDataBuffer();
		this.m_DisplayBytes = ((DataBufferByte)dataBuffer).getData();
		
		this.m_FrameBuffer.Clear((byte)0xFF);
		this.m_FrameBuffer.DrawPixel(100, 100, (byte)0xFF, (byte)0x00, (byte)0x00, (byte)0xFF);
		
		this.m_FrameCurrent = new JFrame();
		this.m_FrameCurrent.add(this);
		this.m_FrameCurrent.pack();
		this.m_FrameCurrent.setResizable(false);
		this.m_FrameCurrent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.m_FrameCurrent.setLocationRelativeTo(null);
		this.m_FrameCurrent.setTitle(_title);
		this.m_FrameCurrent.setVisible(true);
		
		this.createBufferStrategy(1);
		this.m_BufferStrategy = this.getBufferStrategy();
		this.m_Graphics = this.m_BufferStrategy.getDrawGraphics();
	}
	
	public void SwapBuffers()
	{
		this.m_FrameBuffer.CopyToByteArray(this.m_DisplayBytes);
		this.m_Graphics.drawImage(this.m_DisplayImage, 0, 0, this.m_FrameBuffer.GetWidth(),
				this.m_FrameBuffer.GetHeight(), null);
		this.m_BufferStrategy.show();
	}
	
	public RenderContext GetFrameBuffer()
	{
		return this.m_FrameBuffer;
	}
}
