
public class RenderContext extends Bitmap
{
	public RenderContext(int _width, int _height)
	{
		super(_width, _height);
	}
	
	public void FillTriangle(Vertex _v1, Vertex _v2, Vertex _v3, Bitmap _texture)
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
		this.ScanTriangle(minY, midY, maxY, isBiggerThanRightAngle, _texture);
	}
	
	private void ScanTriangle(Vertex _minY, Vertex _midY, Vertex _maxY, boolean _biggerThanRightAngle, Bitmap _texture)
	{
		Gradient grad = new Gradient(_minY, _midY, _maxY);
		Edge topToBottom 	= new Edge(grad, _minY, _maxY, 0);
		Edge topToMiddle 	= new Edge(grad, _minY, _midY, 0);
		Edge middleToBottom = new Edge(grad, _midY, _maxY, 1);
		
		this.ScanEdges(grad, topToBottom, topToMiddle, _biggerThanRightAngle, _texture);
		this.ScanEdges(grad, topToBottom, middleToBottom, _biggerThanRightAngle, _texture);
	}
	
	private void ScanEdges(Gradient _grad, Edge _a, Edge _b, boolean _biggerThanRightAngle, Bitmap _texture)
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
			this.DrawScanLine(_grad, left, right, y, _texture);
			left.Step();
			right.Step();
		}
	}
	
	private void DrawScanLine(Gradient _grad, Edge _left, Edge _right, int _y, Bitmap _texture)
	{
		int xMin = (int)Math.ceil(_left.GetX());
		int xMax = (int)Math.ceil(_right.GetX());
		float xStepPrev = xMin - _left.GetX();
		
		float texCoordX = _left.GetTexCoordX() + _grad.GetTexCoordXXStep() * xStepPrev;
		float texCoordY = _left.GetTexCoordY() + _grad.GetTexCoordYXStep() * xStepPrev;

		
		for(int x = xMin; x < xMax; ++x)
		{
			int srcX = (int)(texCoordX * (_texture.GetWidth() - 1) + 0.5f);
			int srcY = (int)(texCoordY * (_texture.GetHeight() - 1) + 0.5f);

			this.CopyPixel(x, _y, srcX, srcY, _texture);
			texCoordX += _grad.GetTexCoordXXStep();
			texCoordY += _grad.GetTexCoordYXStep();
		}
	}
}
