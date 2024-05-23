package logame;

import logame.config.Config;

public class App {
    public static void main(String[] args) {
        Config config = Config.load();
        config.create("DBLoaded", true);
        config.update("DBLoaded", false);

        config.create("DarkMode", false);

        Boolean opt = config.get("DarkMode");
        System.out.println(opt);
    }
}
