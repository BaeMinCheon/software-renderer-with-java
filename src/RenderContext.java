
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
	
	public void ScanConvertTriangle(Vertex _minY, Vertex _midY, Vertex _maxY, int _side)
	{
		this.ScanConvertLine(_minY, _maxY, _side);
		this.ScanConvertLine(_minY, _midY, 1 - _side);
		this.ScanConvertLine(_midY, _maxY, 1 - _side);
	}
	
	private void ScanConvertLine(Vertex _minY, Vertex _maxY, int _side)
	{
		int yStart = (int)_minY.GetY();
		int yEnd = (int)_maxY.GetY();
		int xStart = (int)_minY.GetX();
		int xEnd = (int)_maxY.GetX();
		
		int yDist = yEnd - yStart;
		int xDist = xEnd - xStart;
		
		if(yDist <= 0)
		{
			return;
		}
		
		float xStep = ((float)xDist)/((float)yDist);
		float xCurr = (float)xStart;
		
		for(int y = yStart; y < yEnd; ++y)
		{
			this.m_ScanBuffer[y * 2 + _side] = (int)xCurr;
			xCurr += xStep;
		}
	}
}
