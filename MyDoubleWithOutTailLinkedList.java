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
        System.out.println("-------Adding-------");
        // no list
        if (top == null) { //FIXME top is null even after addition
            System.out.println(top);
            top = new DNode(s, null, null);
            //temp = top;
            //System.out.println("Prev: " + temp.getPrev());
            System.out.println("Curr: " + top);
            System.out.println("Next: " + top.getNext());
            System.out.println("one" + size());
            return;
        }
        else if(top != null && size() == 1){
            DNode n = new DNode(s, null, temp);
            temp.setNext(n);
            System.out.println("Prev: " + temp.getPrev());
            System.out.println("Curr: " + temp);
            System.out.println("Next: " + temp.getNext());
            System.out.println(n);
        }
        else if(top != null && size() > 1){
            DNode n = new DNode(s, temp.getNext(), temp);
            temp.getNext().setPrev(n);
            temp.setNext(n);
            System.out.println(n);
        }
        System.out.println(size());
        return;

        //temp = top;

        // s is a Game, and s goes on top
//        if (s instanceof Game && top.getData().getDueBack().after(s.dueBack)) {
//            top = new DNode(s, top, null);
//            top.getNext().setPrev(top);
//            System.out.println("Prev: " + temp.getPrev());
//            System.out.println("Curr: " + temp);
//            System.out.println("Next: " + temp.getNext());
//            System.out.println("two" + size());
//            return;
//        }
//        else if(top != null){
//            DNode n = new DNode(s, null, temp);
//            temp = n;
//
//        }

    }

    public Rental remove(int index) {

        System.out.println("Hello, Remove");
        System.out.println("-------Removing-------");
        int count = 0;

        //System.out.println(index);
        System.out.println(size());

        if(index >= size()){
            throw new IndexOutOfBoundsException();
        }

        if (top == null) {
            System.out.println("Hello Top Null");
            return null;
        }

        DNode temp = top;
        Rental eRemoved = null; // dont know about this one

        //in the event of one node in list
        if(size() == 1){
            eRemoved = temp.getData();
            temp.setNext(temp);
            //System.out.println(eRemoved);
            return eRemoved;
        }

        if(index == 0 && temp != null && size() > 1){
            System.out.println("Hello");
            eRemoved = temp.getData(); //first nodes data
            top = temp.getNext(); //points to next node
            //temp = top; //FIXME bug??
            top.setPrev(null); // then points to null instead of initial top
            temp = top;
            return eRemoved;
        }

        temp = top;
        DNode prevNode = null;
        DNode nextNode = null;
        eRemoved = temp.getData();

        //Finds node at index and removes it
        while(temp != null){
            count++;
            temp = temp.getNext();
            eRemoved = temp.getData();
            if(count == index && (index != size() - 1)){
                prevNode = temp.getPrev();
                nextNode = temp.getNext();

                //removing links to temp
                prevNode.setNext(nextNode);
                nextNode.setPrev(prevNode);
                temp = top;
                return eRemoved; // returning removed rental
            }
            else{
                //If node is last in list
                prevNode = temp.getPrev();
                prevNode.setNext(null);
                temp = top;
                return eRemoved; //returning removed rental
            }
        }

        //FIXME, I think this is circular
//        DNode temp2 = temp.getNext();
//        temp.setNext(temp.getPrev());
//        temp.setPrev(temp2);

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
