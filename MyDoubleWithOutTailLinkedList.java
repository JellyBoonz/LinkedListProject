/****************************************************
 Named: Mohamed Abdirahman, Jaiden Ortiz , Jamie Malone
 Class: CIS 163
 Prof:  Beach
 Assignment: Project 03
 change the Rental System program, so the program uses
 a Linked list and other new functionality.
 You cannot add any additional instance variables to the
 classes provided without the instructor’s permission,
 specifically, the Node.java and MyDoubleWithOutTailLinkedList.java
 ******************************************************/

import java.io.Serializable;
import java.util.Random;
public class MyDoubleWithOutTailLinkedList implements Serializable {

    private DNode top;


    public MyDoubleWithOutTailLinkedList() {
        top = null;
    }

    public int size() {
        if (top == null)
            return 0;
        int total = 0;
        DNode temp = top;
        while (temp != null) {

            System.out.println("Forward Data: " + temp.getData());

            total++;
            temp = temp.getNext();
        }

        int totalBack = 0;
        temp = top;
        while (temp.getNext() != null) {

            temp = temp.getNext();
        }

        while (temp != null) {
            System.out.println("Backward Data: " + temp.getData());
            totalBack++;
            temp = temp.getPrev();
        }
        if (total != totalBack) {
            System.out.println("next links " + total + " do not match prev links " + totalBack);
            throw new RuntimeException();
        }
        return total;
    }

    public void clear() {
        Random rand = new Random(13);
        while (size() > 0) {
            int number = rand.nextInt(size());
            remove(number);
        }
    }
    /**
     *Sort by Games first (ordered by dueDate)
     * and by Consoles second (ordered by dueDate).
     * If two dates are the same then sort by
     * renter’s name.
     * @return void
     */
    public void add(Rental s) {
        DNode temp = top;
        DNode before = null;
        DNode after = null;


        //checking for equals cases
        if(temp != null && s.getNameOfRenter().equals(temp.getData().getNameOfRenter()) && s.getDueBack().equals(temp.getData().getDueBack())){
            return;
        }
        if (top == null) {
            //System.out.println(top);
            top = new DNode(s, null, null);
            return;
        } else if (s instanceof Game && top.getData().getDueBack().after(s.dueBack)) { //rental must get put first in list
            top = new DNode(s, top, null);
            top.getNext().setPrev(top);

            return;
        } else if (s instanceof Game) { //when sorting a game


            while (temp.getNext() != null /*&& temp.getData() instanceof Game*/) {
                if (s.getDueBack().equals(temp.getData().getDueBack())) {
                    if (s.getNameOfRenter().compareTo(temp.getData().getNameOfRenter()) < 0) {
                        before = temp;
                        after = temp.getNext();
                        DNode n = new DNode(s, after, before);

                        if (before != null)
                            before.setNext(n);

                        if (after != null)
                            after.setPrev(n);

                        return;
                    } else {
                        before = temp.getPrev();
                        after = temp;

                        DNode n = new DNode(s, after, before);
                        if (before != null)
                            before.setNext(n);

                        if (after != null)
                            after.setPrev(n);

                        return;
                    }
                }
                temp = temp.getNext();
            }
            temp = top;
            //while we are inserting a console through games
            while (temp != null && temp.getData() instanceof Game && s.getDueBack().after(temp.getData().getDueBack())) {
                //checking for equals cases
                if(s.getNameOfRenter().equals(temp.getData().getNameOfRenter()) && s.getDueBack().equals(temp.getData().getDueBack())){
                    return;
                }


                before = temp;
                temp = temp.getNext();
            }

        } else if (s instanceof Console) {
            while (temp != null && temp.getData() instanceof Game) { //change to while, will add console between games

                if(s.getNameOfRenter().equals(temp.getData().getNameOfRenter()) && s.getDueBack().equals(temp.getData().getDueBack())){
                    return;
                }

                System.out.println("ADDING CONSOLE " + s);
                before = temp;
                temp = temp.getNext();
            }
            while (temp != null && temp.getData() instanceof Console && s.getDueBack().after(temp.getData().getDueBack())) {
                //checking for equals cases
                if(s.getNameOfRenter().equals(temp.getData().getNameOfRenter()) && s.getDueBack().equals(temp.getData().getDueBack())){
                    return;
                }

                before = temp;
                temp = temp.getNext();
            }

        }

        System.out.println("Top: " + top);
        System.out.println("Before: " + before);

        if(temp != top)
            after = temp;
        else
            before = temp;

        DNode n = new DNode(s, after, before);
        if(before != null)
            before.setNext(n);

        if(after != null)
            after.setPrev(n);

        return;
    }
    /**
     *Removes an node from the Linkedlist
     * @return the node removed from Rental
     */
    public Rental remove(int index) {
        Rental eRemoved;
        if(index == 0){
            eRemoved = top.getData();
            top = top.getNext();

            if(top != null) {
                top.setPrev(null);
                return eRemoved;
            }
            return null;
        }
        else{
            DNode temp = top;
            for (int i = 0; i < index; i++)
                temp = temp.getNext();

            eRemoved = temp.getData();
            DNode before = temp.getPrev();
            DNode after = temp.getNext();

            before.setNext(after);

            if(after != null)
                after.setPrev(before);


        }

        return eRemoved;
        //
    }
    /**
     *Gets the given index from, rentals.
     * @return
     */
    public Rental get(int index) {

        if(index >= size()){
            throw new IndexOutOfBoundsException();
        }
        DNode temp = top;
        if (top == null)
            return null;

        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }

        return temp.getData();
    }

    public void display() {
        DNode temp = top;
        while (temp != null) {

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
