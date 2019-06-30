
public class Edge
{
	private float 	m_X;
	private float 	m_XStep;
	private int 	m_YStart;
	private int 	m_YEnd;
	private Vector4f m_Color;
	private Vector4f m_ColorStep;
	
	public float GetX()
	{
		return this.m_X;
	}
	
	public int GetYStart()
	{
		return this.m_YStart;
	}
	
	public int GetYEnd()
	{
		return this.m_YEnd;
	}
	
	public Vector4f GetColor()
	{
		return this.m_Color;
	}
	
	public Edge(Gradient _grad, Vertex _minY, Vertex _maxY, int _minYIndex)
	{
		this.m_YStart 	= (int)Math.ceil(_minY.GetY());
		this.m_YEnd 	= (int)Math.ceil(_maxY.GetY());
		
		float yDist = _maxY.GetY() - _minY.GetY();
		float xDist = _maxY.GetX() - _minY.GetX();
		
		float yStepPrev = this.m_YStart - _minY.GetY();
		this.m_XStep = xDist / yDist;
		this.m_X = _minY.GetX() + yStepPrev * this.m_XStep;
		
		float xStepPrev = this.m_X - _minY.GetX();
		this.m_Color = _grad.GetColor(_minYIndex).Add(
				_grad.GetColorYStep().Mul(yStepPrev).Add(
				_grad.GetColorXStep().Mul(xStepPrev)));
		this.m_ColorStep = _grad.GetColorYStep().Add(_grad.GetColorXStep().Mul(this.m_XStep));
	}
	
	public void Step()
	{
		this.m_X += this.m_XStep;
		this.m_Color = this.m_Color.Add(this.m_ColorStep);
	}
}
