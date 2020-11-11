package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import models.Entity;

public class YAMLImplementation extends API
{
	static {
		ToolManager.registerManager(new YAMLImplementation(), ".yaml");
	}
	public YAMLImplementation() {
		super();
	}
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
            
            int count = 0;
            for(Entity entity: data)
            {
            	count++;
            	DumperOptions options = new DumperOptions();
            	ConfigurationModelRepresenter customRepresenter = new ConfigurationModelRepresenter();
                Yaml yaml = new Yaml(customRepresenter, options);
                String text = yaml.dumpAs(entity, Tag.MAP, FlowStyle.BLOCK);
                
                if(count == data.size())
                {
                	fw.write(text + "---");
                }
                else
                {
                    fw.write(text + "---\n");
                }
                
                for(Map.Entry<String, Entity> ent : entity.getEntities().entrySet()) 
				{
                	System.out.println("entitet " + entity.getName() + " ima entitete: " + " ime:" + ent.getKey() + " vrednost:" + ent.getValue());
                	
//                	for(Map.Entry<String, Object> property : ent.getProperties().entrySet()) 
//					{
//                		System.out.println(property);
//					}
				}
            }
            fw.flush();
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	
	@Override
	public void brisiKljucVrednost(String name_, String key, String value_, String dirPath) 
	{
		entities.clear();
		
		File directory = new File(dirPath);
		File[] contents = directory.listFiles();
		
		for(File f : contents)
		{
			List<Entity> entities1 = new ArrayList<>();
			
			if(f.length() > 0)
			{
				Yaml yaml = new Yaml(new Constructor(Entity.class));
				
				try 
				{
					InputStream inputStream = new FileInputStream(f);
					
				    for (Object e : yaml.loadAll(inputStream)) 
				    {
				    	Entity entitet = (Entity)e;
				    	if(!entitet.getName().equals(name_))
				    	{
							entities1.add(entitet);
				    	}
				    }
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				}
			}
			try
			{
				File file = new File(f.getAbsolutePath());
				
				file.createNewFile();
				
				FileWriter fw = new FileWriter(f.getAbsolutePath());
				
				int count = 0;
				
				for(Entity entity: entities1)
				{
					count++;
					DumperOptions options = new DumperOptions();
	            	ConfigurationModelRepresenter customRepresenter = new ConfigurationModelRepresenter();
	                Yaml yaml = new Yaml(customRepresenter, options);
	                String text = yaml.dumpAs(entity, Tag.MAP, FlowStyle.BLOCK);
	                
	                if(count == entities1.size())
	                {
	                	text = text.substring(0, text.length() - 1);
	                	fw.write(text);
	                }
	                else
	                {
	                    fw.write(text + "---\n");
	                }
				}
				fw.flush();
				fw.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			entities.addAll(entities1);
		}
	}
	@Override
	public void brisiNaOsnovuIDa(String id, String dirPath) 
	{
		entities.clear();
		
		File directory = new File(dirPath);
		File[] contents = directory.listFiles();
		
		for(File f : contents)
		{
			List<Entity> entities1 = new ArrayList<>();
			
			if(f.length() > 0)
			{
				Yaml yaml = new Yaml(new Constructor(Entity.class));
				
				try 
				{
					InputStream inputStream = new FileInputStream(f);
					
				    for (Object e : yaml.loadAll(inputStream)) 
				    {
				    	Entity entitet = (Entity)e;
				    	if(!entitet.getId().equals(id))
				    	{
							entities1.add(entitet);
				    	}
				    }
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				}
			}
			try
			{
				File file = new File(f.getAbsolutePath());
				
				file.createNewFile();
				
				FileWriter fw = new FileWriter(f.getAbsolutePath());
				
				int count = 0;
				
				for(Entity entity: entities1)
				{
					count++;
					DumperOptions options = new DumperOptions();
	            	ConfigurationModelRepresenter customRepresenter = new ConfigurationModelRepresenter();
	                Yaml yaml = new Yaml(customRepresenter, options);
	                String text = yaml.dumpAs(entity, Tag.MAP, FlowStyle.BLOCK);
	                
	                if(count == entities1.size())
	                {
	                	text = text.substring(0, text.length() - 1);
	                	fw.write(text);
	                }
	                else
	                {
	                    fw.write(text + "---\n");
	                }
				}
				fw.flush();
				fw.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			entities.addAll(entities1);
		}
	}
}
