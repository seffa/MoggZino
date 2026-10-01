package org.example;

public class Slon {
    private static final String CMD_HELP = "\\help";
    private static final String CMD_EXIT = "\\exit";

    private static final String QUESTION = "Купи слона!";
    private static final String REPEAT_QUESTION = "А ты купи слона!";

    public String greeting() {
        return String.format(
                "Привет!\nЯ – продавец слона\nДля справки используй %s или\n%s\n",
                CMD_HELP,
                QUESTION
        );
    }

    public boolean isExitCommand(String line) {
        return line.equals(CMD_EXIT);
    }

    public String respond(String line) {
        if (line.equals(CMD_HELP)) {
            return greeting();
        }

        return String.format("Все говорят \"%s\".\n%s\n", line, REPEAT_QUESTION);
    }
}
