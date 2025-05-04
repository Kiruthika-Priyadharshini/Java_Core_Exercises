import java.util.ArrayList;
import java.util.List;

class Memento {
    private final String state;
    public Memento(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }
}

class Originator {
    private String state;
    public void setState(String state) {
        this.state = state;
        System.out.println("Current State: " + state);
    }

    public String getState() {
        return state;
    }
    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void restoreStateFromMemento(Memento memento) {
        state = memento.getState();
        System.out.println("State Restored to: " + state);
    }
}

// Caretaker class: manages saved states
class Caretaker {
    private final List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}

// Demo class: runs the program
public class MementoPattern{
    public static void main(String[] args) {
        Originator originator = new Originator();

        Caretaker caretaker = new Caretaker();
        originator.setState("State 1");
        originator.setState("State 2");
        caretaker.add(originator.saveStateToMemento()); // Save2

        originator.setState("State 3");
        caretaker.add(originator.saveStateToMemento()); //Save 3

        originator.setState("State #4");

        System.out.println("\nRestoring to previous states...");
        originator.restoreStateFromMemento(caretaker.get(0)); //2
        originator.restoreStateFromMemento(caretaker.get(1)); //3
        System.out.println("\nRestoring to previous states..."+originator.getState());

    }
}
