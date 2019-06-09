
public class Vertex
{
	private float m_X;
	private float m_Y;
	
	public float GetX()
	{
		return this.m_X;
	}
	
	public float GetY()
	{
		return this.m_Y;
	}
	
	public void SetX(float _x)
	{
		this.m_X = _x;
	}
	
	public void SetY(float _y)
	{
		this.m_Y = _y;
	}
	
	public Vertex(float _x, float _y)
	{
		this.m_X = _x;
		this.m_Y = _y;
	}
}
