package com.oosd.monopolygame.action;
public class PlayerAction {
    private Command command;
    public PlayerAction(Command newCommand) {
        command = newCommand;
    }
    public void takeAction() {
        command.execute();
    }

}
