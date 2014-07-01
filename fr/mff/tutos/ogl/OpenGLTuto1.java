package fr.mff.tutos.ogl;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class OpenGLTuto1
{

	private static int ticks;

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
		ticks++;
		glRecti(ticks, 0, ticks+100, 100);
		Display.update();
		Display.sync(60);
	}

}
