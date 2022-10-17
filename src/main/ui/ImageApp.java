package ui;

import model.Filter;
import model.Image;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.log;
import static java.lang.Math.min;

//TODO: check over method documentation to make sure it makes sense

public class ImageApp {
    private Image myImage;
    private Filter negative;
    private Filter mirror;
    private Filter pixelate;
    private Scanner input;
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
        String command;

        init();

        createOrLeave();

        while (editing) {
            editHistory = myImage.getListOfFilter().size();
            displayToolMenu();

            command = input.next();
            command = command.toLowerCase();

            processCommand(command);
        }
    }


    //MODIFIES: this
    //EFFECTS: initializes user input
    public void init() {
        negative = new Filter("negative");
        mirror = new Filter("mirror");
        pixelate = new Filter("pixelate");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println(displayLogo());
    }

    //MODIFIES: this
    //EFFECTS: displays app logo
    public String displayLogo() {
        return "hello";
    }

    //MODIFIES: this
    //EFFECTS: allows program to continue if user chooses to edit new image, exits otherwise
    public void createOrLeave() {
        boolean deciding = true;
        String decisionCommand;

        while (deciding) {
            displayOpeningMenu();
            decisionCommand = input.next();
            decisionCommand = decisionCommand.toLowerCase();

            if (decisionCommand.equals("y")) {
                doCreate();
                deciding = false;
            } else if (decisionCommand.equals("n")) {
                doQuit();
                deciding = false;
            } else {
                System.out.println("\n invalid command given...");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: creates image to users size specifications and initialized editing process
    public void doCreate() {
        System.out.println("\n IMPORTANT: to use pixelate filter, your image width and height must be even...");
        System.out.println("\n please enter an (integer) width for your image: ");
        processWidth(input.nextInt());
        System.out.println("\n please enter an (integer) height for your image: ");
        processHeight(input.nextInt());
        createImage(width, height);
        editing = true;
    }

    //MODIFIES: this
    //EFFECTS: quits program
    public void doQuit() {
        System.out.println("\n sorry to see you go... come back anytime to edit a new image!");
        editing = false;

    }

    //MODIFIES: width
    //EFFECTS: asks for new width if previous one was < 0 otherwise assigns width
    public void processWidth(int w) {
        if (w < 1) {
            System.out.println("\n oops... please enter a width that is greater than zero!");
            processWidth(input.nextInt());
        } else {
            width = w;
        }
    }

    //MODIFIES: height
    //EFFECTS: asks for new height if previous one was < 0 otherwise assigns height
    public void processHeight(int h) {
        if (h < 1) {
            System.out.println("\n oops... please enter a height that is greater than zero!");
            processHeight(input.nextInt());
        } else {
            height = h;
        }
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
        if (command.equals("a")) {
            doAddFilter();
        } else if (command.equals("ul")) {
            doUndoLast();
        } else if (command.equals("ua")) {
            doUndoAll();
        } else if (command.equals("ut")) {
            doUndoType();
        } else if (command.equals("v")) {
            doViewHistory();
        } else if (command.equals("p")) {
            doProcessAndQuit();
        } else {
            System.out.println("\n invalid command given...");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds selected filter to image
    public void doAddFilter() {
        displayFilterOptions();
        String filterChoice = input.next();
        if (filterChoice.equals("nv")) {
            myImage.addFilter(negative);
        } else if (filterChoice.equals("mr")) {
            myImage.addFilter(mirror);
        } else if (filterChoice.equals("px")) {
            doDisplayPixOptions();
            myImage.addFilter(pixelate);
        } else {
            System.out.println("\n invalid filter chosen");
            doAddFilter();
        }
    }

    //MODIFIES: this
    //EFFECTS: returns user options for which degree of pixelation they'd like
    public void doDisplayPixOptions() {
        int minDim = min(myImage.getImageWidth(), myImage.getImageHeight());
        int maxDegPix = (int) (log(minDim) / log(2));
        int[] degOptions = new int[maxDegPix + 1];
        System.out.println("\n choose one of the following numbers as your degree of pixelation:");
        for (int o = 0; o < maxDegPix + 1; o++) {
            degOptions[o] = o;
        }
        System.out.println(Arrays.toString(degOptions));
        System.out.println("\n note: 0 will return the least pixelated");
        System.out.println("\n       the amount of pixelation increases relative to your choice!");
        myImage.degreeOfPixelation = input.nextInt();
    }

    //EFFECTS: displays available filters
    public void displayFilterOptions() {
        System.out.println("\n choose a filter to apply: ");
        System.out.println("\t nv -> negative");
        System.out.println("\t mr -> mirror");
        System.out.println("\t px -> pixelate");
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

    //MODIFIES: this
    //EFFECTS: removes all filters of type given, if there aren't any filters to remove, returns to menu
    public void doUndoType() {
        if (myImage.getUniqueFiltersUsed().size() > 0) {
            displayAvailableFilters();
            doRemoveChosen(input.next());
        } else {
            System.out.println("\n no filters to remove...");
        }
    }

    //MODIFIES: this
    //EFFECTS: presents user with all unique filters used, so they know which types they can remove
    public void displayAvailableFilters() {
        System.out.println("\n you have used each of the following filters at least once: ");
        for (String filterName : myImage.getUniqueFiltersUsed()) {
            if (filterName.equals("negative")) {
                System.out.println("\t nv -> negative");
            } else if (filterName.equals("mirror")) {
                System.out.println("\t mr -> mirror");
            } else if (filterName.equals("pixelate")) {
                System.out.println("\t px -> pixelate");
            }
        }
        System.out.println("\n choose a type to remove from your image: ");
    }

    //MODIFIES: myImage
    //EFFECTS: removes all instances of a filterType from myImage that the user specified
    public void doRemoveChosen(String command) {
        if (command.equals("nv")) {
            myImage.removeAllOfType("negative");
            System.out.println("\t all applications of (negative) have been removed");
//            myImage.getUniqueFiltersUsed().
        } else if (command.equals("mr")) {
            myImage.removeAllOfType("mirror");
            System.out.println("\t all applications of (mirror) have been removed");
        } else if (command.equals("px")) {
            myImage.removeAllOfType("pixelate");
            System.out.println("\t all applications of (pixelate) have been removed");
        } else {
            System.out.println("\n invalid option...");
            doUndoType();
        }
    }

    //MODIFIES: this
    //EFFECTS: provides user with edit history
    public void doViewHistory() {
        int historyEndInd = myImage.viewEditHistory().length() - 1;
        String history = myImage.viewEditHistory();
        String refinedHistory = history.substring(1, historyEndInd);

        if (history.equals("no filters applied")) {
            System.out.printf("\t %s", refinedHistory);
        } else {
            System.out.println("\n your edit history is: ");
            System.out.printf("\t %s", refinedHistory);
        }
    }

    //MODIFIES: this
    //EFFECTS: quits program and displays exit message
    public void doProcessAndQuit() {
        myImage.processImage();
        String result = myImage.createVisArray(0);
        System.out.println("\n thank you... your image has been processed successfully!");
        System.out.println("\n your image is represented by the following pixel array: ");
        System.out.println(result);
        editing = false;
    }
}
