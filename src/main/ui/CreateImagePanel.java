package ui;

import model.Image;
import model.exceptions.InvalidInputException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

//TODO: add code credit to oracle
//represents panel that is shown when user is creating a new project
public class CreateImagePanel extends JPanel {
    private JTextField chooseWidth;
    private JTextField chooseHeight;
    private String randomize;
    private final ImageAppGUI iaGUI;
    private ToolMenuPanel toolMenuPanel;
    private int height;
    private int width;

    //MODIFIES: this
    //EFFECTS: creates createImagePanel which is shown to user if create new is chosen from opening menu
    public CreateImagePanel(ImageAppGUI iaGUI) {
        super();
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridBagLayout());
//        createChooseNameField();
        createChooseDimensions();
        createRandomizeChooser();
        createConfirm();

        this.iaGUI = iaGUI;
    }

//    //INSPIRATION SOURCE:
//    //https://stackoverflow.com/questions/11200585/adding-a-prompt-text-property-to-jtextfield
//    //MODIFIES: this
//    //EFFECTS: creates text field element so user can set project name according to regex specifications
//    private void createChooseNameField() {
//        String promptText = "choose name";
//        String errorMessage = "name does not meet requirements";
//        GridBagConstraints c = new GridBagConstraints();
//        JTextField chooseName = new JTextField(promptText);
//        JLabel errorLabel = createErrorLabel(1, 0, 0, errorMessage);
//
//        createListener(chooseName, promptText, errorLabel);
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 2;
//        c.gridx = 0;
//        c.gridy = 1;
//
//        add(chooseName, c);
//    }

    //MODIFIES: this
    //EFFECTS: creates text boxes for dimensions setting and adds them to main layout
    private void createChooseDimensions() {
        createWidthField();
        createHeightField();
    }

    //MODIFIES: this
    //EFFECTS: creates width text field and adds it to main layout
    private void createWidthField() {
        String promptText = "width (px)";
        String errorText = "invalid input";
        GridBagConstraints c = new GridBagConstraints();
        JLabel errorLabel = createErrorLabel(1, 0, 0, errorText);
        chooseWidth = new JTextField(promptText, 10);

        createListener(chooseWidth, promptText, errorLabel);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        add(chooseWidth, c);
    }

    //MODIFIES: this
    //EFFECTS: creates width text field and adds it to layout
    private void createHeightField() {
        String promptText = "height (px)";
        String errorText = "invalid input";
        GridBagConstraints c = new GridBagConstraints();
        JLabel errorLabel = createErrorLabel(1, 1, 0, errorText);
        chooseHeight = new JTextField(promptText, 10);

        createListener(chooseHeight, promptText, errorLabel);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;

        add(chooseHeight, c);
    }

    //MODIFIES: this
    //EFFECTS: creates combo box dropdown so user can choose to randomize project color
    public void createRandomizeChooser() {
        GridBagConstraints c = new GridBagConstraints();
        String[] chooseRandomizeOptions = {"", "yes", "no"};

        createRandomizeLabel();

        ActionListener comboBoxListener = e -> {
            JComboBox cb = (JComboBox) e.getSource();
            randomize = (String) cb.getSelectedItem();
        };

        JComboBox chooseRandomize = new JComboBox(chooseRandomizeOptions);
        chooseRandomize.addActionListener(comboBoxListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;

        add(chooseRandomize, c);
    }

    //MODIFIES: this
    //EFFECTS: creates and adds label for randomize chooser
    private void createRandomizeLabel() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel randomize = new JLabel("Randomize Project Color?");

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;

        add(randomize, c);
    }

    //MODIFIES: this
    //EFFECTS: creates confirm button to create project with specified properties
    private void createConfirm() {
        GridBagConstraints c = new GridBagConstraints();
        JButton confirm = new JButton("Create Project");

        ActionListener confirmListener = e -> {
            this.setVisible(false);
            iaGUI.setMyImage(createImage());
            toolMenuPanel = new ToolMenuPanel(iaGUI);
            iaGUI.add(toolMenuPanel);
        };

        confirm.addActionListener(confirmListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 4;

        add(confirm, c);
    }

    //MODIFIES: this
    //EFFECTS: creates an image with user specifications and sends it to toolMenuPanel
    private Image createImage() {
        width = Integer.parseInt(chooseWidth.getText());
        height = Integer.parseInt(chooseHeight.getText());
        Image myImage = new Image(width, height);

        if (randomize.equals("yes")) {
            myImage.randomizeColor();
        }
        return myImage;
    }

    //INSPIRATION SOURCE FOR PROMPT TEXT:
    //https://stackoverflow.com/questions/11200585/adding-a-prompt-text-property-to-jtextfield
    //MODIFIES: this
    //EFFECTS: creates focus listener for text fields that displays prompt text that disappears when user
    //         clicks on desired textField
    private void createListener(JTextField textField, String promptText, JLabel errorLabel) {
        textField.setForeground(Color.GRAY);
        errorLabel.setVisible(false);

        FocusListener listener = new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                performFocused(textField, promptText, errorLabel);
            }

            @Override
            public void focusLost(FocusEvent e) {
                performFocusLost(textField, promptText, errorLabel);
            }
        };

        textField.addFocusListener(listener);
    }

    //MODIFIES: this
    //EFFECTS: removes prompt text and updates input color when user is engaging with textField
    private void performFocused(JTextField textField, String promptText, JLabel errorLabel) {
        if (textField.getText().equals(promptText)) {
            textField.setText("");
            textField.setForeground(Color.BLACK);
        }
        // for error handling
        textField.setBackground(Color.WHITE);
        errorLabel.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: adds prompt text if needed and handles input exceptions
    private void performFocusLost(JTextField textField, String promptText, JLabel errorLabel) {
        if (textField.getText().isEmpty()) {
            textField.setText(promptText);
            textField.setForeground(Color.GRAY);
        }
        // for error handling
        try {
            validateContents(textField);
        } catch (InvalidInputException i) {
            textField.setBackground(new Color(255, 166, 166));
            errorLabel.setVisible(true);
        }
    }

    //MODIFIES: this
    //EFFECTS: creates and places error JLabel with corresponding error message
    private JLabel createErrorLabel(int gridWidth, int gridx, int gridy, String errorMessage) {
        GridBagConstraints c = new GridBagConstraints();
        JLabel errorLabel = new JLabel(errorMessage);
        errorLabel.setForeground(Color.RED);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = gridWidth;
        c.gridx = gridx;
        c.gridy = gridy;

        add(errorLabel, c);
        return errorLabel;
    }

    //MODIFIES: this
    //EFFECTS: checks contents of text field and throws exception if invalid
    private void validateContents(JTextField textField) throws InvalidInputException {
        String contents = textField.getText();

        try {
            Integer.parseInt(contents);
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}
