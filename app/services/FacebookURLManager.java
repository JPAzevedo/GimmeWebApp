package services;

import entities.RemoteSession;
import util.ConfigManager;

import java.io.IOException;
import java.util.Properties;

public class FacebookURLManager {

   private static String CONFIG_PATH =  "C:\\Users\\joaop\\Documents\\GimmeWebApp\\conf\\config.properties";

    public static String getFacebookDebugURL(RemoteSession remoteSession){
        Properties config = getConfiguration();

        if(config == null){
            return null;
        }

        String url = config.getProperty("facebook_graph_api_auth");
        String appSecret = config.getProperty("facebook_app_secret");

        return url.replace("$1",remoteSession.getToken())
                    .replace("$2",remoteSession.geAppId())
                    .replace("$3",appSecret);
    }

    public static String getUserInfoURL(RemoteSession remoteSession){
        Properties config = getConfiguration();

        if(config == null){
            return null;
        }

        String url = config.getProperty("facebook_graph_api_user_info");

        return url.replace("$1",remoteSession.getUserId())
                .replace("$2",remoteSession.getToken());
    }

    private static Properties getConfiguration(){
        ConfigManager configManager = new ConfigManager(CONFIG_PATH);

        Properties properties = new Properties();
        try {
            properties.load(configManager.getConfInputStream());
            return properties;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
