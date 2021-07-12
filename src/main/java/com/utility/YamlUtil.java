package com.utility;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;


public class YamlUtil {
	
	
	public Map <String,String> getTestMapData()
	{
		Yaml yaml= new Yaml();
		Map <String,String> mapValue = null;
		try
		{
		InputStream inputStream= YamlUtil.class.getClassLoader().getResourceAsStream("resources/config.yaml");
		 mapValue	=yaml.load(inputStream);
	
		}
		
		catch(Exception e)
		{
		System.out.println("not able to load Yaml.file.Please correct yaml file "+e.getMessage());	
		}
	
		return mapValue;
				
	}
	
	public String getvalefromYaml(String key  ){
		
		Map <String,String> mapValue=getTestMapData();
		
		String value = null;
		try{
			if(!mapValue.isEmpty())
			 value=	mapValue.get(key);
		}
		catch(Exception e)
		{
			System.out.println("Please correct the data to fetch from YAMl "+e.getMessage());	
			
		}
	
		
		 return value;
		
	}

}
