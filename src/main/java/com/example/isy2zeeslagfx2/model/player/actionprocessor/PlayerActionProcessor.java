package com.example.isy2zeeslagfx2.model.player.actionprocessor;

import com.example.isy2zeeslagfx2.model.player.Player;

public interface PlayerActionProcessor {
    void initialize(Player player);
    void makeMove(String x, String y, Object moveInfo);
    boolean checks(String x, String y, Object moveInfo);

}
