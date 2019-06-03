import java.util.Arrays;

public class Bitmap
{
	private final int m_Width;
	private final int m_Height;
	private final char[] m_ByteArray;
	
	public Bitmap(int _width, int _height)
	{
		this.m_Width = _width;
		this.m_Height = _height;
		this.m_ByteArray = new char[_width * _height * 4];
	}
	
	public void Clear(char _shade)
	{
		Arrays.fill(this.m_ByteArray, _shade);
	}
	
	public void DrawPixel(int _x, int _y, char _a, char _r, char _g, char _b)
	{
		int index_pixel = (this.m_Width * _y) + _x;
		int index_bytes = index_pixel * 4;
		
		this.m_ByteArray[index_bytes + 0] = _a;
		this.m_ByteArray[index_bytes + 1] = _r;
		this.m_ByteArray[index_bytes + 2] = _g;
		this.m_ByteArray[index_bytes + 3] = _b; 
	}
	
	public void CopyToIntArray(int[] _dest)
	{
		for(int i = 0; i < (this.m_Width * this.m_Height); ++i)
		{
			int a = (int)(this.m_ByteArray[i * 4 + 0]);
			int r = (int)(this.m_ByteArray[i * 4 + 1]);
			int g = (int)(this.m_ByteArray[i * 4 + 2]);
			int b = (int)(this.m_ByteArray[i * 4 + 3]);
			
			// ARGB with MSB
			_dest[i] = (a << 24) | (r << 16) | (g << 8) | (b << 0);
		}
	}
}
