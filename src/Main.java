
public class Main
{
	public static void main(String[] args)
	{
		Display display = new Display(800, 600, "software-renderer-with-java");
		RenderContext target = display.GetFrameBuffer();
		Triangle3D triangle = new Triangle3D(64.0f, 4.0f);
		
		long timePrevious = System.nanoTime();
		while(true)
		{
			long timeCurrent = System.nanoTime();
			float timeDelta = (float)((timeCurrent - timePrevious) / 1000000000.0f);
			timePrevious = timeCurrent;
			
			triangle.UpdateAndRender(target, timeDelta);
			
			display.SwapBuffers();
		}
	}
}
