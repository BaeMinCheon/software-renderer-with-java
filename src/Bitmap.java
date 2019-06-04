import java.util.Arrays;

public class Bitmap
{
	private final int m_Width;
	private final int m_Height;
	private final byte[] m_Bytes;
	
	public Bitmap(int _width, int _height)
	{
		this.m_Width = _width;
		this.m_Height = _height;
		this.m_Bytes = new byte[_width * _height * 4];
	}
	
	public void Clear(byte _shade)
	{
		Arrays.fill(this.m_Bytes, _shade);
	}
	
	public void DrawPixel(int _x, int _y, byte _a, byte _b, byte _g, byte _r)
	{
		int index_pixel = (this.m_Width * _y) + _x;
		int index_bytes = index_pixel * 4;
		
		this.m_Bytes[index_bytes + 0] = _a;
		this.m_Bytes[index_bytes + 1] = _b;
		this.m_Bytes[index_bytes + 2] = _g;
		this.m_Bytes[index_bytes + 3] = _r; 
	}
	
	public void CopyToByteArray(byte[] _dest)
	{
		for(int i = 0; i < (this.m_Width * this.m_Height); ++i)
		{
			// dest : bgr <- byteArray : abgr
			_dest[i * 3 + 0] = this.m_Bytes[i * 4 + 1];
			_dest[i * 3 + 1] = this.m_Bytes[i * 4 + 2];
			_dest[i * 3 + 2] = this.m_Bytes[i * 4 + 3];
		}
	}
	
	public int GetWidth()
	{
		return this.m_Width;
	}
	
	public int GetHeight()
	{
		return this.m_Height;
	}
}
