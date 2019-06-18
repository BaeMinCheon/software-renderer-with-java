
public class Matrix4f
{
	private float[][] m_Matrix;
	
	public Matrix4f()
	{
		this.m_Matrix = new float[4][4];
	}
	
	public Matrix4f InitIdentity()
	{
		for(int row = 0; row < 4; ++row)
		{
			for(int col = 0; col < 4; ++col)
			{
				if(row == col)
				{
					this.m_Matrix[row][col] = 1;
				}
				else
				{
					this.m_Matrix[row][col] = 0;
				}
			}
		}
		
		return this;
	}
	
	public Matrix4f InitScreenSpaceTransform(float _halfWidth, float _halfHeight)
	{
		this.InitIdentity();
		
		this.m_Matrix[0][0] = +1 * _halfWidth;
		this.m_Matrix[0][3] = +1 * _halfWidth;
		this.m_Matrix[1][1] = -1 * _halfHeight;
		this.m_Matrix[1][3] = +1 * _halfHeight;
		
		return this;
	}
	
	public Matrix4f InitTranslation(float _x, float _y, float _z)
	{
		this.InitIdentity();
		
		this.m_Matrix[0][3] = _x;
		this.m_Matrix[1][3] = _y;
		this.m_Matrix[2][3] = _z;
		
		return this;
	}
	
	public Matrix4f InitRotation(float _x, float _y, float _z, float _angle)
	{
		this.InitIdentity();
		
		float sin = (float)Math.sin(_angle);
		float cos = (float)Math.cos(_angle);
		
		this.m_Matrix[0][0] = cos + _x * _x * (1 - cos);
		this.m_Matrix[0][1] = _x * _y * (1 - cos) - _z * sin;
		this.m_Matrix[0][2] = _x * _z * (1 - cos) + _y * sin;
		this.m_Matrix[1][0] = _y * _x * (1 - cos) + _z * sin;
		this.m_Matrix[1][1] = cos + _y * _y * (1 - cos);
		this.m_Matrix[1][2] = _y * _z * (1 - cos) - _x * sin;
		this.m_Matrix[2][0] = _z * _x * (1 - cos) - _y * sin;
		this.m_Matrix[2][1] = _z * _y * (1 - cos) + _x * sin;
		this.m_Matrix[2][2] = cos + _z * _z * (1 - cos);
		
		return this;
	}
	
	public Matrix4f InitRotation(float _x, float _y, float _z)
	{
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();

		rz.InitIdentity();
		rz.SetElement(0, 0, (float)Math.cos(_z));
		rz.SetElement(0, 1, (float)Math.sin(_z) * -1.0f);
		rz.SetElement(1, 0, (float)Math.sin(_z));
		rz.SetElement(1, 1, (float)Math.cos(_z));

		rx.InitIdentity();
		rx.SetElement(1, 1, (float)Math.cos(_x));
		rx.SetElement(1, 2, (float)Math.sin(_x) * -1.0f);
		rx.SetElement(2, 1, (float)Math.sin(_x));
		rx.SetElement(2, 2, (float)Math.cos(_x));
		
		ry.InitIdentity();
		ry.SetElement(0, 0, (float)Math.cos(_y));
		ry.SetElement(0, 2, (float)Math.sin(_y) * -1.0f);
		ry.SetElement(2, 0, (float)Math.sin(_y));
		ry.SetElement(2, 2, (float)Math.cos(_y));
		
		this.m_Matrix = rz.MatMul(ry.MatMul(rx)).GetMatrix();

		return this;
	}
	
	public Matrix4f InitRotation(Vector4f _forward, Vector4f _up)
	{
		Vector4f f = _forward.Normalized();

		Vector4f r = _up.Normalized();
		r = r.Cross(f);

		Vector4f u = f.Cross(r);

		return this.InitRotation(f, u, r);
	}
	
	public Matrix4f InitRotation(Vector4f _forward, Vector4f _up, Vector4f _right)
	{
		this.InitIdentity();
		
		Vector4f f = _forward;
		Vector4f r = _right;
		Vector4f u = _up;

		this.m_Matrix[0][0] = r.GetX();
		this.m_Matrix[0][1] = r.GetY();
		this.m_Matrix[0][2] = r.GetZ();
		this.m_Matrix[1][0] = u.GetX();
		this.m_Matrix[1][1] = u.GetY();
		this.m_Matrix[1][2] = u.GetZ();
		this.m_Matrix[2][0] = f.GetX();
		this.m_Matrix[2][1] = f.GetY();
		this.m_Matrix[2][2] = f.GetZ();

		return this;
	}
	
	public Matrix4f InitScale(float _x, float _y, float _z)
	{
		this.InitIdentity();
		
		this.m_Matrix[0][0] = _x;
		this.m_Matrix[1][1] = _y;
		this.m_Matrix[2][2] = _z;
		
		return this;
	}
	
	public Matrix4f InitPerspective(float _fov, float _aspectRatio, float _zNear, float _zFar)
	{
		this.FillZero();
		
		float tanHalfFOV = (float)Math.tan(_fov / 2.0f);
		float zRange = _zNear - _zFar;

		this.m_Matrix[0][0] = +1.0f / (tanHalfFOV * _aspectRatio);
		this.m_Matrix[1][1] = +1.0f / tanHalfFOV;
		this.m_Matrix[2][2] = -1.0f * (_zNear + _zFar) / zRange;
		this.m_Matrix[2][3] = +2.0f * _zFar * _zNear / zRange;
		this.m_Matrix[3][2] = +1.0f;

		return this;
	}
	
	public Matrix4f InitOrthographic(float _left, float _right, float _bottom, float _top, float _near, float _far)
	{
		this.InitIdentity();
		
		float width = _right - _left;
		float height = _top - _bottom;
		float depth = _far - _near;

		this.m_Matrix[0][0] = +2.0f / width;
		this.m_Matrix[0][3] = -1.0f * (_right + _left) / width;
		this.m_Matrix[1][1] = +2.0f / height;
		this.m_Matrix[1][3] = -1.0f * (_top + _bottom) / height;
		this.m_Matrix[2][2] = -2.0f / depth;
		this.m_Matrix[2][3] = -1.0f * (_far + _near) / depth;
		
		return this;
	}
	
	public Vector4f Transform(Vector4f _r)
	{
		float[] v = new float[4];
		
		for(int row = 0; row < 4; ++row)
		{
			v[row] += this.m_Matrix[row][0] * _r.GetX();
		}
		for(int row = 0; row < 4; ++row)
		{
			v[row] += this.m_Matrix[row][1] * _r.GetY();
		}
		for(int row = 0; row < 4; ++row)
		{
			v[row] += this.m_Matrix[row][2] * _r.GetZ();
		}
		for(int row = 0; row < 4; ++row)
		{
			v[row] += this.m_Matrix[row][3] * _r.GetW();
		}
		
		return new Vector4f(v[0], v[1], v[2], v[3]);
	}
	
	public Matrix4f MatMul(Matrix4f _r)
	{
		Matrix4f retVal = new Matrix4f();

		for(int row = 0; row < 4; ++row)
		{
			for(int col = 0; col < 4; ++col)
			{
				float sum = 0.0f;
				
				for(int i = 0; i < 4; ++i)
				{
					sum += this.m_Matrix[row][i] * _r.GetElement(i, col);
				}
				
				retVal.SetElement(row, col, sum);
			}
		}

		return retVal;
	}
	
	public float[][] GetMatrix()
	{
		float[][] retVal = new float[4][4];
		
		for(int row = 0; row < 4; ++row)
		{
			for(int col = 0; col < 4; ++col)
			{
				retVal[row][col] = this.m_Matrix[row][col];
			}
		}
		
		return retVal;
	}
	
	public float GetElement(int _row, int _col)
	{
		return this.m_Matrix[_row][_col];
	}
	
	public void SetMatrix(float[][] _matrix)
	{
		this.m_Matrix = _matrix;
	}
	
	public void SetElement(int _row, int _col, float _val)
	{
		this.m_Matrix[_row][_col] = _val;
	}
	
	private void FillZero()
	{
		for(int row = 0; row < 4; ++row)
		{
			for(int col = 0; col < 4; ++col)
			{
				this.m_Matrix[row][col] = 0.0f;
			}
		}
	}
}
