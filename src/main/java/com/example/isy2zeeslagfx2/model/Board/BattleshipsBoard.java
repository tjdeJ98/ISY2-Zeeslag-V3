package com.example.isy2zeeslagfx2.model.Board;


import com.example.isy2zeeslagfx2.model.Board.Cell.Cell;
import com.example.isy2zeeslagfx2.model.player.Player;
import com.example.isy2zeeslagfx2.other.MoveInfo;
import com.example.isy2zeeslagfx2.other.Ship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class BattleshipsBoard implements Board {
    private Cell[][] grid;
    private final int size;
    private final HashSet<String> listOfAllCoordinatesOnBoard;

    public BattleshipsBoard(int size) {
        this.size = size;
        this.grid = new Cell[size][size];
        this.listOfAllCoordinatesOnBoard = new HashSet<>();
        initializeBoard();
    }

    private void initializeBoard() {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Cell(letters[x] + y);
                listOfAllCoordinatesOnBoard.add(letters[x] + y);
            }
        }
    }

    @Override
    public void makeMove(String x, String y, Object moveData) {
        // Handle moves specific to Battleships
        if (moveData instanceof MoveInfo) {
            MoveInfo moveInfo = (MoveInfo) moveData;
            switch (moveInfo.getMoveType()) {
                case "place":
                    List<String> coordinates = getAllCoordinatesShipWillCover(x, y);
                    placeShip(coordinates, moveInfo.getShip());
                    break;
                case "print hits":
                    printOnlyHitsBoard();
                    break;
                case "shot":
                    processShot(x, moveInfo.getOtherPlayer());
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    @Override
    public void printBoard() {
        for (int row=0; row<size; row++) {
            for (int col=0; col<size; col++) {
                if (grid[row][col].getShipid() != -1) {
                    System.out.printf("[" + grid[row][col].getShipid() + "]");
                } else {
                    System.out.printf("[" + grid[row][col].getCoordinate() + "]");
                }
            }
            System.out.println();
        }
    }

    @Override
    public boolean isValidMove(String x, String y, Object moveData)
    {
        if (moveData instanceof MoveInfo) {
            MoveInfo moveInfo = (MoveInfo) moveData;
            switch (moveInfo.getMoveType()) {
                case "setup":
                    return setupValidation(x, y, moveInfo.getShip());
                case "coordinate exits":
                    return checkIfCoordinateExists(x);
                case "validate shot":
                    return validateShot(x);
                default:
                    return false;
            }
        }
        // Implement logic to check if a move is valid in Battleships
        return false; // Placeholder implementation
    }

    private void processShot(String coordinate, Player otherPlayer)
    {
        int shipid = select(coordinate);
        if (shipid!=-1) {
            otherPlayer.getActionProcessor().makeMove(String.valueOf(shipid), null, new MoveInfo("ship hit", otherPlayer));
            System.out.println("You hit a ship!");
        }
    }

    private boolean validateShot(String coordinate)
    {
        return checkIfCellAlreadyShot(coordinate);
    }

    private void placeShip(List<String> coordinates, Ship ship)
    {
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (coordinates.contains(cell.getCoordinate())) {
                    cell.setShipId(ship.getId());
                }
            }
        }
    }

    public boolean shipPlacementPossible(String startCoordinate, String endCoordinate, Ship ship)
    {
        List<String> coordinates = getAllCoordinatesShipWillCover(startCoordinate, endCoordinate);
        if (coordinates == null)
            return false;

        boolean exists = checkIfCoordinateExists(startCoordinate) && checkIfCoordinateExists(endCoordinate);
        if (!(checkIfSpotsNotTaken(coordinates) && exists)) {
            return false;
        }
        return true;
    }

    private boolean checkIfSpotsNotTaken(List<String> coordinates)
    {
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (coordinates.contains(cell.getCoordinate()) && cell.getShipid() != -1)
                    return false;
            }
        }
        return true;
    }

    public boolean checkIfSpotNotTaken(String coordinate)
    {
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (Objects.equals(coordinate, cell.getCoordinate()) && cell.getShipid() != -1)
                    return false;
            }
        }
        return true;
    }

    private boolean setupValidation(String start, String end, Ship ship)
    {
        boolean foo = !isDuplicateStartAndEnd(start, end);
        boolean bar = isValidShipPlacementLength(start, end, ship.getSize());
        boolean tak = shipPlacementPossible(start, end, ship);
        return foo && bar && tak;
    }

    public boolean isValidShipPlacementLength(String start, String end, int shipLength) {
        char startRow = start.charAt(0);
        char endRow = end.charAt(0);
        int startCol = Character.getNumericValue(start.charAt(1));
        int endCol = Character.getNumericValue(end.charAt(1));

        if (startRow == endRow) {
            return Math.abs(Math.max(startCol, endCol) - Math.min(startCol, endCol)) == shipLength - 1;
        } else if (startCol == endCol) {
            return Math.abs(Math.max(startRow, endRow) - Math.min(startRow, endRow)) == shipLength - 1;
        } else {
            System.out.println("Ship must be placed horizontally or vertically.");
            return false;
        }
    }

    public boolean isDuplicateStartAndEnd(String start, String end)
    {
        if (start.equals(end)) {
            System.out.println("Start and end coordinates cannot be the same.");
            return true;
        }
        return false;
    }

    private void printOnlyHitsBoard()
    {
        StringBuilder builder = new StringBuilder();
        for (int row=0; row<size; row++) {
            for (int col=0; col<size; col++) {
                builder.append(getPrintSymbol(grid[row][col]));
            }
            builder.append("\n");
        }
        System.out.print(builder.toString());
    }

    private String getPrintSymbol(Cell cell)
    {
        if (cell.isHit()) {
            return cell.getShipid() != -1 ? "[X]" : "[O]";
        } else {
            return "[ ]";
        }
    }

    public boolean checkIfCellAlreadyShot(String coordinate)
    {
        return getBattleshipCellByCoordinate(coordinate).isHit();
    }

    public boolean checkIfCoordinateExists(String coordinate)
    {
        return listOfAllCoordinatesOnBoard.contains(coordinate);
    }

    public boolean cellContainsShip(String coordinate)
    {
        return getBattleshipCellByCoordinate(coordinate).getShipid() != -1;
    }

    public int select(String coordinate)
    {
        Cell cell = getBattleshipCellByCoordinate(coordinate);
        cell.setHit();

        return cell.getShipid();
    }


    private Cell getBattleshipCellByCoordinate(String coordinate)
    {
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.getCoordinate().equals(coordinate))
                    return cell;
            }
        }
        return null;
    }

    private List<String> getAllCoordinatesShipWillCover(String startCoordinate, String endCoordinate)
    {
        List<String> coordinates = new ArrayList<>();

        char startRow = startCoordinate.charAt(0);
        char endRow = endCoordinate.charAt(0);
        int startCol = Character.getNumericValue(startCoordinate.charAt(1));
        int endCol = Character.getNumericValue(endCoordinate.charAt(1));

        if (startRow == endRow) {
            for (int col = Math.min(startCol, endCol); col <= Math.max(startCol, endCol); col++) {
                coordinates.add("" + startRow + col);
            }
        } else if (startCol == endCol) {
            for (char row = (char) Math.min(startRow, endRow); row <= (char) Math.max(startRow, endRow); row++) {
                coordinates.add("" + row + startCol);
            }
        } else {
            return null;
        }

        return coordinates;
    }

    // Other methods...
}