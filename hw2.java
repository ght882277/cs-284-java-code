///hantao guo
///I pledge my honor that I have abided by the Stevens Honor System 
import java.util.Random;

class Skiplist {

    private skipListentry head; 
    private skipListentry tail;
    private Integer levels;

    static class RandomGenerator{

        private static Random Random = new Random();

        public static boolean getRandomBoolean(float probability){
            return Random.nextFloat() < probability;
        }
    }

    class skipListentry{
        Integer value;

        skipListentry up;
        skipListentry down;
        skipListentry left;
        skipListentry right;
       
        public skipListentry(Integer value) {
            this.value = value;
        }
      
        public Integer getValue(){
            return this.value;
        }
    }

    private static void insertentryinh(skipListentry source, skipListentry target){
            target.left = source;
            target.right = source.right;
            source.right.left = target;
            source.right = target;
        }
    private static void insertentryinv(skipListentry source, skipListentry target){
            source.down = target; 
            target.up = source;
        }

    public Skiplist() {

        head = new skipListentry(null);
        tail = new skipListentry(null);

        head.right = tail;
        tail.left = head;

        levels = 0;
    }

    private skipListentry search(Integer value, skipListentry startEntry){
   
    	skipListentry current= startEntry;
        while(current.right.getValue() != null && current.right.getValue() <= value){
        	current = current.right;
        }
        if(current.down != null){
            return search(value, current.down);
        }
        return current;
    }

    private void createEmptyLevel() {

    	skipListentry newHeader = new skipListentry(null);
    	skipListentry newTail = new skipListentry(null);

        newHeader.right = newTail;
        newTail.left = newHeader;
    
        insertentryinv(newHeader,this.head);
        insertentryinv(newTail,this.tail);
        
        this.head = newHeader;
        this.tail = newTail;
        this.levels += 1; 

    }
    
    public boolean find(int target) {
    	skipListentry searchingResult = search(target, this.head);
        if(searchingResult.getValue() == null || searchingResult.getValue() != target){
            return false;
        }
        return true;   
    }
    
    public void add(int num) {
    	
    	skipListentry searchingResult = search(num, this.head);
    	skipListentry newEntry = new skipListentry(num);
    	insertentryinh(searchingResult, newEntry);        
        int curLevel = 1;
        
        while(RandomGenerator.getRandomBoolean(0.5f)){           
            if(curLevel > this.levels){
                createEmptyLevel();
            }
            while(searchingResult.up == null){          
                searchingResult = searchingResult.left;  
            }
            searchingResult = searchingResult.up; 
            skipListentry newEntryDuplicate = new skipListentry(num);           
            insertentryinh(searchingResult, newEntryDuplicate);          
            insertentryinv(newEntryDuplicate,newEntry);
            newEntry = newEntryDuplicate; 
            curLevel +=1; 
        }
    }
    
    public boolean delete(int num) {
    	skipListentry searching = search(num,this.head); 
    	skipListentry higherEntry = null;
        if(searching.getValue() == null || searching.getValue() != num){
            return false;
        }
        while(searching != null){
            higherEntry = searching.up;    
            searching.left.right = searching.right;
            searching.right.left = searching.left;         
            searching = higherEntry;
        }
        return true; 
    }
}
