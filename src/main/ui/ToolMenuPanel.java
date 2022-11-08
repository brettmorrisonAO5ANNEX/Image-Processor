package ui;

import model.Filter;
import model.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//represents the tool menu panel that is shown while a user is editing
public class ToolMenuPanel extends JPanel {
    private final ImageAppGUI iaGUI;
    private Image myImage;
    private JPanel menuPanel;
    private JPanel historyPanel;
    private AddFilterPanel addFilterPanel;

    public ToolMenuPanel(ImageAppGUI iaGUI) {
        super();
        this.iaGUI = iaGUI;
        myImage = iaGUI.getMyImage();

        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridLayout(0,2));
        createAndAddSubPanels();

        int width = iaGUI.getMyImage().getImageWidth();
        int height = iaGUI.getMyImage().getImageHeight();
        this.addFilterPanel = new AddFilterPanel(this, width, height);
    }

    //MODIFIES: this
    //EFFECTS: creates subPanels for tool menu view history to be added to this
    private void createAndAddSubPanels() {
        createMenuPanel();
        createHistoryPanel();
    }

    //MODIFIES: this
    //EFFECTS: creates menu panel to be added to tool menu panel
    private void createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder());
        menuPanel.setLayout(new GridLayout(0,1));

        createButtons();

        add(menuPanel);
    }

    //MODIFIES: this
    //EFFECTS: adds menu buttons to menuPanel
    private void createButtons() {
        createAddFilterButton();

        JButton undo = new JButton("Undo");
        menuPanel.add(undo);
//        JComboBox undoType = new JComboBox(createOptions());
//        menuPanel.add(undoType);
        JButton undoAll = new JButton("Undo All");
        menuPanel.add(undoAll);
        JButton processProject = new JButton("Process Project");
        menuPanel.add(processProject);
        JButton quitAndSave = new JButton("Quit And Save");
        menuPanel.add(quitAndSave);
    }

    //MODIFIES: this
    //EFFECTS: creates add filter button such that when pressed, panel changes to add filter panel
    private void createAddFilterButton() {
        JButton addFilter = new JButton("Add Filter");

        ActionListener addFilterListener = e -> {
            setVisible(false);
            iaGUI.add(addFilterPanel);
        };

        addFilter.addActionListener(addFilterListener);
        menuPanel.add(addFilter);
    }

    public Component getAddFilterPanel() {
        return this.addFilterPanel;
    }

    public ImageAppGUI getImageAppGUI() {
        return this.iaGUI;
    }

//    //MODIFIES: this
//    //EFFECTS: creates String[] from unique filters used for undo type functionality
//    private Object[] createOptions() {
//        int len = myImage.getUniqueFiltersUsed().size();
//        String[] filterOptions = new String[len];
//        for (int i = 0; i < len; i++) {
//            filterOptions[i] = myImage.getUniqueFiltersUsed().get(i);
//        }
//        return filterOptions;
//    }

    //MODIFIES: this
    //EFFECTS: creates history panel to show what edits have been made to far
    private void createHistoryPanel() {
        historyPanel = new JPanel();
        historyPanel.setLayout(new GridLayout(0,1));
        historyPanel.setBorder(BorderFactory.createEmptyBorder());
        historyPanel.setBackground(new Color(220,220,220));
        createAndAddJLabels(historyPanel);

        add(historyPanel);
    }

    //MODIFIES: this
    //EFFECTS: creates JLabel
    private void createAndAddJLabels(JPanel historyPanel) {
        int index = 1;
        for (Filter filter: myImage.getListOfFilter()) {
            String name = filter.getFilterName();
            JLabel edit = new JLabel(index + ": " + name);
            historyPanel.add(edit);
            index++;
        }
    }
}
