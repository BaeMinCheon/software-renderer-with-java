
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
	
	public float TriangleArea(Vertex _b, Vertex _c)
	{
		float x1 = _b.GetX() - this.m_X;
		float y1 = _b.GetY() - this.m_Y;
		float x2 = _c.GetX() - this.m_X;
		float y2 = _c.GetY() - this.m_Y;
		
		float crossProduct = x1 * y2 - x2 * y1;
		return crossProduct;
	}
}
