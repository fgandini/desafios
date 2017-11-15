package org.gandini.robot.reddit.response.commands;

import org.gandini.robot.reddit.response.TextResponseInterface;
import org.gandini.robot.reddit.response.utils.CommandsList;

import java.util.List;
import java.util.stream.Collectors;

public class Ajuda implements TextResponseInterface {

    private final String MESSAGE = "Da uma olhada na minha lista de comandos:\n/";
    private final String MESSAGE_ERROR = "Xiiiii...pode voltar mais tarde!";
    private final String MESSAGE_DEFAULT = "N\u00E3o sei fazer nada! Melhor conversar com um amigo! ";

    @Override
    public String getResponseText(String args) {
        try {
            List<String> commands = CommandsList.getCommands();
            if (!commands.isEmpty()) {
                return MESSAGE + commands.stream().collect(Collectors.joining("\n/"));
            }
        } catch (Exception e) {
            return MESSAGE_ERROR;
        }
        return MESSAGE_DEFAULT;
    }

    @Override
    public String getHelp(String args) {
        return null;
    }
}
