
public class Stars3D
{
	private final int 		m_Size;
	private final float 	m_Spread;
	private final float 	m_Speed;
	private final float[] 	m_StarX;
	private final float[] 	m_StarY;
	private final float[] 	m_StarZ;
	
	public Stars3D(int _num, float _spread, float _speed)
	{
		this.m_Size = _num;
		this.m_Spread = _spread;
		this.m_Speed = _speed;
		this.m_StarX = new float[_num];
		this.m_StarY = new float[_num];
		this.m_StarZ = new float[_num];
		
		for(int i = 0; i < _num; ++i)
		{
			this.InitStar(i);
		}
	}
	
	private void InitStar(int _index)
	{
		this.m_StarX[_index] = ((float)Math.random() - 0.5f) * 2 * this.m_Spread;
		this.m_StarY[_index] = ((float)Math.random() - 0.5f) * 2 * this.m_Spread;
		this.m_StarZ[_index] = ((float)Math.random() + 0.00001f) * this.m_Spread;
	}
	
	public void UpdateAndRender(Bitmap _target, float _delta)
	{
		_target.Clear((byte)0x00);
		
		float halfWidth = _target.GetWidth() / 2.0f;
		float halfHeight = _target.GetHeight() / 2.0f;
		for(int i = 0; i < this.m_Size; ++i)
		{
			this.m_StarZ[i] -= _delta * this.m_Speed;
			
			if(this.m_StarZ[i] <= 0)
			{
				this.InitStar(i);
			}
			
			int x = (int)((this.m_StarX[i] / this.m_StarZ[i]) * halfWidth + halfWidth);
			int y = (int)((this.m_StarY[i] / this.m_StarZ[i]) * halfHeight + halfHeight);
			boolean checkX = (x < 0) || (x >= _target.GetWidth());
			boolean checkY = (y < 0) || (y >= _target.GetHeight());
			if(checkX || checkY)
			{
				this.InitStar(i);
			}
			else
			{
				_target.DrawPixel(x, y, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF);
			}
		}
	}
}
