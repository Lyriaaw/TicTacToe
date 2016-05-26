package com.lyriaaw.TicTacToe.GameLogic;

/**
 * (commentaires)
 *
 * @author Amalric Lombard de Buffières <amalric.debuffieres@icloud.com>
 * @version 1.0.0
 */
public class Coordinate {

    private int x, y;

    public Coordinate() {
    }

    public Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }



    public static Coordinate getCoordinatesFromStrings(String xCoordinate, String yCoordinate) {
        Coordinate generatedCoordinates = new Coordinate();

        int xSelected, ySelected;

        xSelected = getValueFromString(xCoordinate);
        ySelected = getValueFromString(yCoordinate);

        if (xSelected == -1 || ySelected == -1) return null;

        generatedCoordinates.setX(xSelected);
        generatedCoordinates.setY(ySelected);

        return generatedCoordinates;

    }


    /**
     * Turn a String to a number
     * @param inputString String must be in this format : "X:{x}"
     *                    With 'X' the Corresponding coord and {x} the value
     * @return return the value as a int
     */
    private static int getValueFromString(String inputString) {

        String value;

        try {
            value = inputString.split(":")[1];
        } catch (IndexOutOfBoundsException e) {
            // e.printStackTrace();

            System.err.println("String format is invalid");
            return -1;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e ) {
            // e.printStackTrace();

            System.err.println("Wrong number format");
            return -1;
        }

    }




    public boolean areValid(int gridSize) {
        return (this.x < gridSize && this.x >= 0 && this.y < gridSize && this.y >= 0);
    }





}
