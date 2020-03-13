///hantao guo
public class MyStackList<Item> {
   private Node first = null;

   private class Node {
       Item item;
       Node next;

   }

   public void push(Item item) {
       Node newNode = new Node();
       newNode.item = item;
       if (this.first == null) {
           first = newNode;
       } else {
           Node tmp = first;
           while (tmp.next != null) {
               tmp = tmp.next;
           }
           tmp.next = newNode;
       }
   }

   public void pop(Item item) {
       Node temp = first, prev = null;

       if (temp != null && temp.item == item) {
           first = temp.next;
           return;
       }
       while (temp != null && temp.item != item) {
           prev = temp;
           temp = temp.next;
       }

       if (temp == null) {
           return;
       }
       prev.next = temp.next;
   }
}
