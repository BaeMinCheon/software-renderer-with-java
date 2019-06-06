
public class Main
{
	public static void main(String[] args)
	{
		Display display = new Display(800, 600, "software-renderer-with-java");
		Bitmap target = display.GetFrameBuffer();
		Stars3D stars = new Stars3D(4096, 64.0f, 20.0f);
		
		long timePrevious = System.nanoTime();
		while(true)
		{
			long timeCurrent = System.nanoTime();
			float timeDelta = (float)((timeCurrent - timePrevious) / 1000000000.0f);
			timePrevious = timeCurrent;
			
			stars.UpdateAndRender(target, timeDelta);
			display.SwapBuffers();
		}
	}
}
