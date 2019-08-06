package com.oosd.monopolygame.board;

public class FreeParkingLot implements BoardPart{
    @Override
    public void accept(BoardPartVisitor boardPartVisitor) {
        boardPartVisitor.visit(this);
    }
}
