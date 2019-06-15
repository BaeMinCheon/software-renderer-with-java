
public class Vertex
{
	private Vector4f m_Pos;
	
	public float GetX()
	{
		return this.GetX();
	}
	
	public float GetY()
	{
		return this.GetY();
	}
	
	public Vertex(float _x, float _y)
	{
		this.m_Pos = new Vector4f(_x, _y, 0.0f, 1.0f);
	}
	
	public Vertex(Vector4f _vector)
	{
		this.m_Pos = _vector;
	}
	
	public Vertex Transform(Matrix4f _transform)
	{
		Vector4f retVal = _transform.Transform(this.m_Pos);
		
		return new Vertex(retVal);
	}
	
	public float TriangleAreaTimesTwo(Vertex _b, Vertex _c)
	{
		float x1 = _b.GetX() - this.GetX();
		float y1 = _b.GetY() - this.GetY();
		float x2 = _c.GetX() - this.GetX();
		float y2 = _c.GetY() - this.GetY();
		
		float crossProduct = x1 * y2 - x2 * y1;
		return crossProduct;
	}
}
