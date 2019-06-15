
public class Vector4f
{
	private final float m_X;
	private final float m_Y;
	private final float m_Z;
	private final float m_W;

	public Vector4f(float x, float y, float z, float w)
	{
		this.m_X = x;
		this.m_Y = y;
		this.m_Z = z;
		this.m_W = w;
	}

	public float Length()
	{
		return (float)Math.sqrt(this.m_X * this.m_X + this.m_Y * this.m_Y + this.m_Z * this.m_Z + this.m_W * this.m_W);
	}

	public float Max()
	{
		return Math.max(Math.max(this.m_X, this.m_Y), Math.max(this.m_Z, this.m_W));
	}

	public float Dot(Vector4f _other)
	{
		return this.m_X * _other.GetX() + this.m_Y * _other.GetY() + this.m_Z * _other.GetZ() + this.m_W * _other.GetW();
	}

	public Vector4f Cross(Vector4f _other)
	{
		float x_ = this.m_Y * _other.GetZ() - this.m_Z * _other.GetY();
		float y_ = this.m_Z * _other.GetX() - this.m_X * _other.GetZ();
		float z_ = this.m_X * _other.GetY() - this.m_Y * _other.GetX();

		return new Vector4f(x_, y_, z_, 0);
	}

	public Vector4f Normalized()
	{
		float length = Length();

		return new Vector4f(this.m_X / length, this.m_Y / length, this.m_Z / length, this.m_W / length);
	}

	public Vector4f Rotate(Vector4f _axis, float _angle)
	{
		float sinAngle = (float)Math.sin(-_angle);
		float cosAngle = (float)Math.cos(-_angle);

		Vector4f rotationX = this.Cross(_axis.Mul(sinAngle));					// rotation on local X
		Vector4f rotationZ = this.Mul(cosAngle);								// rotation on local Z
		Vector4f rotationY = _axis.Mul(this.Dot(_axis.Mul(1.0f - cosAngle)));	// rotation on local Y
		
		return rotationX.Add(rotationZ.Add(rotationY));
	}

	public Vector4f Lerp(Vector4f _dest, float _lerpFactor)
	{
		return _dest.Sub(this).Mul(_lerpFactor).Add(this);
	}

	public Vector4f Add(Vector4f _other)
	{
		return new Vector4f(this.m_X + _other.GetX(), this.m_Y + _other.GetY(), this.m_Z + _other.GetZ(), this.m_W + _other.GetW());
	}

	public Vector4f Add(float _other)
	{
		return new Vector4f(this.m_X + _other, this.m_Y + _other, this.m_Z + _other, this.m_W + _other);
	}

	public Vector4f Sub(Vector4f _other)
	{
		return new Vector4f(this.m_X - _other.GetX(), this.m_Y - _other.GetY(), this.m_Z - _other.GetZ(), this.m_W - _other.GetW());
	}

	public Vector4f Sub(float _other)
	{
		return new Vector4f(this.m_X - _other, this.m_Y - _other, this.m_Z - _other, this.m_W - _other);
	}

	public Vector4f Mul(Vector4f _other)
	{
		return new Vector4f(this.m_X * _other.GetX(), this.m_Y * _other.GetY(), this.m_Z * _other.GetZ(), this.m_W * _other.GetW());
	}

	public Vector4f Mul(float _other)
	{
		return new Vector4f(this.m_X * _other, this.m_Y * _other, this.m_Z * _other, this.m_W * _other);
	}

	public Vector4f Div(Vector4f _other)
	{
		return new Vector4f(this.m_X / _other.GetX(), this.m_Y / _other.GetY(), this.m_Z / _other.GetZ(), this.m_W / _other.GetW());
	}

	public Vector4f Div(float _other)
	{
		return new Vector4f(this.m_X / _other, this.m_Y / _other, this.m_Z / _other, this.m_W / _other);
	}

	public Vector4f Abs()
	{
		return new Vector4f(Math.abs(this.m_X), Math.abs(this.m_Y), Math.abs(this.m_Z), Math.abs(this.m_W));
	}

	public String toString()
	{
		return "(" + this.m_X + ", " + this.m_Y + ", " + this.m_Z + ", " + this.m_W + ")";
	}

	public float GetX()
	{
		return this.m_X;
	}

	public float GetY()
	{
		return this.m_Y;
	}

	public float GetZ()
	{
		return this.m_Z;
	}

	public float GetW()
	{
		return this.m_W;
	}

	public boolean equals(Vector4f _other)
	{
		return this.m_X == _other.GetX() && this.m_Y == _other.GetY() && this.m_Z == _other.GetZ() && this.m_W == _other.GetW();
	}
}