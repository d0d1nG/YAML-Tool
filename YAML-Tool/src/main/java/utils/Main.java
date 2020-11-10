package utils;

import java.io.File;

public class Main {

	public static void main(String[] args)
	{
		YAMLImplementation yaml = new YAMLImplementation();
		yaml.openDir("podaci");
		yaml.save(yaml.entities, "export.yaml");
	}

}
