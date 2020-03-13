///hantao guo
import java.util.*;
public class MyStackArray<Item>
{
   private Item[] s;
   private int N=0;

   public MyStackArray()
   {
	   s = (Item []) new Object[100]; N = 0;
   }

   public boolean isEmpty()
   {
       return N==0;
   }

   public void push(Item item) {
	   s[N ++] = item;
	   }

   public Item pop() {
   N--;
   return s[N+1]; 
   }
}