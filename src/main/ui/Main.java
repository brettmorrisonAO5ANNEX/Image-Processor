package ui;

//TODO: add abandon functionality
//TODO: figure out how to save a list of all names used
//TODO: add method to view pixelArray before processing
//TODO: create feature that allows users to choose the "myImage" part of "./data/myImage.json" destination file
//TODO: create feature that allows user to retrieve their specific image file
//TODO: create 'choose from gallery option'
//TODO: add feature where user can revert back to previously saved version if they want to (while still editing)
//TODO: create save after processing feature
//TODO: create gallery class for storing all editing images
//TODO: update save ability to also account for a processed image (if a user wants to reedit a previously
//      edited image)
//TODO: add cancel option to return back to menu
//TODO: write test for JsonReader that includes image result (after implementing save after processing feature)

//represents starting point for ImageApp to be run from
public class Main {
    public static void main(String[] args) {
        new ImageApp();
    }
}
