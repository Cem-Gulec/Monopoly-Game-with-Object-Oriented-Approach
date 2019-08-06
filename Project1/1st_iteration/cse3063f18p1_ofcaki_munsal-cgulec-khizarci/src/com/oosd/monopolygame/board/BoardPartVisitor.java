package com.oosd.monopolygame.board;

public interface BoardPartVisitor {
    public void visit(ChanceCommunity chanceCommunity);
    public void visit(FreeParkingLot freeParkingLot);
    public void visit(Go go);
    public void visit(GoToJail goToJail);
    public void visit(Jail jail);
    public void visit(Property property);

    public void visit(Tax tax);
}
