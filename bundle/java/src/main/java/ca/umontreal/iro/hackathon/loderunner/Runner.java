package ca.umontreal.iro.hackathon.loderunner;

import java.util.ArrayList;

/**
 *
 */
public class Runner extends BasicRunner {

    // TODO : Remplacer ceci par votre clé secrète
    public static final String ROOM = "#defineIfWhile";
    
    /* Utilisez cette variable pour choisir le niveau de départ
     *
     * Notez: le niveau de départ sera 1 pour tout
     * le monde pendant la compétition :v)
     */
    public static final int START_LEVEL = 1;
    
    //My variables
    public int height;
    public int length;
    
    public int playerPosX;
    public int playerPosY;
    
    public ArrayList<Integer> coinX = new ArrayList();
    public ArrayList<Integer> coinY = new ArrayList();
    public int numCoins;
    public int coinsCollected;
    public final ArrayList<Integer> coinLevelX = new ArrayList();
    public final ArrayList<Integer> coinLevelY = new ArrayList();
    //public ArrayList<Integer> indexPref = new ArrayList();
    public int indexLevel;
    public int indexPrefl;
    public boolean isCoinOnLevel;
    
    
    public int xFurthest;
    public int yFurthest;
    public int indexFurthest;
    
    public int xCloser;
    public int yCloser;
    
    public  ArrayList<Integer> posLadderX = new ArrayList();//h
    public  ArrayList<Integer> posLadderY = new ArrayList();//h
    public ArrayList<Integer> ladderLevelX = new ArrayList();//h
    public ArrayList<Integer> ladderLevelY = new ArrayList();//h
    public boolean isLadderOnLevel;
    
    
    public  ArrayList<Integer> posBrickX = new ArrayList();
    public  ArrayList<Integer> posBrickY = new ArrayList();
    
//    public int posSpaceX;//escpace
//    public int posSpaceY;//escpace
//    public int posBrickX;//#
//    public int posBrickY;//#
//    public int posBlock;//@
    public int posRope;//-
    public int posExitX;//S
    public int posExitY;//S
    public int posRunner;
    
    public  ArrayList<Integer> posSpaceX = new ArrayList();
    public  ArrayList<Integer> posSpaceY = new ArrayList();
    public int xCloserSpace;
    public int yCloserSpace;
    
    
    public char[][] myGrid;

    public Runner() {
        super(ROOM, START_LEVEL);
    }

    @Override
    public void start(String[] grid) {
                       
        System.out.println("Nouveau niveau ! Grille initiale reçue :");

        height = grid.length;
        length = grid[0].length();
        myGrid = new char[height][length];
        
        for (int i=0; i<height; i++) {
            String ligne = grid[i];
            System.out.println(ligne);
            
            for (int j = 0; j <length; j++) {
                
                myGrid[i][j] = grid[i].charAt(j);
                if(grid[i].charAt(j)=='$'){
                    coinX.add(j);
                    coinY.add(i);                    
                }
                
                if(grid[i].charAt(j) == 'H'){
                    posLadderX.add(j);
                    posLadderY.add(i);
                }
                
                
                else if(grid[i].charAt(j)=='S'){
                    posExitX = j;
                    posExitY = i;
                }
                else if(grid[i].charAt(j) == ' '){
                    posSpaceX.add(j);
                    posSpaceY.add(i);
                }
                else if (grid[i].charAt(j) == '#'){
                    posBrickX.add(j);
                    posBrickY.add(i);
                }
            }
        }
        
        System.out.println("coinX: "+coinX);
        System.out.println("conY: " +coinY);

        
        //coinOnLevel(playerPosY);
        //ladderOnLevel(playerPosY);
        
    }
    
    public boolean isSpace (int y, int x){
        if(myGrid[y+1][x] == ' '){
            return true;
        }
        return false;
    }
    
    public int distanceDoor(int xDoor, int yDoor, int xCoin, int yCoin) {
        int dis = xCoin - xDoor + yDoor - yCoin;
        return Math.abs(dis);
    } 
    
    public int distanceCoin(int xr, int yr, int xc, int yc){
        int dis = xc - xr + yc - yr;
        return Math.abs(dis);
    }
    
    public int distanceLadder(int xr, int yr, int xc, int yc){
        int dis = xc - xr + yc - yr;
        return Math.abs(dis);
    }
    
    public void closerLadderToPrefCoin(){
        //System.out.println("closerLadderTOPrefCoin");
        int min = 1000;
        
        for (int i = 0; i < ladderLevelX.size(); i++) {
            if (min > distanceLadder(playerPosX, playerPosY, ladderLevelX.get(i), ladderLevelY.get(i))) {
                min = distanceLadder(playerPosX, playerPosY, ladderLevelX.get(i), ladderLevelY.get(i));
                //System.out.println("ladderLevelX.get(i)" + ladderLevelX.get(i));
                xCloser = ladderLevelX.get(i);
                yCloser = ladderLevelY.get(i);
            }
        }
    }
    
    public void closerSpace(){
        int min = 1000;
        
        for (int i = 0; i < posSpaceX.size(); i++) {
            if(posSpaceY.get(i) - 1 ==  playerPosY){
                if (min > distanceLadder(playerPosX, playerPosY, posSpaceX.get(i), posSpaceY.get(i))) {
                    min = distanceLadder(playerPosX, playerPosY, posSpaceX.get(i), posSpaceY.get(i));
                    //System.out.println("ladderLevelX.get(i)" + ladderLevelX.get(i));
                    xCloserSpace = posSpaceX.get(i);
                    yCloserSpace = posSpaceY.get(i);
                }
            }
            
        }
    }

    public void coordinatesOfFurthestCoin(ArrayList<Integer> x, ArrayList<Integer> y){
        int max = 0;//this initial value of max distanceDoor
        //System.out.println("x.size " + x.size());
        for (int i = 0; i < x.size(); i++) {//finding the furthest coin
            
           
            if (max < distanceDoor(posExitX, posExitY, x.get(i), y.get(i))) {//fining the 
                max = distanceDoor(posExitX, posExitY, x.get(i), y.get(i));
               xFurthest = x.get(i);//the x coordinate of the furthest coin
               yFurthest = y.get(i);
               indexFurthest = i;
               
            }
            
             
        }
        
        //System.out.println("x furthest: " + xFurthest);
        //System.out.println("y furthest: " + yFurthest);
        
        //return x;
    }
    
    public void furthestCoinOnLevel(int y){
        int max = 0;//this initial value of max distanceDoor
       
        
        //int playery = playerPosY;
        coinLevelY.clear();
        coinLevelX.clear();
        for (int i = 0; i < coinY.size(); i++) {
            //System.out.println(" for loop coinOnLevel");
            
            if (coinY.get(i) == y) {
               
                int e = coinY.get(i);
                //System.out.println("e: "+e);
                coinLevelY.add(e);
                //System.out.println("i: "+ i);
                coinLevelX.add(coinX.get(i));
                 for (int j = 0; j < coinLevelX.size(); j++) {//finding the furthest coin
            
           
                    if (max < distanceDoor(posExitX, posExitY, coinLevelX.get(j), coinLevelY.get(j))) {//fining the 
                        max = distanceDoor(posExitX, posExitY, coinLevelX.get(j), coinLevelY.get(j));
                       xFurthest = coinLevelX.get(j);//the x coordinate of the furthest coin
                       yFurthest = coinLevelY.get(j);
                       indexFurthest = i;//futhest in general
                       indexLevel = j;//furthest in the level 
                    }


                }
                isCoinOnLevel = true;
               
            }
            
            else{
                isCoinOnLevel = false;
            }
        }
    }
    
    public void coinOnLevel(){
        int y = playerPosY;
        coinLevelY.clear();
        coinLevelX.clear();
        //System.out.println("coinY.size: " + coinY.size());
        //System.out.println("y: " + y);
        for (int i = 0; i < coinY.size(); i++) {
            //System.out.println(" for loop coinOnLevel");
            
            if (coinY.get(i) == y) {
               
                int e = coinY.get(i);
                //System.out.println("e: "+e);
                coinLevelY.add(e);
                //System.out.println("i: "+ i);
                coinLevelX.add(coinX.get(i));
                
                //System.out.println("coinLevelX: "+coinLevelX);
               //System.out.println("coinLevelY: " + coinLevelY);
                isCoinOnLevel = true;
               
            }
            
            else{
                isCoinOnLevel = false;
            }
        }
        
    }
    
    public boolean doorOnLevel(int y){
        if( posExitY == y){
            return true;
        }
        return false;
    }
    
    public void ladderOnLevel(int y){
        ladderLevelY.clear();
        ladderLevelX.clear();
        for (int i = 0; i < posLadderY.size(); i++) {
            if (posLadderY.get(i) == y ) {
                //indexPref.add(i);
                
                ladderLevelY.add(posLadderY.get(i));
                ladderLevelX.add(posLadderX.get(i));
                isLadderOnLevel = true;
                indexPrefl = i;
            }
            else{
                isLadderOnLevel =false;
            }
        }
        System.out.println("ladderLevelX: " + ladderLevelX);
    }
    
    
    
    @Override
    public Move next(int x, int y) {
        System.out.println("Position du runner : (" + x + ", " + y + ")");
        //Move m;
        int direction = 0;
        playerPosX=x;
        playerPosY=y;
        
        ladderOnLevel(y);
        //coordinatesOfFurthestCoin(coinLevelX, coinLevelY);
        numCoins = coinX.size();
        if(numCoins!=0){
            //look for coins
            System.out.println("");
            System.out.println("look for coins");
            furthestCoinOnLevel(y);
            System.out.println("there are coins left ");
            System.out.println("isCoinOnLevel " + isCoinOnLevel);
            if(isCoinOnLevel ){
                if(myGrid[y][x+1] == '#' || myGrid[y][x-1] == '#'){
                    System.out.println(" there a block infront of you idiot ");
                    //look for ladder 
                closerLadderToPrefCoin();
                System.out.println("isLadderOnLevel: " + isLadderOnLevel);
                //if(isLadderOnLevel){
                    
                    //reach for it
                    System.out.println("xCloser: " + xCloser);
                    if(x > xCloser){
                        System.out.println("if x > xCloser");
                        //System.out.println("");
                        direction = 2;
                        
                    }
//                    
                    if(x< xCloser){
                        System.out.println("if x < xCloser");
                        direction = 4;
                        
                    }
                    if(myGrid[y][x] == 'H' ){
//                            while(myGrid[y+1][x] == 'H'){
                            System.out.println("found ladder");
                                direction = 1;
                        //}
                    }
                    if(myGrid[y-2][x] != 'H'){
                        furthestCoinOnLevel( y);
                        if(x > xFurthest){
                    direction = 2;
                }
                else if(x< xFurthest){
                    direction = 4;
                }
                    }
                }
//                System.out.println("same level");
//                System.out.println("isCoinOnLeve: " + isCoinOnLevel);
                else if(x > xFurthest){
                    direction = 2;
                }
                else if(x< xFurthest){
                    direction = 4;
                }
                 
                if(x == xFurthest && y == yFurthest){
                    
                    System.out.println("I'm at coin");
                    System.out.println(coinX);
                    System.out.println(coinY);
                    System.out.println(coinLevelX);
                    System.out.println(coinLevelY);
//                    numCoins--;
//                    coinX.set(indexFurthest, 0);
                    System.out.println("indexFurthest " + indexFurthest);
                    System.out.println("indexLeve " + indexLevel);
                    if((indexFurthest < coinX.size()) && (indexLevel < coinLevelX.size())){
                        System.out.println("the index is correct");
                        coinX.remove(indexFurthest);
                        coinY.remove(indexFurthest);
                        coinLevelX.remove(indexLevel);
                        coinLevelY.remove(indexLevel); 
                        
                    }
                    else{
                        System.out.println("index is being corrected");
                        indexFurthest = coinX.size() - 1;
                        indexLevel = coinLevelX.size() -1;
                        coinX.remove(indexFurthest);
                        coinY.remove(indexFurthest);
                        coinLevelX.remove(indexLevel);
                        coinLevelY.remove(indexLevel); 
                    }
                                       
                    System.out.println(coinLevelX);
                    System.out.println(coinLevelY);
                    System.out.println("old indexFurdest " + indexFurthest);
                    //coinOnLevel();
                    furthestCoinOnLevel(y);
                    
                    System.out.println(coinX);
                    System.out.println(coinY);
                    System.out.println(coinLevelX);
                    System.out.println(coinLevelY);
                    
                    System.out.println(coinLevelX+ " , " + coinLevelY);
                    System.out.println("new indexFurthest " + indexFurthest);
                    
                    if(x > xFurthest){
                        direction = 2;
                    }
                    if(x< xFurthest){
                        direction = 4;
                    }
          
                }
                numCoins--;
               
            }
            
            
            //
            
            if(isCoinOnLevel == false){
                System.out.println("EMPTY");
//                coinLevelX.clear();
//                coinLevelY.clear();
                
                System.out.println("go to the ladder");
                
//               ladderOnLevel(y);
                closerLadderToPrefCoin();
                System.out.println("isLadderOnLevel: " + isLadderOnLevel);
                //if(isLadderOnLevel){
                    
                    //reach for it
                    System.out.println("xCloser: " + xCloser);
                    if(x > xCloser){
                        System.out.println("if x > xCloser");
                        //System.out.println("");
                        direction = 2;
                        
                    }
//                    
                    else if(x< xCloser){
                        System.out.println("if x < xCloser");
                        direction = 4;
                        
                    }
                    if(myGrid[y][x] == 'H' ){
//                            while(myGrid[y+1][x] == 'H'){
                            System.out.println("found ladder");
                                direction = 1;
                        //}
                    }
//                }

            }



        }
        else if(numCoins == 0){
                
            
                //go to door
                System.out.println("go to door");
                if(doorOnLevel(y)){
                    System.out.println("doorOnLevel true");
                    if(x <= posExitX){
                        direction = 4;
                        //if(myGrid[y][x] == 'S'){
                        if(x == posExitX -1){
                            System.out.println("if to clear ");
                            xFurthest= 0;
                            yFurthest = 0;
                            coinX.clear();
                            coinY.clear();
                            coinLevelX.clear();
                            coinLevelY.clear();
                            //indexPref.clear();
                            posLadderX.clear();
                            posLadderY.clear();
                            ladderLevelX.clear();
                            ladderLevelY.clear();
                        }

                    }
                    if(x >= posExitX){
                        direction = 2;
                        //if(myGrid[y][x] == 'S'){
                        if(x == posExitX +1){
                            System.out.println("if to clear ");
                            xFurthest= 0;
                            yFurthest = 0;
                            coinX.clear();
                            coinY.clear();
                            coinLevelX.clear();
                            coinLevelY.clear();
                            //indexPref.clear();
                            posLadderX.clear();
                            posLadderY.clear();
                            ladderLevelX.clear();
                            ladderLevelY.clear();

                        }

                    }
                }
                else if (y > posExitY){
                    //look for ladder 
                    System.out.println(" look for ladder ");
                    closerLadderToPrefCoin();
                    if(x > xCloser){
                        System.out.println("if x > xCloser");
                        //System.out.println("");
                        direction = 2;
                        
                    }
//                    
                    else if(x< xCloser){
                        System.out.println("if x < xCloser");
                        direction = 4;
                        
                    }
                    if(myGrid[y][x] == 'H' ){
//                            while(myGrid[y+1][x] == 'H'){
                            System.out.println("found ladder");
                                direction = 1;
                        //}
                    }

                    
                }
                else if (y < posExitY){
                    System.out.println("look for space");
                    closerSpace();
                    System.out.println("xCloser Space " + xCloserSpace);
    
                    //go to empty
                    if(isSpace(y, x)){
                        direction = 3;
                    }
                    else if(x > xCloserSpace){
                        direction = 2;
                        
                    }
                    else if(x < xCloserSpace){
                        direction = 4;
                    }
                }
                
                
        }
        
       

        //direction = 1;
        Direction dir = Direction.fromInt(direction);
        return new Move(Event.MOVE, dir);
       
        
    

    }
    
    
}
