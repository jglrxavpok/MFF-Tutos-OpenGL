package fr.mff.tutos.ogl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class OpenGLTuto1
{

	public static void main(String[] args)
	{
		try
		{
			Display.create();
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, 800, 0, 600, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			while(!Display.isCloseRequested())
				tick();
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
		}
	}

	private static void tick()
	{
		Display.sync(60);
		Display.update();
	}

}
