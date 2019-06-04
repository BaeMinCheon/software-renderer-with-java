
public class Main
{
	public static void main(String[] args)
	{
		Display display = new Display(800, 600, "software-renderer-with-java");
		
		while(true)
		{
			display.SwapBuffers();
		}
	}
}
