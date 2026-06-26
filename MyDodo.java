import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    private int steps = Mauritius.MAXSTEPS;
    private int score = 0;

    public MyDodo()
    {
        super(EAST);
        myNrOfEggsHatched = 0;
    }

    public void act()
    {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P>
     * Initial: Dodo is somewhere in the world
     * <P>
     * Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move()
    {
        if (canMove())
        {
            step();
        } else
        {
            showError("I'm stuck!");
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions or end of world in
     * the cell in front of her).
     * 
     * <p>
     * Initial: Dodo is somewhere in the world
     * <p>
     * Final: Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead) false if Dodo
     *         can't move (an obstruction or end of world ahead)
     */
    public boolean canMove()
    {
        if (borderAhead() || fenceAhead())
        {
            return false;
        } else
        {
            return true;
        }
    }

    /**
     * Hatches the egg in the current cell by removing the egg from the cell. Gives
     * an error message if there is no egg
     * 
     * <p>
     * Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p>
     * Final: Dodo is in the same cell. The egg has been removed (hatched).
     */
    public void hatchEgg()
    {
        if (onEgg())
        {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else
        {
            showError("There was no egg in this cell");
        }
    }

    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched()
    {
        return myNrOfEggsHatched;
    }

    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p>
     * Initial:
     * <p>
     * Final:
     * 
     * @param int distance: the number of steps made
     */
    public void jump(int distance)
    {
        int nrStepsTaken = 0; // set counter to 0
        while (nrStepsTaken < distance)
        { // check if more steps must be taken
            move(); // take a step
            nrStepsTaken++; // increment the counter
            System.out.println("moved: " + nrStepsTaken);
        }
    }

    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p>
     * Initial: Dodo is on West side of world facing East.
     * <p>
     * Final: Dodo is on East side of world facing East. Coordinates of each cell
     * printed in the console.
     */

    public void walkToWorldEdge()
    {
        while (!borderAhead() && !fenceAhead())
        {
            move();
            if (onNest() && !onEgg())
            {
                layEgg();
            }
        }
    }

    /**
     * Test if Dodo can lay an egg. (there is not already an egg in the cell)
     * 
     * <p>
     * Initial: Dodo is somewhere in the world
     * <p>
     * Final: Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there) false if Dodo
     *         can't lay an egg (already an egg in the cell)
     */

    public boolean canLayEgg()
    {
        if (onEgg())
        {
            // E
            return false;

        } else
        {
            return true;
        }
    }

    /**
     * Dodo draait 180 graden.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo draait 180 graden om en kijkt naar de andere kant.
     */

    public void turn180()
    {
        turnRight();
        turnRight();
    }

    /**
     * Dodo klimt over het hekje heen
     * 
     * <p>
     * Initial: Dodo staat voor een hekje.
     * <p>
     * Final: Dodo staat voor een hekje en klimt er over heen en staat dan aan de
     * andere kant van het hek.
     */

    public void climbOverFence()
    {
        turnLeft();
        move();
        turnRight();
        move();
        move();
        turnRight();
        move();
        turnLeft();
    }

    /**
     * Dodo kijkt of er een graan voor hem is.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo returnt een true or false boolean.
     */

    public boolean grainAhead()
    {
        move();
        if (onGrain())
        {
            stepOneCellBackwards();
            return true;
        } else
        {
            stepOneCellBackwards();
            return false;
        }
    }

    /**
     * De dodo stopt op het ei als hij die tegen komt.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo returnt een true or false boolean.
     */

    public void goToEgg()
    {
        while (!onEgg())
        {
            move();
        }
    }

    /**
     * Dodo draait 180 graden om en loopt naar de worldborder en draait vervolgens
     * weer 180 graden om.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo staat tegen de rand van de worldborder en kijkt de andere kant
     * op.
     * 
     * @return
     */

    public void goBackToStartOfRowAndFaceBack()
    {
        turn180();
        walkToWorldEdgeClimbingOverFences();
        turn180();
    }

    /**
     * Dodo blijft lopen totdat hij bij de worldborder is als hij onderweg een hekje
     * tegen komt klimt hij erover heen en vervolgd zijn pad.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo loopt naar de worldborder.
     * 
     * @return
     */

    public void walkToWorldEdgeClimbingOverFences()
    {
        while (!borderAhead())
        {
            if (!fenceAhead())
            {
                move();
            } else
            {
                climbOverFence();
            }
            if (onNest())
            {
                layEgg();
                break;
            }
        }

    }

    /**
     * Als dodo graan tegen komt tijdens het lopen dan pakt hij hem op en geeft de
     * exacte coordinates waar hij het graantje heeft gepakt.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Alle graan pakt hij op onderweg.
     * 
     * @return
     */

    public void pickUpGrainsAndPrintCoordinates()
    {
        while (!borderAhead())
        {
            if (!onGrain())
            {
                move();
            }
            if (onGrain())
            {
                pickUpGrain();
                System.out.println("Er ligt een graan op: " + "x = " + getX() + ", y = " + getY());
            }
        }
    }

    /**
     * Dodo die gaat een stapje naar achtere toe.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo staat een vakje terug.
     * 
     * @return
     */

    public void stepOneCellBackwards()
    {
        turn180();
        move();
        turn180();
    }

    /**
     * Dodo loopt om de hekjes heen tot hij een eitje tegen komt.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo staat stil op het eitje.
     * 
     * @return
     */
    public void walkAroundFencedArea()
    {
        while (!onEgg())
        {
            turnRight();
            if (fenceAhead())
            {
                turnLeft();
                if (!fenceAhead())
                {
                    move();
                } else
                {
                    turnLeft();
                }
            } else
            {
                if (!fenceAhead())
                {
                    move();
                } else
                {
                    turnLeft();
                    move();
                }
            }
        }
    }

    /**
     * Dodo volgt het eieren spoor naar het nest.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo staat aan het einde van het spoor stil op het nest.
     * 
     * @return
     */

    public void eggTrailToNest()
    {
        while (!onNest())
        {
            if (eggAhead() || nestAhead())
            {
                move();
                if (onEgg())
                {
                    pickUpEgg();
                }
                if (onNest())
                {
                    break;
                }
            }
            if (!eggAhead() && !nestAhead())
            {
                turnRight();
            }
        }
    }

    /**
     * Dodo zoekt naar de nest in de doolhof.
     * 
     * <p>
     * Initial: Het nest moet langs een henkje staan.
     * <p>
     * Final: Doormiddel van de hekjes vind de dodo het nest.
     * 
     * @return
     */

    public void searchNestInMaze()
    {
        while (!onNest())
        {
            turnRight();
            if (fenceAhead())
            {
                turnLeft();
                if (!fenceAhead())
                {
                    move();
                } else
                {
                    turnLeft();
                }
            } else
            {
                if (!fenceAhead())
                {
                    move();
                } else
                {
                    turnLeft();
                    move();
                }
            }
        }
        if (onNest())
        {
            System.out.println("Dodo heeft het nest gevonden");
        }
    }

    /**
     * Alle eiren wisselen van waarde met elkaar.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: het blauwe is is het gouden ei, en het gouden ei is het blauwe ei.
     * 
     * @return
     */

    public void eggWissel()
    {
        BlueEgg blueEgg = new BlueEgg();
        GoldenEgg goldenEgg = new GoldenEgg();

        int tijdelijkeWaardeEi = blueEgg.getValue();
        blueEgg.setValue(goldenEgg.getValue());
        goldenEgg.setValue(tijdelijkeWaardeEi);

        System.out.println("BlueEgg Waarde: " + blueEgg.getValue() + " \nGoldenEgg Waarde: " + goldenEgg.getValue());
    }

    /**
     * Dodo kijkt naar het westen.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo draait naar het westen toe.
     * 
     * @return
     */

    public void faceEast()
    {
        int direction = getDirection();
        while (direction != 1)
        {
            turnRight();
            direction = getDirection();
        }
    }

    /**
     * Dodo daait naar richting.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo draait naar de ingevoerde richting toe.
     * 
     * @return
     */

    public void faceRichting(int locatie)
    {
        if (locatie <= 3 && locatie >= 0)
        {
            while (getDirection() != locatie)
            {
                turnRight();
            }
        } else
        {
            showError("Ongeldige Richting");
        }

    }

    /**
     * Dodo gaat naar de coordinaten toe.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo gaat naar de ingevoerde coordinaten toe als ze geldig zijn.
     * 
     * @return
     */

    public void goToLocation(int coordX, int coordY)
    {

        if (validCoordinates(coordX, coordY) == true)
        {
            if (coordX > getX())
            {
                setDirection(EAST);
                while (coordX > getX() && !fenceAhead())
                {
                    if(steps != 0){
                        move();
                        getScore(steps -=1, score);
                    }else{
                        break;
                    }
                }

            }
            if (coordX < getX())
            {
                setDirection(WEST);
                while (coordX < getX() && !fenceAhead())
                {
                    if(steps != 0){
                        move();
                        getScore(steps -=1, score);
                    }else{
                        break;
                    }
                }
            }
            if (coordY > getY())
            {
                setDirection(SOUTH);
                while (coordY > getY() && !fenceAhead())
                {
                    if(steps != 0){
                        move();
                        getScore(steps -=1, score);
                    }else{
                        break;
                    }
                }
            }
            if (coordY < getY())
            {
                setDirection(NORTH);
                while (coordY < getY() && !fenceAhead())
                {
                    if(steps != 0){
                        move();
                        getScore(steps -=1, score);
                    }else{
                        break;
                    }
                }
            }
        }
    }

    /**
     * Controlleerd of de coordinaten overeen komen met het bord.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Geeft een true of false terug.
     * 
     * @return: true of false.
     */

    public boolean validCoordinates(int x, int y)
    {
        if (x <= 11 && x >= 0)
        {
            return true;
        }

        if (y <= 11 && y >= 0)
        {
            return true;
        }
        showError("Invalid coordinates");
        return false;
    }

    /**
     * Dodo telt de eieren die hij in de rij tegen komt.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo telt de eieren in de rij en gaat vervolgens terug naar het begin.
     * 
     * @return
     */

    public int countEggsInRow()
    {
        int eggsInRow = 0;

        while (!borderAhead())
        {
            if (!onEgg())
            {
                move();
            } else if (onEgg())
            {
                eggsInRow++;
                move();
            }
            if (fenceAhead())
            {
                climbOverFence();
            }
        }

        if (onEgg())
        {
            eggsInRow++;
        }

        goBackToStartOfRowAndFaceBack();

        return eggsInRow;
    }

    /**
     * Dodo legt de ingevoerde eieren in een rij.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo legt alle ingevoerde eieren in een rij achter elkaar.
     * 
     * @return
     */

    public void layTrailOfEggs(int n)
    {
        while (n != 0)
        {
            if (!onEgg())
            {
                layEgg();
                n--;
            }
            if (n != 0 && !borderAhead())
            {
                move();
            } else
            {
                break;
            }

        }
    }

    /**
     * Dodo telt de eieren in de wereld.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo telt alle eieren die hij tegenkomt in de hele wereld.
     * 
     * @return
     */

    public int countEggsInWorld()
    {
        int howManyEggs = 0;
        int heightRoom = getWorld().getHeight() - 1;

        goToLocation(0, 0);
        setDirection(EAST);

        while (getY() < heightRoom)
        {
            howManyEggs += countEggsInRow();
            goToLocation(0, getY() + 1);
            setDirection(EAST);
        }
        howManyEggs += countEggsInRow();
        return howManyEggs;
    }

    /**
     * Dodo telt alle eieren per rij en kijkt waar de meeste liggen
     * 
     * <p>
     * Initial:
     * <p>
     * Final: dodo telt de eieren in de wereld per rij en geeft dan de rij nummer terug.
     * 
     * @return int en dan de nummer van de rij met de meeste eieren.
     */ 

    public int selectRowWithMostEggs()
    {
        int heightRoom = getWorld().getHeight();
        int mostEggs = 0;
        int rowNumber = 0;

        goToLocation(0, 0);
        setDirection(EAST);

        for (int row = 0; row < heightRoom; row++)
        {
            int howManyEggs = countEggsInRow();

            goToLocation(0, row);

            setDirection(EAST);

            if (howManyEggs >= mostEggs)
            {
                mostEggs = howManyEggs;
                rowNumber = row;
            }
        }
        return rowNumber;
    }

    /**
     * Dodo legt eieren in een bepaald patroon.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Zij legt eieren in een patroon en dat is een soort trapje tot zij niet meer verder kan.
     * 
     * @return
     */

    public void layEggPatron()
    {
        int startX = getX();
        int heightRoom = getWorld().getHeight();
        int n = 0;

        while (getY() < heightRoom)
        {
            layTrailOfEggs(n += 1);
            if (getY() + 1 < heightRoom)
            {
                setLocation(startX, getY() + 1);
            } else
            {
                break;
            }
        }
        if (!borderAhead())
        {
            setLocation(startX, getY() + 1);
        }
    }

    /**
     * Dodo legt weer een patroon met eieren maar deze keer een ander.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo legt weer een trapjes patroon maar zij verdubbeld steeds de laatste uitstekende eieren van de rij.
     * 
     * @return
     */

    public void layEggPatronDouble()
    {
        int startX = getX();
        int startY = getY();
        int heightRoom = getWorld().getHeight() - 1;
        int widthRoom = getWorld().getWidth();
        int eggToLay = 1;

        while (getY() < heightRoom)
        {
            layTrailOfEggs(eggToLay);
            if (!borderAhead())
            {
                setLocation(startX, getY() + 1);
                faceEast();
                eggToLay *= 2;
            } else
            {
                break;
            }

        }
        layTrailOfEggs(eggToLay);
    }

    /**
     * Nog een patroon die door dodo wordt gemaakt.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo maakt een piramide vorm van eieren tot zij niet meer verder kan.
     * 
     * @return
     */

    public void layEggPatronPiramide()
    {
        int startX = getX();
        int heightRoom = getWorld().getHeight();
        int n = 0;
        int face = 0;

        while (getY() < heightRoom)
        {
            n++;

            layTrailOfEggs(n);
            boolean rightHitBorder = borderAhead();

            setLocation(startX, getY());
            setDirection(WEST);
            face = n - 1;
            layTrailOfEggs(face);
            boolean leftHitBorder = borderAhead();
            setDirection(EAST);

            if (rightHitBorder || leftHitBorder)
                break;

            if (getY() + 1 < heightRoom)
            {
                setLocation(startX, getY() + 1);
            } else
            {
                break;
            }
        }
    }

    /**
     * Dodo telt alle eieren en vervolgens berekent ze de gemiddelde eieren per rij.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo telt alle eieren en deelt dat door de room hoogte.
     * 
     * @return double met het gemiddelde
     */

    public double gemiddeldEggsBerekenen()
    {
        int heightRoom = getWorld().getHeight();
        int eggs = countEggsInWorld();
        double gemiddelde = (double) eggs / heightRoom;

        return gemiddelde;
    }

    /**
     * Dodo controlleerd de rijen of fouten en legt eventueel een eitje .
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo controlleerd de rijen of fouten en repareerd het dan en legt een eitje aan de rand van de wereld erbij.
     * 
     * @return
     */

    public void pariteitsBit(){
        int heightRoom = getWorld().getHeight() -1;
        int widthRoom = getWorld().getWidth() -1;

        goToLocation(0, 0);
        setDirection(EAST);

        while(getY() < heightRoom){
            double eggsH = countEggsInRow();

            if(eggsH % 2 != 0){
                goToLocation(widthRoom, getY());
                layEgg();
            }
            goToLocation(0, getY() + 1);
            setDirection(EAST);
        }
        double eggsH = countEggsInRow();
        if(eggsH % 2 != 0){
            goToLocation(widthRoom, getY());
            layEgg();
        }
        goToLocation(0, 0);
        setDirection(SOUTH);
        while(getX() < widthRoom){
            double eggsV = countEggsInRow();
            if(eggsV % 2 != 0){
                goToLocation(getX(), heightRoom );
                layEgg();
            }
            goToLocation(getX() + 1, 0);
            setDirection(SOUTH);
        }
        double eggsV = countEggsInRow();
        if(eggsV % 2 != 0){
            goToLocation(getX(), heightRoom );
            layEgg();
        }
    }

    /**
     * Dodo zoekt naar fouten en geeft daarna de cordinaten terug.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo controlleerd alle rijen op fouten en als hij een fout heeft gevonden slaat hij dat op en geeft hij dat aan het einde terug.
     * 
     * @return
     */

    public void foutOpsporen(){
        int heightRoom = getWorld().getHeight() -1;
        int widthRoom = getWorld().getWidth() -1;
        int foutOne = -1;
        int foutTwo = -1;

        goToLocation(0, 0);
        setDirection(EAST);

        while(getY() < heightRoom){
            double eggsH = countEggsInRow();

            if(eggsH % 2 != 0){
                foutOne = getY();
            }
            goToLocation(0, getY() + 1);
            setDirection(EAST);
        }
        double eggsH = countEggsInRow();
        if(eggsH % 2 != 0){
            foutOne = getY();
        }
        goToLocation(0, 0);
        setDirection(SOUTH);
        while(getX() < widthRoom){
            double eggsV = countEggsInRow();
            if(eggsV % 2 != 0){
                foutTwo = getX();
            }
            goToLocation(getX() + 1, 0);
            setDirection(SOUTH);
        }
        double eggsV = countEggsInRow();
        if(eggsV % 2 != 0){
            foutTwo = getX();
        }

        if(foutOne == -1 && foutTwo == -1){
            System.out.println("Geen fouten gevonden.");
        }else{
            System.out.println("Fout gevonden op:");
            System.out.println("Y: " + foutOne + ", " + "X: " + foutTwo);
        }

    }

    /**
     * Dodo controlleerd de wereld op fouten en hersteld dan de fout.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo controlleerd de wereld op fouten en legt er een eitje.
     * 
     * @return
     */

    public void foutHerstelllen(){
        int heightRoom = getWorld().getHeight() -1;
        int widthRoom = getWorld().getWidth() -1;
        int foutOne = -1;
        int foutTwo = -1;

        goToLocation(0, 0);
        setDirection(EAST);

        while(getY() < heightRoom){
            double eggsH = countEggsInRow();

            if(eggsH % 2 != 0){
                foutOne = getY();
            }
            goToLocation(0, getY() + 1);
            setDirection(EAST);
        }
        double eggsH = countEggsInRow();
        if(eggsH % 2 != 0){
            foutOne = getY();
        }
        goToLocation(0, 0);
        setDirection(SOUTH);
        while(getX() < widthRoom){
            double eggsV = countEggsInRow();
            if(eggsV % 2 != 0){
                foutTwo = getX();
            }
            goToLocation(getX() + 1, 0);
            setDirection(SOUTH);
        }
        double eggsV = countEggsInRow();
        if(eggsV % 2 != 0){
            foutTwo = getX();
        }

        if(foutOne == -1 && foutTwo == -1){
            System.out.println("Geen fouten gevonden.");
        }else{
            goToLocation(foutTwo, foutOne);
            if(!onEgg()){
                layEgg();
            }
            else{
                pickUpEgg();
            }
        }

    }

    /**
     * Dodo heeft zijn hoofd gestoten maar toch controlleerd ze de wereld op fouten en hersteld dan de fout.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo controlleerd de wereld op fouten en legt er een eitje ondangs dat ze hoofdpijn heeft.
     * 
     * @return
     */

    public void foutHerstelllenMetHoofdGestoten(){
        int foutOne = -1;
        int foutTwo = -1;
        int count = 0;
        int countHeight = 0;
        int countWidth = 0;
        boolean startPoint = false;
        boolean endOfWorld = false;

        while(!startPoint){
            goBackToStartOfRowAndFaceBack();
            turnRight();
            goBackToStartOfRowAndFaceBack();
            while(!borderAhead()){
                move();
                countHeight++;
            }
            goBackToStartOfRowAndFaceBack();
            turnLeft();
            while(!borderAhead()){
                move();
                countWidth++;
            }
            goBackToStartOfRowAndFaceBack();
            startPoint = true;
        }
        while(count != countHeight){

            double eggsH = countEggsInRow();

            if(eggsH % 2 != 0){
                foutOne = count;
            }

            turnRight();
            move();
            count++;
            turnLeft();
        }
        double eggsH = countEggsInRow();

        if(eggsH % 2 != 0){
            foutOne = count;
        }
        turnLeft();
        count = 0;
        while(count != countWidth){

            double eggsW = countEggsInRow();

            if(eggsW % 2 != 0){
                foutTwo = count;
            }

            turnRight();
            move();
            count++;
            turnLeft();
        }
        double eggsW = countEggsInRow();

        if(eggsW % 2 != 0){
            foutTwo = count;
        }

        if(foutOne == -1 && foutTwo == -1){
            System.out.println("Geen fouten gevonden.");
        }else{
            while(foutOne != countHeight){
                countHeight--;
                move();
            }
            turnLeft();
            while(foutTwo!= countWidth){
                countWidth--;
                move();
            }
            if(!onEgg()){
                layEgg();
            }
            else{
                pickUpEgg();
            }
        }
    }

    /**
     * 
     * 
     * 
     * Senario 6
     * 
     * 
     * 
     * 
     */

    /**
     * Places all the Egg objects in the world in a list.
     * 
     * @return List of Egg objects in the world
     */
    public List<Egg> getListOfEggsInWorld() {
        return getWorld().getObjects(Egg.class);
    }

    /* Maakt een random lijst van 10 eieren en verdeeld dat over de wereld.
     * 
     * @return De volledige lijst
     */

    public List<SurpriseEgg> makeListOfSurpriseEggs(){
        return SurpriseEgg.generateListOfSurpriseEggs( 10, getWorld() );
    }

    /* Print de cordinaten en waarde van het ingevoerde ei.
     * 
     */

    public void printCoordinatesOfEgg(Egg egg){
        System.out.println("X: " + egg.getX() + " Y: " + egg.getY() + " Waarde: " + egg.getValue());

    } 

    /* Maakt een random lijst van 10 eieren en verdeeld dat over de wereld en 
     * print de cordinaten en waarde uit per ei.
     * 
     */

    public void makeListOfSurpriseEggsAndPrintCoordinates(){
        for(SurpriseEgg egg : makeListOfSurpriseEggs()){
            printCoordinatesOfEgg(egg);

        }
    } 

    /* Maakt een random lijst van 10 eieren en verdeeld dat over de wereld 
     * controlleerd vervolgens of de waarde van het vorige ei of die groter of kleiner 
     * is en bewaard de hoogste waarde.
     * 
     * @return de waardevolste ei
     */

    public int meestWaardevolleEgg(){
        int waardevolsteEgg = 0;

        for(Egg egg: getListOfEggsInWorld()){
            if(waardevolsteEgg <= egg.getValue()){
                waardevolsteEgg = egg.getValue();
            }
            printCoordinatesOfEgg(egg);

        }
        return waardevolsteEgg;
    } 

    /* Maakt een random lijst van 10 eieren en slaat dat op in een List en verdeeld 
     * het over de wereld en vervolgens berekent hij de gemiddelde waarde van de eieren.
     * 
     * @return De volledige lijst
     */

    public double gemiddeldeWaardeEggs(){
        List<SurpriseEgg> lijst = makeListOfSurpriseEggs();
        int totaal = 0;

        for(SurpriseEgg egg: lijst){
            totaal += egg.getValue();
        }

        double gemiddeldeEggWaarde = (double) totaal / lijst.size();

        return gemiddeldeEggWaarde;
    }

    /**
     * Dodo beweegt naar een random richting toe en kan 40 stappen zetten.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo beweegt naar een random richting en blijft dat doen tot dat hij 40 stappen heeft gezet 
     * ondertussen wordt het scoreboard bijgewerkt.
     * 
     * @return
     */

    public void moveRandomly(){
        int myNrOfStepsTaken = 0;

        faceRichting(randomDirection());
        for(int i = steps; myNrOfStepsTaken < Mauritius.MAXSTEPS;){
            if(borderAhead() || fenceAhead()){
                faceRichting(randomDirection());
            }else{
                move();
                i -= 1;
                getScore(i,0);
                faceRichting(randomDirection());
                myNrOfStepsTaken++;
            }
        }
    }

    /**
     * Werkt het scoreboard bij.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Deze methode werkt het scoreboard bij.
     * 
     * @return
     */

    public void getScore(int score1, int score2){
        ((Mauritius)getWorld()).updateScore(score1, score2);
    }

    /**
     * Dodo kijkt naar de eieren op de map en kiest de dichtbijzijnste ei.
     * 
     * <p>
     * Initial:
     * <p>
     * Final: Dodo kiest het dichtbijzijnste ei die er bij hem in de buurt ligt
     * 
     * @return
     */

    public void dichtbijzijndeEgg(){
        int total = 1000;
        int dx = -1;
        int dy = -1;

        for(Egg egg : getListOfEggsInWorld()){

            int positionX = egg.getX() - getX();
            int positionY = egg.getY() - getY();

            int result = positionX * positionX + positionY * positionY;

            if(result < total ){
                total = result;

                dx = egg.getX();
                dy = egg.getY();
            }

        }
        goToLocation(dx, dy);
        pickUpEgg();
    }

    public void hetAlgoritme(){
        int total = 1000;
        int dx = -1;
        int dy = -1;
        int value = 0;
        int waardevolsteEgg = 0;
        int stepsNeeded = 1000;
        int eggsRemain = getListOfEggsInWorld().size();

        Egg eggWaardevol = null;

        while(steps > 0 && eggsRemain > 0){
            total = 1000;
            stepsNeeded = 1000;
            waardevolsteEgg = 0;
            for(Egg egg : getListOfEggsInWorld()){

                int positionX = egg.getX() - getX();
                int positionY = egg.getY() - getY();

                int result = positionX * positionX + positionY * positionY;

                if(waardevolsteEgg < egg.getValue()){
                    waardevolsteEgg = egg.getValue();
                    eggWaardevol = egg;

                    int posX = eggWaardevol.getX() - getX();
                    int posY = eggWaardevol.getY() - getY(); 
                    int stepsToGet = Math.abs(posX) + Math.abs(posY);
                    stepsNeeded = stepsToGet;
                }

                if(result < total ){
                    total = result;
                    dx = egg.getX();
                    dy = egg.getY();
                    value = egg.getValue();
                }

            }

            if(eggWaardevol.getValue() > stepsNeeded){
                goToLocation(eggWaardevol.getX(), eggWaardevol.getY());
                getScore(steps, score += eggWaardevol.getValue());
            }else{
                goToLocation(dx, dy);
                getScore(steps, score += value);
            }

            if(onEgg()){
                pickUpEgg();
                eggsRemain -= 1;
            }
        }
    }

}
