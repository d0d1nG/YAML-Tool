package utils;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import models.Entity;

public class YAMLImplementation extends API{

	@Override
	public void openFile(File file) {
		Yaml yaml = new Yaml();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(file.getPath());
		Entity entity = yaml.load(inputStream);
		entities.add(entity);
	}

}
