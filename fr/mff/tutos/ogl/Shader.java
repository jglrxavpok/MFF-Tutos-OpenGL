package fr.mff.tutos.ogl;

import static org.lwjgl.opengl.GL20.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.lwjgl.opengl.GL20;

public class Shader
{

	private int programID;

	public Shader(String vertexShader, String fragmentShader)
	{
		String vertexSource = read(vertexShader);
		String fragmentSource = read(fragmentShader);

		this.programID = GL20.glCreateProgram(); // On crée le pointeur

		addSource(vertexSource, GL_VERTEX_SHADER); // On ajoute le code source du vertex shader à notre programme
		addSource(fragmentSource, GL_FRAGMENT_SHADER); // On ajoute le code source du fragment shader à notre programme

		glLinkProgram(programID); // On lie toutes les données ensemble

		if(glGetProgrami(programID, GL_LINK_STATUS) == 0) // On vérifie que tout est bien lié
		{
			System.err.println(glGetProgramInfoLog(programID, 1024));
			return;
		}

		glValidateProgram(programID); // On valide le programme

		if(glGetProgrami(programID, GL_VALIDATE_STATUS) == 0) // On vérifie que la validation n'a pas échouée
		{
			System.err.println(glGetProgramInfoLog(programID, 1024));
			return;
		}
	}

	private void addSource(String source, int type)
	{
		int shader = glCreateShader(type); // On crée la plage mémoire pour le shader

		glShaderSource(shader, source); // On attache la source
		glCompileShader(shader); // On compile le shader

		if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0) // On vérifie qu'il n'y a pas d'erreurs
		{
			System.err.println(glGetShaderInfoLog(shader, 1024));
			return;
		}

		glAttachShader(programID, shader); // On attache le code compilé à notre programme
	}

	public void bind()
	{
		glUseProgram(programID);
	}

	public static String read(String fileName)
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try
		{
			InputStream in = OpenGLTuto1.class.getResourceAsStream(fileName);
			byte[] buffer = new byte[65565];
			int i;
			while((i = in.read(buffer, 0, buffer.length)) != -1)
			{
				out.write(buffer, 0, i);
			}
			out.flush();
			out.close();
			return new String(out.toByteArray(), "UTF-8");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
