
public class Main
{
	public static void main(String[] args)
	{
		Display display = new Display(800, 600, "software-renderer-with-java");
		RenderContext target = display.GetFrameBuffer();
		
		Vertex minY = new Vertex(new Vector4f(-1.0f, -1.0f, 0.0f, 1.0f), new Vector4f(1.0f, 0.0f, 0.0f, 0.0f));
		Vertex midY = new Vertex(new Vector4f(0.0f, 1.0f, 0.0f, 1.0f), new Vector4f(0.0f, 1.0f, 0.0f, 0.0f));
		Vertex maxY = new Vertex(new Vector4f(1.0f, -1.0f, 0.0f, 1.0f), new Vector4f(0.0f, 0.0f, 1.0f, 0.0f));
		float fov = (float)Math.toRadians(70.0f);
		float aspectRatio = (float)target.GetWidth() / (float)target.GetHeight();
		float zNear = 0.1f;
		float zFar = 1000.0f;
		Matrix4f projection = new Matrix4f().InitPerspective(fov, aspectRatio, zNear, zFar);
		
		float rotAngle = 0.0f;
		long timePrevious = System.nanoTime();
		while(true)
		{
			long timeCurrent = System.nanoTime();
			float timeDelta = (float)((timeCurrent - timePrevious) / 1000000000.0f);
			timePrevious = timeCurrent;
			
			rotAngle += timeDelta;
			Matrix4f translation = new Matrix4f().InitTranslation(0.0f, 0.0f, 3.0f);
			Matrix4f rotation = new Matrix4f().InitRotation(0.0f, rotAngle, 0.0f);
			Matrix4f transform = projection.MatMul(translation.MatMul(rotation));
			target.Clear((byte)0x00);
			target.FillTriangle(maxY.Transform(transform), midY.Transform(transform), minY.Transform(transform));
			
			display.SwapBuffers();
		}
	}
}
