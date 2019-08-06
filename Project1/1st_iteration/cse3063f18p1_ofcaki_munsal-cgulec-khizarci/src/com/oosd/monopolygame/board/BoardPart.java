package com.oosd.monopolygame.board;

public interface BoardPart {
    public void accept(BoardPartVisitor boardPartVisitor);
}
