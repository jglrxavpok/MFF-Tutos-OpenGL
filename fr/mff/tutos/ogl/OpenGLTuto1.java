package fr.mff.tutos.ogl;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.FloatBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class OpenGLTuto1
{

	private static int textureID;

	public static void main(String[] args)
	{
		try
		{
			Display.create();
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, 800, 0, 600, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			glEnable (GL_BLEND);
			glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glEnable(GL_TEXTURE_2D);
			loadTexture();
			while(!Display.isCloseRequested())
				tick();
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void loadTexture()
	{
		textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureID);
		try
		{
			BufferedImage img = ImageIO.read(OpenGLTuto1.class.getResourceAsStream("/OpenGL_Logo.png"));
			int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
			FloatBuffer pixelsBuffer = BufferUtils.createByteBuffer(pixels.length * 4 * 4).asFloatBuffer();
			for(int y = img.getHeight()-1;y>=0;y--)
			{
				for(int x = 0;x<img.getWidth();x++)
				{
					int pixel = pixels[y*img.getWidth()+x];
					float a = ((pixel >> 24) & 0xFF)/255f;
					float r = ((pixel >> 16) & 0xFF)/255f;
					float g = ((pixel >> 8) & 0xFF)/255f;
					float b = ((pixel >> 0) & 0xFF)/255f;
    				
					pixelsBuffer.put( r);
					pixelsBuffer.put(g);
					pixelsBuffer.put(b);
					pixelsBuffer.put(a);
				}
			}
			
			pixelsBuffer.flip();
			
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, img.getWidth(), img.getHeight(), 0, GL_RGBA, GL_FLOAT, pixelsBuffer);
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void tick()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glBindTexture(GL_TEXTURE_2D, textureID);
		glPushMatrix();
		glTranslated(50, 50, 0);
		glBegin(GL_QUADS);
		
			glTexCoord2d(0, 0);
			glVertex2d(0, 0);
			
			glTexCoord2d(0, 1);
			glVertex2d(0, 149);
			
			glTexCoord2d(1, 1);
			glVertex2d(300, 149);
			
			glTexCoord2d(1, 0);
			glVertex2d(300, 0);
			
		glEnd();
		glPopMatrix();
		Display.update();
		Display.sync(60);
	}
}
