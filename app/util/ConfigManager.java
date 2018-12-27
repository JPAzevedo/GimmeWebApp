package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigManager {

    private static String ENV_CONFIG_NAME = "ENV_GIMME_CONFIG_PATH";
    private String path;

    public ConfigManager(String path){
        this.path = path;

        if(path==null){
            this.path = System.getenv(ENV_CONFIG_NAME);
        }
    }

    public ConfigManager(){
        this.path = System.getenv(ENV_CONFIG_NAME);
    }

    public String getConfPath() throws IOException {

        if (path == null){
            throw new IOException("There is no configuration file");
        }

        File cFile = new File(path);
        return cFile.getAbsolutePath();
    }

    public File getConfFile() throws IOException {
        return new File(getConfPath());
    }

    public InputStream getConfInputStream() throws IOException {
        return new FileInputStream(getConfFile());
    }


}
