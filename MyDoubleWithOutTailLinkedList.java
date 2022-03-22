package MyDoubleLinkedList;

import java.io.Serializable;
import java.util.Random;

public class MyDoubleWithOutTailLinkedList implements Serializable {

    private DNode top;

    public MyDoubleWithOutTailLinkedList() {
        top = null;
    }

    // This method has been provided and you are not permitted to modify
    public int size() {
        if (top == null)
            return 0;

        int total = 0;
        DNode temp = top;
        while (temp != null) {
            total++;
            temp = temp.getNext();
        }

        int totalBack = 0;
        temp = top;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }

        while (temp != null) {
            totalBack++;
            temp = temp.getPrev();
        }

        if (total != totalBack) {
            System.out.println("next links " + total + " do not match prev links " + totalBack);
            throw new RuntimeException();
        }

        return total;

    }

    // This method has been provided and you are not permitted to modify
    public void clear() {
        Random rand = new Random(13);
        while (size() > 0) {
            int number = rand.nextInt(size());
            remove(number);
        }
    }

    public void add(Rental s) {
        DNode temp = top;

        // no list
        if (top == null) {
            top = new DNode(s, null, null);
            return;
        }

        // s is a Game, and s goes on top
        if (s instanceof Game && top.getData().getDueBack().after(s.dueBack)) {
            top = new DNode(s, top, null);
            top.getNext().setPrev(top);
            return;
        }

    }

    public Rental remove(int index) {

        int count = 0;

        if(index >= size()){
            throw new IndexOutOfBoundsException();
        }

        if (top == null)
            return null;

        DNode temp = top;
        Rental eRemoved = null; // dont know about this one

        if(index == 0){
            eRemoved = temp.getData();
            temp = temp.getNext(); //points to next node
            temp.setPrev(null); // then points to null instead of initial top
        }

        temp = top;
        DNode prevNode = null;
        DNode nextNode = null;
        //Finds node at index and removes it
        while(temp != null){ //FIXME: May Need to add temp.null instead of temp
            count++;
            eRemoved = temp.getData();
            temp = temp.getNext();
            if(count == index && (index != size() - 1)){
                prevNode = temp.getPrev();
                nextNode = temp.getNext();

                //removing links to temp
                prevNode.setNext(nextNode);
                nextNode.setPrev(prevNode);
                return eRemoved; // returning removed rental
            }

            else if(index == (size() - 1)){
                //If node is last in list
                prevNode = temp.getPrev();
                prevNode.setNext(null);

                return eRemoved; //returning removed rental
            }
        }

        //FIXME, I think this is circular
//        DNode temp2 = temp.getNext();
//        temp.setNext(temp.getPrev());
//        temp.setPrev(temp2);

    // more code here

            return null;

    }

    public Rental get(int index) {

        if (top == null)
            return null;

        return top.getData();  // this line will need to be changed
    }

    public void display() {
        DNode temp = top;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    public String toString() {
        return "LL {" +
                "top=" + top +
                ", size=" + size() +
                '}';
    }

}