//package ui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
////INSPIRATION SOURCE: https://www.youtube.com/watch?v=tHNWIWxRDDA&t=617s
////represents loading panel with load bar and animation after processing is applied
//public class LoadingPanel extends JPanel implements ActionListener {
//    private int progress = 0;
//    private double degRot = 0;
//    private ImageAppGUI iaGUI;
//    private JProgressBar progressBar;
//    private OriginalIcon originalIcon;
//
//    public LoadingPanel(ImageAppGUI iaGUI) {
//        setBorder(BorderFactory.createEmptyBorder());
//        setLayout(new GridBagLayout());
//        setBackground(new Color(206, 226, 255));
//
//        this.iaGUI = iaGUI;
//
//        createAnimationComponents();
//        iaGUI.add(this);
//
//        Timer timer = new Timer(10, this);
//        timer.start();
//    }
//
//
//    //MODIFIES: this
//    //EFFECTS: adds loading bar and spinning gear animation
//    private void createAnimationComponents() {
//        createGearIcon();
//        createProgressBar();
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates and adds gear icon to this
//    private void createGearIcon() {
//        GridBagConstraints c = new GridBagConstraints();
//        ImageIcon gearIcon = new ImageIcon("./data/UI/gear.png");
//
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 0;
//
//        add(new JLabel(gearIcon, JLabel.CENTER), c);
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates and adds progress bar to this
//    private void createProgressBar() {
//        GridBagConstraints c = new GridBagConstraints();
//        progressBar = new JProgressBar();
//
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 1;
//
//        add(progressBar, c);
//    }
//
//    //MODIFIES: this
//    //EFFECTS: updates progress with timer
//    public void paint(Graphics g) {
//        super.paint(g);
//        progressBar.setValue(progress);
//    }
//
//    /**
//     * Invoked when an action occurs.
//     *
//     * @param e the event to be processed
//     */
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (progress < 101) {
//            progress++;
//        } else {
//            createAndAddFinalResult();
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates new ViewResultsPanel, adds resulting pixel array, and displays the ViewResultPanel
//    private void createAndAddFinalResult() {
//        originalIcon = new OriginalIcon(iaGUI.getMyImage(), iaGUI);
//        setVisible(false);
//    }
//}
