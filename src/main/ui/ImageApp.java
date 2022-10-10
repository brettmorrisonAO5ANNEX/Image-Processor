package ui;

import model.Filter;
import model.Image;

import java.util.Scanner;

//TODO: check over method documentation to make sure it makes sense

public class ImageApp {
    private Image myImage;
    private Filter negative;
    private Scanner input;
    private String command = null;
    private boolean editing;
    private int width;
    private int height;
    private int editHistory;


    //EFFECTS: runs ImageApp
    public ImageApp() {
        runImageApp();
    }

    //MODIFIES: this
    //EFFECTS: accepts user input and processes it accordingly
    public void runImageApp() {

        init();

        createOrLeave();

        while (editing) {
            editHistory = myImage.getlistOfFilter().size();
            displayToolMenu();

            command = input.next();
            command = command.toLowerCase();

            if (command == "p") {
                editing = false;
            } else {
                processCommand(command);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes user input
    public void init() {
        negative = new Filter("negative");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //MODIFIES: this
    //EFFECTS: allows program to continue if user chooses to edit new image, exits otherwise
    public void createOrLeave() {
        displayOpeningMenu();

        command = input.next();
        command = command.toLowerCase();

        if (command == "y") {
            editing = true;

            System.out.println("\n please enter an (integer) width for your image: ");
            processWidth(input.nextInt());

            System.out.println("\n please enter an (integer) height for your image: ");
            processHeight(input.nextInt());

            createImage(width, height);

        } else {
            editing = false;
            System.out.println("\n sorry to see you go... come back anytime to edit a new image!");
        }
    }

    //MODIFIES: width
    //EFFECTS: asks for new width if previous one was < 0 otherwise assigns width
    public void processWidth(int w) {
        while (width < 0) {
            System.out.println("\n oops... please enter a width that is greater than zero!");
            processWidth(input.nextInt());
        }
        width = w;
    }

    //MODIFIES: height
    //EFFECTS: asks for new height if previous one was < 0 otherwise assigns height
    public void processHeight(int h) {
        while (height < 0) {
            System.out.println("\n oops... please enter a height that is greater than zero!");
            processHeight(input.nextInt());
        }
        height = h;
    }

    //REQUIRES: width and height > 0
    //EFFECTS: instantiates an image with width w and height h
    public void createImage(int w, int h) {
        myImage = new Image(w, h);
    }

    //EFFECTS: displays opening menu to user
    public void displayOpeningMenu() {
        System.out.println("\n would you like to edit a new image?");
        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");
    }

    //EFFECTS: displays tool menu to user
    public void displayToolMenu() {
        System.out.println("\n continue editing or processes final image:");
        System.out.println("\t a -> add filter");
        System.out.println("\t ul -> undo last edit");
        System.out.println("\t ua -> undo all edits");
        System.out.println("\t ut -> undo all filters of a certain type");
        System.out.println("\t v -> view edit history");
        System.out.println("\t p -> process final image");
    }

    //MODIFIES: this
    //EFFECTS: processes user commands while editing
    public void processCommand(String command) {
        if (command == "a") {
            doAddFilter();
        } else if (command == "ul") {
            doUndoLast();
        } else if (command == "ua") {
            doUndoAll();
        } else if (command == "ut") {
            doUndoType();
        } else if (command == "v") {
            doViewHistory();
        } else if (command == "p") {
            doQuit();
        } else {
            System.out.println("\n invalid command given...");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds filter to image
    public void doAddFilter() {
        displayFilterOptions();
        if (input.next() == "nv") {
            myImage.addFilter(negative);
        } else {
            System.out.println("\n invalid filter chosen");
        }
    }

    //EFFECTS: displays available filters
    public void displayFilterOptions() {
        System.out.println("\n choose a filter to apply: ");
        System.out.println("\t nv -> negative");
    }

    //MODIFIES: this
    //EFFECTS: undoes last edit
    public void doUndoLast() {
        if (editHistory == 0) {
            System.out.println("\n no filters to remove...");
        } else {
            myImage.undoLast();
            System.out.println("\n the most recent filter has been removed...");
        }
    }

    //MODIFIES: this
    //EFFECTS: removes all added filters
    public void doUndoAll() {
        if (editHistory == 0) {
            System.out.println("\n no filters to remove...");
        } else {
            myImage.undoAll();
            System.out.println("\n all filters have been removed...");
        }
    }

    //TODO: create method that keeps track of which unique filters a user has applied so when they ask to remove
    //      all of a type they know their options
    //MODIFIES: this
    //EFFECTS: removes all filters of type given
    public void doUndoType() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: provides user with edit history
    public void doViewHistory() {
        int historyEndInd = myImage.viewEditHistory().length() - 1;
        String history = myImage.viewEditHistory();
        String refinedHistory = history.substring(1, historyEndInd);
        System.out.println("\n your edit history is: ");
        System.out.printf("\t %s", myImage.viewEditHistory());
    }

    //MODIFIES: this
    //EFFECTS:
    public void doQuit() {
        //stub
    }
}
