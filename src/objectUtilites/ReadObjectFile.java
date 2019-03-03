package objectUtilites;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObjectFile {

	Properties p = new Properties();
	
	public Properties getObjectRepository() throws IOException{
		//E:\Work\JavaLearningClasses2\KeywordDrivenFramework
		File file = new File(System.getProperty("user.dir")+"\\Resources\\object.properties");
		InputStream stream = new FileInputStream(file);
		p.load(stream);
	
		return p;
	}
	
}
