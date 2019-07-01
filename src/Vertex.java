
public class Vertex
{
	private Vector4f m_Pos;
	private Vector4f m_TexCoords;
	
	public float GetX()
	{
		return this.m_Pos.GetX();
	}
	
	public float GetY()
	{
		return this.m_Pos.GetY();
	}
	
	public Vector4f GetPosition()
	{
		return this.m_Pos;
	}
	
	public Vector4f GetTexCoords()
	{
		return this.m_TexCoords;
	}
	
	public Vertex(Vector4f _pos, Vector4f _texCoord)
	{
		this.m_Pos = _pos;
		this.m_TexCoords = _texCoord;
	}
	
	public Vertex Transform(Matrix4f _transform)
	{
		Vector4f retVal = _transform.Transform(this.m_Pos);
		return new Vertex(retVal, this.m_TexCoords);
	}
	
	public Vertex PerspectiveDivide()
	{
		float newX = this.m_Pos.GetX() / this.m_Pos.GetW();
		float newY = this.m_Pos.GetY() / this.m_Pos.GetW();
		float newZ = this.m_Pos.GetZ() / this.m_Pos.GetW();
		Vector4f newVec = new Vector4f(newX, newY, newZ, this.m_Pos.GetW());
		Vertex retVal = new Vertex(newVec, this.m_TexCoords);
		return retVal;
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
