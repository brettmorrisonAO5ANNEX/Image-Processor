package ui;

import model.CurrentProjects;
import model.Filter;
import model.Image;
import model.exceptions.DuplicateName;
import model.exceptions.InvalidName;
import persistence.JsonReader;
import persistence.JsonReaderCurrentProjects;
import persistence.JsonWriter;
import persistence.JsonWriterCurrentProjects;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.*;

//CODE PARTIALLY MODELED AFTER: JsonSerializationDemo:
//                              https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//represents image editing app that is interactive
public class ImageApp {
    private Scanner input;
    private boolean editing;
    private Image myImage;
    private int width;
    private int height;
    private int editHistory;
    private Filter negative;
    private Filter mirror;
    private Filter pixelate;
    private String projectName;
    private String imageDestination;
    private CurrentProjects currentProjects;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonWriterCurrentProjects jsonWriterCP;
    private JsonReaderCurrentProjects jsonReaderCP;
    private static final String FILE_BEGIN = "./data/";
    private static final String FILE_END = ".json";
    private static final String currentProjectsDest = "./data/currentProjects.json";



    //EFFECTS: runs ImageApp
    public ImageApp() throws IOException {
        runImageApp();
    }

    //MODIFIES: this
    //EFFECTS: accepts user input and processes it accordingly
    private void runImageApp() throws IOException {
        init();
        createOrLeave();
        nonRedundantRunApp();
    }

    //MODIFIES: this
    //EFFECTS: initializes user input
    private void init() throws IOException {
        jsonReaderCP = new JsonReaderCurrentProjects(currentProjectsDest);
        jsonWriterCP = new JsonWriterCurrentProjects(currentProjectsDest);
        negative = new Filter("negative");
        mirror = new Filter("mirror");
        pixelate = new Filter("pixelate");
        currentProjects = jsonReaderCP.read();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println(displayLogo());
    }

    //MODIFIES: this
    //EFFECTS: displays app logo
    private String displayLogo() {
        return "\n"
                + "▀█▀ ░█▀▄▀█ ─█▀▀█ ░█▀▀█ ░█▀▀▀ ─ ▄▀ ▀█▀ ░█▄─░█ ▀▄ \n"
                + "░█─ ░█░█░█ ░█▄▄█ ░█─▄▄ ░█▀▀▀ ▄ █─ ░█─ ░█░█░█ ─█ \n"
                + "▄█▄ ░█──░█ ░█─░█ ░█▄▄█ ░█▄▄▄ █ ▀▄ ▄█▄ ░█──▀█ ▄▀";
    }

    //MODIFIES: this
    //EFFECTS: allows program to continue if user chooses to edit new image, exits otherwise
    private void createOrLeave() {
        boolean deciding = true;
        String decisionCommand;

        while (deciding) {
            displayOpeningMenu();
            decisionCommand = input.next();
            decisionCommand = decisionCommand.toLowerCase();

            if (decisionCommand.equals("n")) {
                doCreateNew();
                deciding = false;
                editing = true;
            } else if (decisionCommand.equals("l")) {
                doLoadChoice();
                editing = true;
                deciding = false;
                nonRedundantRunApp();
            } else if (decisionCommand.equals("q")) {
                doQuit();
                deciding = false;
            } else {
                System.out.println("\n invalid command given...");
            }
        }
    }

    //EFFECTS: displays opening menu to user
    private void displayOpeningMenu() {
        System.out.println("\n welcome to Image.(in)!");
        System.out.println("\t n -> create and edit a new image");
        if (!currentProjects.getCurrentProjects().isEmpty()) {
            System.out.println("\t l -> load a previous project");
        }
        System.out.println("\t q -> quit application");
    }

    //MODIFIES: this
    //EFFECTS: creates image to users size specifications and initialized editing process
    private void doCreateNew() {
        displayNamingRules(true);
        System.out.println("\n IMPORTANT: to use pixelate filter, your image width and height must be even...");
        System.out.println("\n please enter an (integer) width for your image: ");
        processWidth(input.nextInt());
        System.out.println("\n please enter an (integer) height for your image: ");
        processHeight(input.nextInt());
        createImage(width, height);
        System.out.println("\n would you like to randomize the color of your image?");
        doRandomize();
        editing = true;
    }

    //EFFECTS: displays file naming rules for user to follow
    private void displayNamingRules(Boolean needsDisplay) {
        if (needsDisplay) {
            System.out.println("\n please create a name for your project according to the following rules:");
            System.out.println("\t 1. name must begin with the literal string \"image\"");
            System.out.println("\t 2. \"image\" must be followed by a single integer and a single uppercase letter");
        }
        try {
            doCreateName();
        } catch (InvalidName e) {
            System.out.println("oops, the name you chose is invalid, please try a new one!");
            displayNamingRules(false);
        } catch (DuplicateName e) {
            System.out.println("oops, the name you chose already exists, please choose a new one!");
            displayNamingRules(false);
        }
    }

    //MODIFIES: this
    //EFFECTS: creates file name for current project that can be saved to either currentProjects or
    //         gallery depending on user actions later on
    private void doCreateName() {
        projectName = input.next();
        imageDestination = FILE_BEGIN + projectName + FILE_END;
        jsonWriter = new JsonWriter(imageDestination);
        jsonReader = new JsonReader(imageDestination);

        if (!projectName.matches("(image)\\d{1,2}[A-Z]{1}")) {
            throw new InvalidName();
        } else if (currentProjects.getCurrentProjects().contains(projectName)) {
            throw new DuplicateName();
        } else {
            currentProjects.addToCurrentProjects(projectName);
            System.out.println("your image has successfully been named: " + projectName);
            System.out.println("now choose your image's dimensions...");
        }
    }

    //MODIFIES: width
    //EFFECTS: asks for new width if previous one was < 0 otherwise assigns width
    private void processWidth(int w) {
        if (w < 1) {
            System.out.println("\n oops... please enter a width that is greater than zero!");
            processWidth(input.nextInt());
        } else {
            width = w;
        }
    }

    //MODIFIES: height
    //EFFECTS: asks for new height if previous one was < 0 otherwise assigns height
    private void processHeight(int h) {
        if (h < 1) {
            System.out.println("\n oops... please enter a height that is greater than zero!");
            processHeight(input.nextInt());
        } else {
            height = h;
        }
    }

    //REQUIRES: width and height > 0
    //EFFECTS: instantiates an image with width w and height h
    private void createImage(int w, int h) {
        myImage = new Image(w, h);
    }

    //MODIFIES: myImage and this
    //EFFECTS: randomizes RGB values within image if randomize is true, otherwise does not change myImage.pixelArray
    private void doRandomize() {
        String randomizeCommand;
        System.out.println("\n y -> yes");
        System.out.println("\n n -> no");
        randomizeCommand = input.next();

        if (randomizeCommand.equals("y")) {
            myImage.randomizeColor();
            System.out.println("your image now has a randomized color!");
        } else if (randomizeCommand.equals("n")) {
            System.out.println("your image is a default blank canvas!");
        }
    }

    //TODO: add feature that asks user to reenter name if the entered one doesn't exist in currentProjects
    //TODO: debug choose file feature (currently does not work when user types in file name and enters)
    //MODIFIES: this
    //EFFECTS: loads chosen file to current session
    private void doLoadChoice() {
        String fileName;
        doDisplayAllProjectNames();
        fileName = input.next();

        imageDestination = FILE_BEGIN + fileName + FILE_END;
        System.out.println(imageDestination);

        try {
            myImage = jsonReader.read();
            System.out.println(fileName + " has been loaded successfully!");
        } catch (IOException e) {
            System.out.println("sorry, we were unable to load " + fileName);
        }
    }

    //EFFECTS: displays all project names from this.currentProjects
    private void doDisplayAllProjectNames() {
        System.out.println("type the name of the file you would like to open");
        for (String fileName : currentProjects.getCurrentProjects()) {
            System.out.println("\t " + fileName);
        }
    }

    //MODIFIES: this
    //EFFECTS: accepts user input and processes it accordingly - extracts portion of program that does
    //         not include redundant loading in info so that when user loads previous project they can
    //         jump straight into working rather than viewing redundant info
    private void nonRedundantRunApp() {
        String command;

        while (editing) {
            editHistory = myImage.getListOfFilter().size();
            displayToolMenu();

            command = input.next();
            command = command.toLowerCase();

            processCommand(command);
        }
    }

    //EFFECTS: displays tool menu to user
    private void displayToolMenu() {
        System.out.println("\n Tool Menu:");
        System.out.println("\t a -> add filter");
        System.out.println("\t ul -> undo last edit");
        System.out.println("\t ua -> undo all edits");
        System.out.println("\t ut -> undo all filters of a certain type");
        System.out.println("\t v -> view edit history");
        System.out.println("\t sc -> save current progress");
        System.out.println("\t rp -> reset progress to last saved");
        System.out.println("\t qs -> quit and save progress");
        System.out.println("\t p -> process final image");
        System.out.println("\t ab -> abandon project");
    }

    //MODIFIES: this
    //EFFECTS: processes user commands while editing
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddFilter();
        }

        doUndo(command);

        if (command.equals("v")) {
            doViewHistory();
        } else if (command.equals("sc")) {
            doSave(false);
        } else if (command.equals("rp")) {
            doLoadPrevious();
        } else if (command.equals("qs")) {
            doSave(true);
            doSaveCurrentProjects();
        } else if (command.equals("p")) {
            doProcessAndQuit();
        } else if (command.equals("ab")) {
            doQuit();
        } else {
            System.out.println("\n invalid command given...");
        }
    }

    //MODIFIES: this
    //EFFECTS: handles all instances in which a user want to undo (of either last, type, or all)
    public void doUndo(String command) {
        if (command.equals("ul")) {
            doUndoLast();
        } else if (command.equals("ua")) {
            doUndoAll();
        } else if (command.equals("ut")) {
            doUndoType();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds selected filter to image
    private void doAddFilter() {
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

    //EFFECTS: displays available filters
    private void displayFilterOptions() {
        System.out.println("\n choose a filter to apply: ");
        System.out.println("\t nv -> negative");
        System.out.println("\t mr -> mirror");
        if (bothEven()) {
            System.out.println("\t px -> pixelate");
        } else {
            System.out.println("\n NOTE: pixelate filter not available because your chosen "
                    + whichOdd() + " odd...");
        }
    }

    //EFFECTS: returns true if both myImage width and height are even
    private boolean bothEven() {
        boolean widthIsEven = (myImage.getImageWidth() % 2 == 0);
        boolean heightIsEven = (myImage.getImageHeight() % 2 == 0);
        return widthIsEven && heightIsEven;
    }

    //EFFECTS: returns true if either myImage width or height are odd
    private String whichOdd() {
        boolean widthIsEven = (myImage.getImageWidth() % 2 == 0);
        boolean heightIsEven = (myImage.getImageHeight() % 2 == 0);
        if (widthIsEven && !heightIsEven) {
            return "(height) is";
        } else if (!widthIsEven && heightIsEven) {
            return "(width) is";
        } else {
            return "(width and height) are";
        }
    }

    //MODIFIES: this
    //EFFECTS: returns user options for which degree of pixelation they'd like
    private void doDisplayPixOptions() {
        int minDim = min(myImage.getImageWidth(), myImage.getImageHeight());
        int maxDegPix = (int) (log(minDim) / log(2));
        int[] degOptions = new int[maxDegPix + 1];
        System.out.println("\n choose one of the following numbers as your degree of pixelation:");
        System.out.println("\t NOTE: 0 cause the lowest amount of pixelation");
        for (int o = 0; o < maxDegPix + 1; o++) {
            degOptions[o] = o;
        }
        System.out.println(Arrays.toString(degOptions));
        myImage.setDegreeOfPixelation(input.nextInt());
    }

    //MODIFIES: this
    //EFFECTS: undoes last edit
    private void doUndoLast() {
        if (editHistory == 0) {
            System.out.println("\n no filters to remove...");
        } else {
            myImage.undoLast();
            System.out.println("\n the most recent filter has been removed...");
        }
    }

    //MODIFIES: this
    //EFFECTS: removes all added filters
    private void doUndoAll() {
        if (editHistory == 0) {
            System.out.println("\n no filters to remove...");
        } else {
            myImage.undoAll();
            System.out.println("\n all filters have been removed...");
        }
    }

    //MODIFIES: this
    //EFFECTS: removes all filters of type given, if there aren't any filters to remove, returns to menu
    private void doUndoType() {
        if (myImage.getUniqueFiltersUsed().size() > 0) {
            displayAvailableFilters();
            doRemoveChosen(input.next());
        } else {
            System.out.println("\n no filters to remove...");
        }
    }

    //MODIFIES: this
    //EFFECTS: presents user with all unique filters used, so they know which types they can remove
    private void displayAvailableFilters() {
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
    private void doRemoveChosen(String command) {
        if (command.equals("nv")) {
            myImage.removeAllOfType("negative");
            System.out.println("\t all applications of (negative) have been removed");
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
    private void doViewHistory() {
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
    //EFFECTS: saves current progress to .json file, stores project name in this.CurrentProjects
    //         and quits application if quit is true
    private void doSave(Boolean quit) {
        try {
            jsonWriter.open();
            jsonWriter.write(myImage);
            jsonWriter.close();

            if (quit) {
                System.out.println("\n your image was successfully saved to " + imageDestination);
                editing = false;
            } else {
                System.out.println("\n current progress saved to " + imageDestination);
            }

        } catch (FileNotFoundException e) {
            System.out.println("\n sorry, we were unable to write your image to " + imageDestination);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads a previously saved image of the users choice
    private void doLoadPrevious() {
        try {
            myImage = jsonReader.read();
            System.out.println("your previous image has been loaded from " + imageDestination);
        } catch (IOException e) {
            System.out.println("sorry, we were unable to load your work from " + imageDestination);
        }
    }

    //MODIFIES: this
    //EFFECTS: saves updated currentProjects to standard destination to be retrieved at next session
    private void doSaveCurrentProjects() {
        try {
            jsonWriterCP.open();
            jsonWriterCP.write(currentProjects);
            jsonWriterCP.close();
        } catch (FileNotFoundException e) {
            System.out.println("there was an error while saving the currentProjects to " + currentProjectsDest);
        }
    }

    //MODIFIES: this
    //EFFECTS: quits program and displays exit message
    private void doProcessAndQuit() {
        myImage.processImage();
        String result = myImage.createVisArray(0);
        System.out.println("\n thank you... your image has been processed successfully!");
        System.out.println("\n your image is represented by the following pixel array: ");
        System.out.println("\n" + result);
        editing = false;
    }

    //MODIFIES: this
    //EFFECTS: quits program
    private void doQuit() {
        System.out.println("\n sorry to see you go... come back anytime to edit a new image!");
        editing = false;

    }
}
