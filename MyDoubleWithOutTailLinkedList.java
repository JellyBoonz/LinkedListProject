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
            //System.out.println("TOP : " + top);
            //System.out.println("Forward Data: " + temp.getData());
           // System.out.println("Next Data: " + temp.getNext().getData());
            total++;
            temp = temp.getNext();
        }

        int totalBack = 0;
        temp = top;
        while (temp.getNext() != null) {
           // System.out.println("TOP : " + top);
            //System.out.println("2 Forward Data: " + temp.getData());
            temp = temp.getNext();
        }

        while (temp != null) {
            //System.out.println("Backward Data: " + temp.getData());
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
    //FIXME Check setPrev's
    public void add(Rental s) {
        DNode temp = top;
        DNode before = null;
        DNode after = null;
        System.out.println("-------Adding-------");
//        System.out.println("TOP: " + top);
        // no list
        //checking for equals cases
        if(temp != null && s.getNameOfRenter().equals(temp.getData().getNameOfRenter()) && s.getDueBack().equals(temp.getData().getDueBack())){
            return;
        }
        if (top == null) {
            //System.out.println(top);
            top = new DNode(s, null, null);
            //temp = top;
            //System.out.println("Prev: " + temp.getPrev());
//            System.out.println("Curr: " + top);
//            System.out.println("Next: " + top.getNext());
            return;
        } else if (s instanceof Game && top.getData().getDueBack().after(s.dueBack)) { //rental must get put first in list
            top = new DNode(s, top, null);
            top.getNext().setPrev(top);
//            top.setPrev(new DNode(s, top, null));
//            top = top.getPrev();
//            System.out.println("Prev: " + temp.getPrev());
//            System.out.println("Curr: " + temp);
//            System.out.println("Next: " + temp.getNext());
//            System.out.println("SIZE: " + size());
            return;
        } else if (s instanceof Game) { //when sorting a game
//            System.out.println("Hello");
//            temp = top;
            //while we are inserting a console through games
            while (temp != null && temp.getData() instanceof Game && s.getDueBack().after(temp.getData().getDueBack())) {//FIXME Node being set to before when should be after
                //checking for equals cases
                if(s.getNameOfRenter().equals(temp.getData().getNameOfRenter()) && s.getDueBack().equals(temp.getData().getDueBack())){
                    return;
                }

                System.out.println("HELLOOOOOOO");
                before = temp;
                temp = temp.getNext();
            }
//                } else if ((temp.getData() instanceof Game && temp.getNext().getData() instanceof Console) || (temp.getData() instanceof Game &&
//                        temp.getNext().getData() == null)) {
//                    before = temp;
//                    after = temp.getNext();
//                    break;
//                }
           // System.out.println("SIZE: " + size());
        } else if (s instanceof Console) {
            while (temp != null && temp.getData() instanceof Game) { //change to while, will add console between games
                //checking for equals cases
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

                //if rental is later than others
                before = temp;
                temp = temp.getNext();
            }
//            System.out.println("SIZE: " + size());
        }
//        else if(top != null && size() > 1){
//            DNode n = new DNode(s, temp.getNext(), temp);
//            temp.getNext().setPrev(n);
//            temp.setNext(n);
//            System.out.println(n);
//        }
//        System.out.println("SIZE: " + size());
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

    //FIXME check my setPrev's
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
        else {
            DNode temp = top;
            for (int i = 0; i < index; i++)
                temp = temp.getNext();

            eRemoved = temp.getData();
            DNode before = temp.getPrev();
            DNode after = temp.getNext();

            before.setNext(after);
            
            if(after != null)
                after.setPrev(before);

           // temp = top;
        }


//        System.out.println("-------Removing-------");
//        System.out.println("Hello, Remove");
//        int count = 0;
//
//        //System.out.println(index);
//        System.out.println("Before Remove: " + size());
//
//        if(index >= size()){
//            throw new IndexOutOfBoundsException();
//        }
//
//        if (top == null) {
//            System.out.println("Hello Top Null");
//            return null;
//        }
//
//        DNode temp = top;
//        Rental eRemoved = null; // dont know about this one
//
//        //in the event of one node in list
//        if(size() == 1){
//            eRemoved = temp.getData();
//            top = top.getNext();
//            temp = top;
//            //System.out.println(eRemoved);
//            return eRemoved;
//        }
//
//        if(index == 0 && temp != null && size() > 1){
//            System.out.println("Hello");
//            eRemoved = temp.getData(); //first nodes data
//            top = temp.getNext(); //points to next node
//            temp = top; //FIXME bug??
//            top.setPrev(null); // then points to null instead of initial top
//            System.out.println("After Remove first node: " + size());
//            return eRemoved;
//        }
//
//        temp = top; //FIXME do I need this?
//        DNode prevNode = null;
//        DNode nextNode = null;
//
//        //Finds node at index and removes it
//        while(count < size()){
//            count++;
//            eRemoved = temp.getData();
//            temp = temp.getNext();
//            System.out.println("Count: " + count);
//            if(count == index && (index != size() - 1)){
//                prevNode = temp.getPrev();
//                nextNode = temp.getNext();
//                System.out.println("Removal in here");
//
//                //removing links to temp
//                temp.getPrev().setNext(nextNode);
//                temp.getNext().setPrev(prevNode);
//                temp = top;
//                System.out.println("After Remove index node: " + size());
//                return eRemoved; // returning removed rental
//            }
//            else if (index == size() - 1 && count == index){
//                //If node is last in list
//                prevNode = temp.getPrev();
//                prevNode.setNext(null);
//                temp = top;
//                System.out.println("After Remove end node: " + size());
//                return eRemoved; //returning removed rental
//            }
//            return null;
//        }
//
//        //FIXME, I think this is circular
////        DNode temp2 = temp.getNext();
////        temp.setNext(temp.getPrev());
////        temp.setPrev(temp2);
//
            return eRemoved;
//
    }

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

        return temp.getData();  // this line will need to be changed
    }

    public void display() {
        DNode temp = top;
        while (temp != null) {
            //System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

//    public String toString() {
//        return "LL {" +
//                "top=" + top +
//                ", size=" + size() +
//                '}';
//    }

}
