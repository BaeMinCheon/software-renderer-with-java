
public class Main
{
	public static void main(String[] args)
	{
		Display display = new Display(800, 600, "software-renderer-with-java");
		RenderContext target = display.GetFrameBuffer();
		Stars3D stars = new Stars3D(4096, 64.0f, 20.0f);
		
		long timePrevious = System.nanoTime();
		while(true)
		{
			long timeCurrent = System.nanoTime();
			float timeDelta = (float)((timeCurrent - timePrevious) / 1000000000.0f);
			timePrevious = timeCurrent;
			
			//stars.UpdateAndRender(target, timeDelta);
			target.Clear((byte)0x00);
			for(int i = 100; i < 200; ++i)
			{
				target.DrawScanBuffer(i, 300 - i, 300 + i);
			}
			target.FillShape(100, 200);
			display.SwapBuffers();
		}
	}
}
