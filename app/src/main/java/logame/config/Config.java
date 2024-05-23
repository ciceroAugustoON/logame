package logame.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Config {
    private static Config config;

    private File configFile;

    private Config() {
        String resource = Config.class.getClassLoader().getResource("").getPath();
        String path = resource + File.separator + "config.json";
        
        configFile = new File(path);

        try {
            if (configFile.createNewFile()) {
                System.out.println(path);
            } else {
                System.out.println("Arquivo já existe");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config load() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public <T> void create(String config, T value) {
        ObjectMapper om = new ObjectMapper();

        try {
            if (isEmpty()) {
                Map<String, T> newconfig = new HashMap<>();
                newconfig.put(config, value);
                om.writeValue(configFile, newconfig);
            } else {
                Map<String, Object> conf = om.readValue(configFile, new TypeReference<Map<String, Object>>() {});
                conf.put(config, value);
                om.writeValue(configFile, conf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> void update(String config, T newValue) {
        ObjectMapper om = new ObjectMapper();

        try {
            Map<String, Object> configs = om.readValue(configFile, new TypeReference<Map<String, Object>>() {});

            configs.replace(config, newValue);
            om.writeValue(configFile, configs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T get(String config) {
        ObjectMapper om = new ObjectMapper();

        try {
            Map<String, T> configs = om.readValue(configFile, new TypeReference<Map<String, T>>() {});

            return configs.get(config);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isEmpty() {
        try (FileReader fr = new FileReader(configFile); BufferedReader br = new BufferedReader(fr)) {
            if (br.readLine() == null) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }
}
