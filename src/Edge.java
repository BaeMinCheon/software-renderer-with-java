
public class Edge
{
	private float 	m_X;
	private float 	m_XStep;
	private int 	m_YStart;
	private int 	m_YEnd;
	private float 	m_TexCoordX;
	private float	m_TexCoordXStep;
	private float	m_TexCoordY;
	private float	m_TexCoordYStep;
	
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
	
	public float GetTexCoordX()
	{
		return this.m_TexCoordX;
	}
	
	public float GetTexCoordY()
	{
		return this.m_TexCoordY;
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
		this.m_TexCoordX = _grad.GetTexCoordX(_minYIndex) +
				_grad.GetTexCoordXXStep() * xStepPrev +
				_grad.GetTexCoordXYStep() * yStepPrev;
		this.m_TexCoordXStep = _grad.GetTexCoordXYStep() + _grad.GetTexCoordXXStep() * this.m_XStep;

		this.m_TexCoordY = _grad.GetTexCoordY(_minYIndex) +
				_grad.GetTexCoordYXStep() * xStepPrev +
				_grad.GetTexCoordYYStep() * yStepPrev;
		this.m_TexCoordYStep = _grad.GetTexCoordYYStep() + _grad.GetTexCoordYXStep() * this.m_XStep;
	}
	
	public void Step()
	{
		this.m_X += this.m_XStep;
		this.m_TexCoordX += this.m_TexCoordXStep;
		this.m_TexCoordY += this.m_TexCoordYStep;
	}
}
