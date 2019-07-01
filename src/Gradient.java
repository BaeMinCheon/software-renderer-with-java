public class Gradient
{
	private float[] m_TexCoordX;
	private float[] m_TexCoordY;
	private float m_TexCoordXXStep;
	private float m_TexCoordXYStep;
	private float m_TexCoordYXStep;
	private float m_TexCoordYYStep;

	public float GetTexCoordX(int loc)
	{
		return this.m_TexCoordX[loc];
	}
	
	public float GetTexCoordY(int loc)
	{
		return this.m_TexCoordY[loc];
	}

	public float GetTexCoordXXStep()
	{
		return this.m_TexCoordXXStep;
	}
	
	public float GetTexCoordXYStep()
	{
		return this.m_TexCoordXYStep;
	}
	
	public float GetTexCoordYXStep()
	{
		return this.m_TexCoordYXStep;
	}
	
	public float GetTexCoordYYStep()
	{
		return this.m_TexCoordYYStep;
	}

	public Gradient(Vertex _minY, Vertex _midY, Vertex _maxY)
	{
		float dx = (_midY.GetX() - _maxY.GetX()) *
			(_minY.GetY() - _maxY.GetY()) -
			(_minY.GetX() - _maxY.GetX()) *
			(_midY.GetY() - _maxY.GetY());

		float dy = -1.0f * dx;

		this.m_TexCoordX = new float[3];
		this.m_TexCoordY = new float[3];

		this.m_TexCoordX[0] = _minY.GetTexCoords().GetX();
		this.m_TexCoordX[1] = _midY.GetTexCoords().GetX();
		this.m_TexCoordX[2] = _maxY.GetTexCoords().GetX();

		this.m_TexCoordY[0] = _minY.GetTexCoords().GetY();
		this.m_TexCoordY[1] = _midY.GetTexCoords().GetY();
		this.m_TexCoordY[2] = _maxY.GetTexCoords().GetY();

		this.m_TexCoordXXStep = ((this.m_TexCoordX[1] - this.m_TexCoordX[2]) *
			(_minY.GetY() - _maxY.GetY()) -
			(this.m_TexCoordX[0] - this.m_TexCoordX[2]) *
			(_midY.GetY() - _maxY.GetY())) / dx;

		this.m_TexCoordXYStep = ((this.m_TexCoordX[1] - this.m_TexCoordX[2]) *
			(_minY.GetX() - _maxY.GetX()) -
			(this.m_TexCoordX[0] - this.m_TexCoordX[2]) *
			(_midY.GetX() - _maxY.GetX())) / dy;

		this.m_TexCoordYXStep = ((this.m_TexCoordY[1] - this.m_TexCoordY[2]) *
			(_minY.GetY() - _maxY.GetY()) -
			(this.m_TexCoordY[0] - this.m_TexCoordY[2]) *
			(_midY.GetY() - _maxY.GetY())) / dx;

		this.m_TexCoordYYStep = ((this.m_TexCoordY[1] - this.m_TexCoordY[2]) *
			(_minY.GetX() - _maxY.GetX()) -
			(this.m_TexCoordY[0] - this.m_TexCoordY[2]) *
			(_midY.GetX() - _maxY.GetX())) / dy;
	}
}