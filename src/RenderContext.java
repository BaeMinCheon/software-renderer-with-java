
public class RenderContext extends Bitmap
{
	private final int[] m_ScanBuffer;
	
	public RenderContext(int _width, int _height)
	{
		super(_width, _height);
		
		this.m_ScanBuffer = new int[_height * 2];
	}
	
	public void DrawScanBuffer(int _y, int _xMin, int _xMax)
	{
		this.m_ScanBuffer[_y * 2 + 0] = _xMin;
		this.m_ScanBuffer[_y * 2 + 1] = _xMax;
	}
	
	public void FillShape(int _yMin, int _yMax)
	{
		for(int y = _yMin; y < _yMax; ++y)
		{
			int xMin = this.m_ScanBuffer[y * 2 + 0];
			int xMax = this.m_ScanBuffer[y * 2 + 1];
			
			for(int x = xMin; x < xMax; ++x)
			{
				this.DrawPixel(x, y, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF);
			}
		}
	}
}
