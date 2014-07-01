package fr.mff.tutos.ogl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

public class OpenGLTuto1
{

	public static void main(String[] args)
	{
		try
		{
			Display.create();
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
		}
	}

}
