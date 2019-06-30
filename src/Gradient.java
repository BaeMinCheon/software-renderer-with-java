
public class Gradient
{
	private Vector4f[] m_Colors;
	private Vector4f m_ColorXStep;
	private Vector4f m_ColorYStep;

	public Vector4f GetColor(int _index)
	{
		return this.m_Colors[_index];
	}
	
	public Vector4f GetColorXStep()
	{
		return this.m_ColorXStep;
	}
	
	public Vector4f GetColorYStep()
	{
		return this.m_ColorYStep;
	}

	public Gradient(Vertex _minY, Vertex _midY, Vertex _maxY)
	{
		this.m_Colors = new Vector4f[3];
		this.m_Colors[0] = _minY.GetColor();
		this.m_Colors[1] = _midY.GetColor();
		this.m_Colors[2] = _maxY.GetColor();
		float dx = (_midY.GetX() - _maxY.GetX()) *
			(_minY.GetY() - _maxY.GetY()) -
			(_minY.GetX() - _maxY.GetX()) *
			(_midY.GetY() - _maxY.GetY());
		float dy = -1.0f * dx;
		this.m_ColorXStep =  
			(((this.m_Colors[1].Sub(this.m_Colors[2])).Mul(
			(_minY.GetY() - _maxY.GetY()))).Sub(
			((this.m_Colors[0].Sub(this.m_Colors[2])).Mul(
			(_midY.GetY() - _maxY.GetY()))))).Div(dx);
		this.m_ColorYStep =  
			(((this.m_Colors[1].Sub(this.m_Colors[2])).Mul(
			(_minY.GetX() - _maxY.GetX()))).Sub(
			((this.m_Colors[0].Sub(this.m_Colors[2])).Mul(
			(_midY.GetX() - _maxY.GetX()))))).Div(dy);
	}
}
