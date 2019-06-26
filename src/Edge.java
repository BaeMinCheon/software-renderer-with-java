
public class Edge
{
	private float 	m_X;
	private float 	m_XStep;
	private int 	m_YStart;
	private int 	m_YEnd;
	
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
	
	public Edge(Vertex _minY, Vertex _maxY)
	{
		this.m_YStart 	= (int)Math.ceil(_minY.GetY());
		this.m_YEnd 	= (int)Math.ceil(_maxY.GetY());
		
		float yDist = _maxY.GetY() - _minY.GetY();
		float xDist = _maxY.GetX() - _minY.GetX();
		
		float yStepPrev = this.m_YStart - _minY.GetY();
		this.m_XStep = xDist / yDist;
		this.m_X = _minY.GetX() + yStepPrev * this.m_XStep;
	}
	
	public void Step()
	{
		this.m_X += this.m_XStep;
	}
}
