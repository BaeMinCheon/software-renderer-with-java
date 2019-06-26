
public class RenderContext extends Bitmap
{
	public RenderContext(int _width, int _height)
	{
		super(_width, _height);
	}
	
	public void FillTriangle(Vertex _v1, Vertex _v2, Vertex _v3)
	{
		Matrix4f screenSpaceTransform = new Matrix4f().InitScreenSpaceTransform(this.GetWidth() / 2.0f, this.GetHeight() / 2.0f);
		Vertex minY = _v1.Transform(screenSpaceTransform).PerspectiveDivide();
		Vertex midY = _v2.Transform(screenSpaceTransform).PerspectiveDivide();
		Vertex maxY = _v3.Transform(screenSpaceTransform).PerspectiveDivide();
		
		{	// sort
			if(maxY.GetY() < midY.GetY())
			{
				Vertex temp = maxY;
				maxY = midY;
				midY = temp;
			}
			if(midY.GetY() < minY.GetY())
			{
				Vertex temp = midY;
				midY = minY;
				minY = temp;
			}
			if(maxY.GetY() < midY.GetY())
			{
				Vertex temp = maxY;
				maxY = midY;
				midY = temp;
			}
		}
		
		boolean isBiggerThanRightAngle = minY.TriangleAreaTimesTwo(maxY, midY) < 0.0f;
		this.ScanTriangle(minY, midY, maxY, isBiggerThanRightAngle);
	}
	
	private void ScanTriangle(Vertex _minY, Vertex _midY, Vertex _maxY, boolean _biggerThanRightAngle)
	{
		Edge topToBottom 	= new Edge(_minY, _maxY);
		Edge topToMiddle 	= new Edge(_minY, _midY);
		Edge middleToBottom = new Edge(_midY, _maxY);
		
		this.ScanEdges(topToBottom, topToMiddle, _biggerThanRightAngle);
		this.ScanEdges(topToBottom, middleToBottom, _biggerThanRightAngle);
	}
	
	private void ScanEdges(Edge _a, Edge _b, boolean _biggerThanRightAngle)
	{
		Edge left = _a;
		Edge right = _b;
		if(_biggerThanRightAngle == false)
		{
			Edge temp = left;
			left = right;
			right = temp;
		}
		
		int yStart = _b.GetYStart();
		int yEnd = _b.GetYEnd();
		for(int y = yStart; y < yEnd; ++y)
		{
			this.DrawScanLine(left, right, y);
			left.Step();
			right.Step();
		}
	}
	
	private void DrawScanLine(Edge _left, Edge _right, int _y)
	{
		int xMin = (int)Math.ceil(_left.GetX());
		int xMax = (int)Math.ceil(_right.GetX());
		
		for(int x = xMin; x < xMax; ++x)
		{
			this.DrawPixel(x, _y, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF);
		}
	}
}
