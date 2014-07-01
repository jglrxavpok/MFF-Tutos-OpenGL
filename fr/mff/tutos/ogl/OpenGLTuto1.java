package fr.mff.tutos.ogl;

import static org.lwjgl.opengl.GL11.*;

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
		glClear(GL_COLOR_BUFFER_BIT);
		glTranslated(1, 0, 0);
		glBegin(GL_TRIANGLES);
			glColor3f(0, 0, 1);
			glVertex2d(0, 100);
			glColor3f(0, 1, 0);
			glVertex2d(50, 100+100);
			glColor3f(1, 0, 0);
			glVertex2d(100, 100);
		glEnd();
		Display.update();
		Display.sync(60);
	}

}
