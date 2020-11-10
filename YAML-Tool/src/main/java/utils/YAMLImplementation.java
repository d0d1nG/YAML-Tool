package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import models.Entity;

public class YAMLImplementation extends API
{
	@Override
	public void openFile(File file) 
	{
		Yaml yaml = new Yaml(new Constructor(Entity.class));
		
		try 
		{
			InputStream inputStream = new FileInputStream(file);
			
		    for (Object e : yaml.loadAll(inputStream)) 
		    {
				entities.add((Entity) e);
		    }
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		

	}

	@Override
	public void enterEntity(String arg0)
	{
		
	}

	@Override
	public void save(List<Entity> data, String fileName) 
	{
		try
		{
			File file = new File("export/" + fileName);
			
			file.createNewFile();
			
			FileWriter fw = new FileWriter("export/" + fileName);
			
			for(Entity entity: data)
			{
			    Yaml yaml = new Yaml();
			    StringWriter object = new StringWriter();
			    yaml.dump(entity, object);  
			    fw.write(object.toString());
			    fw.write("---\n");
			}
			fw.flush();
			fw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
