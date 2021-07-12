package com.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderUtils {

	private Properties props;
	
	
	public Properties propload(){
		
		props=new Properties();
		
		try {
			FileInputStream fileInputStream=new FileInputStream("resources\\ApplicationConfig.properties");
			props.load(fileInputStream);
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return props;
	}
	
}
