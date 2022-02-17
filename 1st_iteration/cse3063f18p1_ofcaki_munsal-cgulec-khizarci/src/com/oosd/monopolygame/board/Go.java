package com.oosd.monopolygame.board;

public class Go implements BoardPart {
    @Override
    public void accept(BoardPartVisitor boardPartVisitor) {
        boardPartVisitor.visit(this);
    }
}
