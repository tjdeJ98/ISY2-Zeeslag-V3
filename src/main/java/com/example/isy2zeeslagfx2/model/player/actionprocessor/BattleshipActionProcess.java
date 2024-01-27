package com.example.isy2zeeslagfx2.model.player.actionprocessor;

import com.example.isy2zeeslagfx2.model.player.Player;
import com.example.isy2zeeslagfx2.other.ConsoleHandler;
import com.example.isy2zeeslagfx2.other.MoveInfo;
import com.example.isy2zeeslagfx2.other.Ship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BattleshipActionProcess implements PlayerActionProcessor {
    private List<Ship> ships;
    private boolean setupTodo;
    private int[] shipSizes;

    public BattleshipActionProcess() {
        this.ships = new ArrayList<>();
        this.setupTodo = true;
        this.shipSizes = new int[]{2, 4, 5, 6}; // TODO call this in an other spot and move it to a config file
    }

    private void setupFase(Player player) {
        System.out.println(player.getName());
        while (setupTodo) {
            printShips();
            Ship ship = selectShip();
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
        }
        player.getBoard().printBoard();
        player.getBoard().makeMove(null, null, new MoveInfo("print hits"));
    }

    private String[] getValidCoordinates(Ship ship, Player player) {
        while (true) {
            String x = ConsoleHandler.getConsoleInput("Enter the first coordinate (e.g., A1):");
            String y = ConsoleHandler.getConsoleInput("Enter the second coordinate (e.g., A3):");

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

    private Ship selectShip()
    {
        boolean shipSelect = true;
        Ship selectedShip = null;

        while (shipSelect) {
            int shipId = Integer.parseInt(ConsoleHandler.getConsoleInput("Ship ID"));
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

        setupFase(player);
    }

    @Override
    public void makeMove(String x, String y, Object moveData)
    {
        if (moveData instanceof MoveInfo){
            MoveInfo moveInfo = (MoveInfo) moveData;
            switch (moveInfo.getMoveType()) {
                case "make shot":
                    makeShot(moveInfo.getCurPlayer(), moveInfo.getOtherPlayer());
                    break;
                case "ship hit":
                    shipHit(Integer.parseInt(x));
                    break;
                default:
                    System.out.println("action processor no move case found");
                    break;
            }
        }

    }

    private void makeShot(Player player, Player otherPlayer)
    {
        player.getBoard().makeMove(null, null, new MoveInfo("print hits"));;
        String input = selectCoordinateForShot("Give coordinate to shoot: ", player, otherPlayer);
        otherPlayer.getBoard().makeMove(input, null, new MoveInfo("shot", otherPlayer));
    }

    private String selectCoordinateForShot(String message, Player player, Player otherPlayer)
    {
        while (true) {
            String input = ConsoleHandler.getConsoleInput(message);
            if (player.getBoard().checkIfCoordinateExists(input)) {
                if (!otherPlayer.getBoard().isValidMove(input, null, new MoveInfo("validate shot")))
                    return input;
            }

            System.out.println("Coordinate does not exist!");
        }
    }

    private void shipHit(int shipid)
    {
        for (Ship ship : ships) {
            if (ship.getId() == shipid) {
                ship.takeHit();
            }
        }
    }
}
