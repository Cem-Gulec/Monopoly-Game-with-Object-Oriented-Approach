package com.oosd.monopolygame.board;

import com.oosd.monopolygame.Player;

/***
 *  Simplest part of the board. Basic visitable interface.
 *
 */
public interface BoardPart {
    void accept(BoardPartVisitor boardPartVisitor);
    int getInd();
}
