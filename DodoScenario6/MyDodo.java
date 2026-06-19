import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.1 -- 29-07-2017
 */
public class MyDodo extends Dodo
{

    public MyDodo() {
        super( EAST );
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, 
     * i.e. there are no obstructions or end of world in the cell in front of her.
     * 
     * <p> Initial:   Dodo is somewhere in the world
     * <p> Final:     Same as initial situation
     * 
     * @return  boolean true if Dodo can move (thus, no obstructions ahead)
     *                  false if Dodo can't move
     *                      there is an obstruction or end of world ahead
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;                 // increment the counter
        }
    }

    /**
     * Places all the Egg objects in the world in a list.
     * 
     * @return List of Egg objects in the world
     */
    public List<Egg> getListOfEggsInWorld() {
        return getWorld().getObjects(Egg.class);
    }

    public List<Integer> createListOfNumbers() {
        return new ArrayList<> (Arrays.asList( 2, 43, 7, -5, 12, 7 ));
    }

    /**
     * Method for praciticing with lists.
     */
    public void practiceWithLists( ){
        List<Integer> listOfNumbers = createListOfNumbers();
        
        //the following is incorrect and is to be fixed in challenge 6.1c
        System.out.println("First element: " + listOfNumbers.get(1) ); 
    }

    public void practiceWithListsOfSurpriseEggs(){
        List<SurpriseEgg>  listOfEgss = SurpriseEgg.generateListOfSurpriseEggs( 12, getWorld() );
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
        
        for(SurpriseEgg egg: makeListOfSurpriseEggs()){
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
}
