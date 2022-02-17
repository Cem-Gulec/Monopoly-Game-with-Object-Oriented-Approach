package com.oosd.monopolygame.board;

/**
 *  Basic visitor interface. Polymorphism! Overloading!
 */
public interface BoardPartVisitor {
    void visit(Chance chanceCommunity);

    void visit(FreeParkingLot freeParkingLot);

    void visit(Go go);

    void visit(GoToJail goToJail);

    void visit(Jail jail);

    void visit(Property property);

    void visit(CommunityChest communityChest);

    void visit(Tax tax);

    void visit(Railroad railroad);

    void visit(Utility Utility);

}
