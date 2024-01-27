package com.example.isy2zeeslagfx2.model.player.actionprocessor;

import com.example.isy2zeeslagfx2.model.player.Player;
import com.example.isy2zeeslagfx2.other.MoveInfo;
import com.example.isy2zeeslagfx2.other.Ship;

import java.util.ArrayList;
import java.util.List;

public class BattleshipActionProcess implements PlayerActionProcessor {
    private List<Ship> ships;
    private boolean setupTodo;
    private int[] shipSizes;

    public BattleshipActionProcess() {
        this.ships = new ArrayList<>();
        this.setupTodo = true;
        this.shipSizes = new int[]{2}; // TODO call this in an other spot and move it to a config file
    }

    @Override
    public void makeMove(String x, String y, Object moveData)
    {
        if (moveData instanceof MoveInfo){
            MoveInfo moveInfo = (MoveInfo) moveData;
            switch (moveInfo.getMoveType()) {
                case "make shot":
                    makeShot(moveInfo.getCurPlayer(), moveInfo.getOtherPlayer(), x);
                    break;
                case "ship hit":
                    shipHit(x);
                    break;
                default:
                    System.out.println("action processor no move case found");
                    break;
            }
        }

    }

    @Override
    public boolean checks(String x, String y, Object moveData)
    {
        if (moveData instanceof MoveInfo){
            MoveInfo moveInfo = (MoveInfo) moveData;
            switch (moveInfo.getMoveType()) {
                case "check ships sunk":
                    return checkAllShipsSunk();
                default:
                    System.out.println("No valid check found");
                    return false;
            }
        }
        return false;
    }

    private void setupFase(Player player, int shipid, String start, String end) {
        System.out.println(player.getName());
        while (setupTodo) {
            printShips();
            Ship ship = selectShip(shipid);
            String[] validCoordinates;

            do {
                validCoordinates = getValidCoordinates(ship, player);
            } while (validCoordinates == null);

            String x = validCoordinates[0];
            String y = validCoordinates[1];

            if (player.getBoard().isValidMove(x, y, new MoveInfo("setup", ship))) {
                player.getBoard().makeMove(x, y, new MoveInfo("place", ship));
            } else {
                System.out.println("Move not valid, try again.");
            }

            if (checkSetupDone()) {
                this.setupTodo = false;
            }
//            player.getBoard().makeMove(null, null, new MoveInfo("print hits"));
            player.getBoard().printBoard();
            System.out.println("------------------------------------");
        }
    }

    private String[] getValidCoordinates(Ship ship, Player player) {
        while (true) {
            String x = player.getInputHandler().getInput("Enter the first coordinate (e.g., A1):");
            String y = player.getInputHandler().getInput("Enter the second coordinate (e.g., A3):");

            if (!player.getBoard().isValidMove(x, y, new MoveInfo("setup", ship))) {
                System.out.println("Invalid coordinates or move. Please enter different coordinates.");
                continue;
            }

            return new String[]{x, y};
        }
    }

    private void printShips()
    {
        for (Ship ship : ships){
            System.out.println(ship.getId() + " - " + ship.getSize());
        }
    }

    private boolean checkSetupDone()
    {
        return ships.size() == shipSizes.length;
    }

    private void createdListOfShips()
    {
        // TODO get this from a config file
        int shipId = 0;
        for (int shipSize : shipSizes) {
            ships.add(new Ship(shipId, shipSize));
            shipId++;
        }
    }

    private Ship selectShip(String shipid)
    {
        boolean shipSelect = true;
        Ship selectedShip = null;

        while (shipSelect) {
            int shipId = Integer.parseInt(shipid);
//            Integer.parseInt(inputHandler.getInput("Ship ID"));
            for (Ship ship : ships) {
                if (ship.getId() == shipId) {
                    selectedShip = ship;
                    shipSelect = false;
                }
            }
        }
        return selectedShip;
    }

    @Override
    public void initialize(Player player)
    {
        if (ships.isEmpty())
            createdListOfShips();
    }

    private boolean checkAllShipsSunk()
    {
        if (ships.isEmpty())
            return false;

        for (Ship ship : ships) {
            if (ship.getHealth() != 0)
                    return false;
        }

        return true;
    }

    private void makeShot(Player player, Player otherPlayer, String coordinate)
    {
        System.out.println("Make a shot!");
        otherPlayer.getBoard().makeMove(null, null, new MoveInfo("print hits"));
        String input = coordinate;
//        String input = selectCoordinateForShot("Give coordinate to shoot: ", player, otherPlayer);
        otherPlayer.getBoard().makeMove(input, null, new MoveInfo("shot", otherPlayer));

        otherPlayer.getActionProcessor().makeMove(input, null, new MoveInfo("ship hit"));
    }

    private String selectCoordinateForShot(Player player, Player otherPlayer, String coordinate)
    {
        while (true) {
//            String input = inputHandler.getInput(message);
            String input = coordinate;
            if (player.getBoard().checkIfCoordinateExists(input)) {
                if (!otherPlayer.getBoard().isValidMove(input, null, new MoveInfo("validate shot")))
                    return input;
            }

            System.out.println("Coordinate does not exist!");
        }
    }

    private void shipHit(String coordinate)
    {
        for (Ship ship : ships) {
            if (ship.getCoordinates().contains(coordinate)) {
                ship.takeHit();
            }
        }
    }
}

