package org.gandini.robot.configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {

    public static BotConfig botConfig;

    public static void load(String path) throws IOException {
        Yaml yaml = new Yaml();

        try (InputStream in = Files.newInputStream(Paths.get(path))) {
            botConfig = yaml.loadAs(in, BotConfig.class);
        }
    }


}
