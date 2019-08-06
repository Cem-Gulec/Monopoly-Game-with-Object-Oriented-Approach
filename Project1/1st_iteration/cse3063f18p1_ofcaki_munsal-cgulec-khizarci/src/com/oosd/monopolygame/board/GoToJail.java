package com.oosd.monopolygame.board;

public class GoToJail implements BoardPart {
    @Override
    public void accept(BoardPartVisitor boardPartVisitor) {
        boardPartVisitor.visit(this);
    }
}
