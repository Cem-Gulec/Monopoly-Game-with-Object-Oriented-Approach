package com.oosd.monopolygame.action;

/**
 *  This is an invoker class. It provides decoupling between MonopolyGame and Player
 *  classes but we can't exactly say rest of the code is decoupled.
 *
 *  It runs execute method of related command.
 */
public class PlayerAction {
    private Command command;

    public void setCommand(Command newCommand) {
        command = newCommand;
    }
    public void takeAction() {
        command.execute();
    }

}
