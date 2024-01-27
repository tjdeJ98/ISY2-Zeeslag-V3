package com.example.isy2zeeslagfx2.model.Board;

public interface Board {
    void makeMove(String x, String y, Object moveData);
    Object getCell(int x, int y);
    void printBoard();
    boolean isValidMove(String x, String y, Object moveData);
    boolean checkIfCoordinateExists(String x);
}
