package org.gandini.robot.reddit.response.utils;

import com.google.common.reflect.ClassPath;
import org.gandini.robot.reddit.response.TextResponseInterface;

import java.util.ArrayList;
import java.util.List;

public class CommandsList {

    private static final String SOURCE = "org.gandini.robot.reddit.response.commands";

    public static List<String> getCommands() throws Exception {

        List<String> classList = new ArrayList<>();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        ClassPath classpath = ClassPath.from(loader);
        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClassesRecursive(SOURCE)) {
            @SuppressWarnings("unchecked")
            Class<TextResponseInterface> commands = (Class<TextResponseInterface>) classInfo.load();
            classList.add(commands.getSimpleName());
        }
        return classList;
    }
}
