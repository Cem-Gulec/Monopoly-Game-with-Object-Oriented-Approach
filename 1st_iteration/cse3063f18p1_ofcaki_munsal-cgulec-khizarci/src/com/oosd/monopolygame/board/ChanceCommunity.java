package com.oosd.monopolygame.board;

public class ChanceCommunity implements BoardPart {

    @Override
    public void accept(BoardPartVisitor boardPartVisitor) {
        boardPartVisitor.visit(this);
    }
}
