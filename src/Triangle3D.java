
public class Triangle3D
{
	private final int 		m_Size;
	private final float 	m_Spread;
	private final float 	m_Speed;
	private final float[] 	m_TriangleX;
	private final float[] 	m_TriangleY;
	private final float[] 	m_TriangleZ;
	
	public Triangle3D(float _spread, float _speed)
	{
		this.m_Size = 3;
		this.m_Spread = _spread;
		this.m_Speed = _speed;
		this.m_TriangleX = new float[3];
		this.m_TriangleY = new float[3];
		this.m_TriangleZ = new float[3];
		
		for(int i = 0; i < 3; ++i)
		{
			this.InitTriangle(i);
		}
	}
	
	private void InitTriangle(int _index)
	{
		this.m_TriangleX[_index] = ((float)Math.random() - 0.5f) * 2 * this.m_Spread;
		this.m_TriangleY[_index] = ((float)Math.random() - 0.5f) * 2 * this.m_Spread;
		this.m_TriangleZ[_index] = ((float)Math.random() + 0.00001f) * this.m_Spread;
	}
	
	public void UpdateAndRender(RenderContext _target, float _delta)
	{
		final float tanHalfFOVX = (float)Math.atan(Math.toRadians(70.0 / 2.0));
		final float tanHalfFOVY = (float)Math.atan(Math.toRadians(70.0 / 2.0));
		_target.Clear((byte)0x00);
		
		float halfWidth = _target.GetWidth() / 2.0f;
		float halfHeight = _target.GetHeight() / 2.0f;
		int counter = 0;
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		for(int i = 0; i < this.m_Size; ++i)
		{
			this.m_TriangleZ[i] -= _delta * this.m_Speed;
			
			if(this.m_TriangleZ[i] <= 0)
			{
				this.InitTriangle(i);
			}
			
			int x = (int)(this.m_TriangleX[i] / (this.m_TriangleZ[i] * tanHalfFOVX) * halfWidth + halfWidth);
			int y = (int)(this.m_TriangleY[i] / (this.m_TriangleZ[i] * tanHalfFOVY) * halfHeight + halfHeight);
			boolean checkX = (x < 0) || (x >= _target.GetWidth());
			boolean checkY = (y < 0) || (y >= _target.GetHeight());
			if(checkX || checkY)
			{
				this.InitTriangle(i);
				continue;
			}
			
			counter += 1;
			if(counter == 1)
			{
				x1 = x;
				y1 = y;
			}
			else if(counter == 2)
			{
				x2 = x;
				y2 = y;
			}
			else if(counter == 3)
			{
				counter = 0;
			}
		}
	}
}
