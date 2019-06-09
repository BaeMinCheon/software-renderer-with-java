
public class Main
{
	public static void main(String[] args)
	{
		Display display = new Display(800, 600, "software-renderer-with-java");
		RenderContext target = display.GetFrameBuffer();
		Stars3D stars = new Stars3D(4096, 64.0f, 20.0f);
		
		Vertex minY = new Vertex(100, 100);
		Vertex midY = new Vertex(150, 200);
		Vertex maxY = new Vertex(80, 300);
		
		long timePrevious = System.nanoTime();
		while(true)
		{
			long timeCurrent = System.nanoTime();
			float timeDelta = (float)((timeCurrent - timePrevious) / 1000000000.0f);
			timePrevious = timeCurrent;
			
			//stars.UpdateAndRender(target, timeDelta);
			target.Clear((byte)0x00);
//			for(int i = 100; i < 200; ++i)
//			{
//				target.DrawScanBuffer(i, 300 - i, 300 + i);
//			}
			target.ScanConvertTriangle(minY, midY, maxY, 0);
			target.FillShape(100, 300);
			display.SwapBuffers();
		}
	}
}
