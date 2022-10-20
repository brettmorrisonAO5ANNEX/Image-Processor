package ui;

//TODO: debug doLoadChoice() so that when command is called in terminal, all options to open are
//      displayed and the user can input the one they want
//TODO: organize terminal interface

import java.io.IOException;

//represents starting point for ImageApp to be run from
public class Main {
    public static void main(String[] args) throws IOException {
        new ImageApp();
    }
}
