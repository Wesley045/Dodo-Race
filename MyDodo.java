import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;

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
            move();
        }

        }
        if (coordX < getX())
        {
        setDirection(WEST);
        while (coordX < getX() && !fenceAhead())
        {
            move();
        }
        }
        if (coordY > getY())
        {
        setDirection(SOUTH);
        while (coordY > getY() && !fenceAhead())
        {
            move();
        }
        }
        if (coordY < getY())
        {
        setDirection(NORTH);
        while (coordY < getY() && !fenceAhead())
        {
            move();
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
        if(fenceAhead()){
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

    public void layTrailOfEggs(int n)
    {
    while (n != 0)
    {
        if (!onEgg())
        {
        layEgg();
        n--;
        }
        if (n != 0)
        {
        move();
        }
    }
    }
    
    public int countEggsInWorld(){
        int howManyEggs = 0;
        int coordY = 1;
        boolean endCount = false;
        
        goToLocation(0, 0);
        setDirection(EAST);
        
        while(!endCount){
        int eggsInWorld = countEggsInRow();
        howManyEggs += eggsInWorld;
        if(getY() != 9){
            goToLocation(0,coordY++);
            setDirection(EAST);
        }else{
            endCount = true;
        }
    }  
    return howManyEggs;
    }
    
    public int selectRowWithMostEggs(){
        int howManyEggs = 0;
        int coordY = 1;
        int mostEggs = 0;
        int rowNumber = 0;
        
        boolean endCount = false;
        
        goToLocation(0, 0);
        setDirection(EAST);
        
        while(!endCount){
            howManyEggs = countEggsInRow();
            
            if(howManyEggs >= mostEggs){
                mostEggs = howManyEggs;
                rowNumber = getY();
            }
            
            if(getY() != 9){
                goToLocation(0,coordY++);
                setDirection(EAST);
            }else{
                endCount = true;
            }
        }
    
        return rowNumber;
    }
}
