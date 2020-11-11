package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

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

            for(Entity entity: data)
            {
                Yaml yaml = new Yaml();
                String text = yaml.dumpAs(entity, Tag.MAP, FlowStyle.BLOCK);
                fw.write(text + "---\n");
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
	public void brisiKljucVrednost(String arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void brisiNaOsnovuIDa(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}
}
