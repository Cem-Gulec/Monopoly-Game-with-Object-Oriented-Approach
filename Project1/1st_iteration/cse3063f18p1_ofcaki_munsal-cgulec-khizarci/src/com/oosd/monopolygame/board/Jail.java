package com.oosd.monopolygame.board;

public class Jail implements BoardPart {
    @Override
    public void accept(BoardPartVisitor boardPartVisitor) {
        boardPartVisitor.visit(this);
    }
}
