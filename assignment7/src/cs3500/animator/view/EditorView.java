package cs3500.animator.view;

import cs3500.animator.controller.Features;
import cs3500.animator.model.IViewModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * editor view that is an extension of the visual view. retains the visual views functionality and adds editing features to
 * the animation such as playback features like pause,rewind speed up slow down enable looping etc. and
 * also editing functionality like adding removing and modifying keyFrames of shapes in the animation.
 */
public class EditorView extends JFrame implements IEditorView, ActionListener {

  private JPanel mainPanel;
  private AnimationPanel animationPanel;
  private int speed;

  private JPanel buttonPanel;
  private JButton startButton;
  private JButton pauseButton;
  private JButton resumeButton;
  private JButton rewindButton;
  private JButton loopBackButton;
  private JButton speedUpButton;
  private JButton slowDownButton;
  private JLabel addKeyFrameLabel;
  private JTextField keyFrameInput;
  private JButton addKeyFrameButton;
  private JButton addShapeButton;
  private JLabel addShapeNameLabel;
  private JTextField addShapeNameInput;
  private JLabel addShapeTypeLabel;
  private JTextField addShapeTypeInput;
  private JScrollPane buttonPanelScrollPane;
  private TimerTask timerTask;
  private Timer timer;
  private JLabel speedLabel;
  private JButton modifyKeyFrameButton;
  private JButton deleteKeyFrameButton;
  private JButton deleteShapeButton;
  private JComboBox<String> shapeNamesComboBox;
  private JComboBox<Integer> shapeKeyFramesComboBox;
  private DefaultComboBoxModel<String> shapes;
  private DefaultComboBoxModel<Integer> keyFrames;

  //Variables
  private JTextField xVariable;
  private JTextField yVariable;
  private JTextField widthVariable;
  private JTextField heightVariable;
  private JTextField redVariable;
  private JTextField greenVariable;
  private JTextField blueVariable;

  private IViewModel model;

  /**
   * constructs the user interface of the editor view adds two combo boxes for the shapeNames and keyframes
   * of selected shapeNames adds labels and text fields for all of the attributes of a shape it also
   * shows the playback buttons on the bottom of the window and the animation panel on the right side.
   * @param speed the speed of the animation in ticks per second
   * @param model the read only model of the animation
   */
  public EditorView(int speed, IViewModel model) {
    super();
    this.model = model;
    this.speed = speed;
    this.setTitle("Visual Animation Editor View");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //the main panel of the editor view
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);

    //top part of the main panel has the combo boxes and animation panel
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
    mainPanel.add(topPanel);

    //this is the bottom panel that will have the playback buttons
    //this button will have all buttons be horizontal in flow layout
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.setPreferredSize(new Dimension(700, 100));

    // start button
    startButton = new JButton("Start animation");
    startButton.setActionCommand("start button");
    startButton.addActionListener(this);
    buttonPanel.add(startButton);

    // pause button
    pauseButton = new JButton("Pause");
    pauseButton.setActionCommand("pause button");
    pauseButton.addActionListener(this);
    buttonPanel.add(pauseButton);

    // resume button
    resumeButton = new JButton("Resume");
    resumeButton.setActionCommand("resume button");
    resumeButton.addActionListener(this);
    buttonPanel.add(resumeButton);

    // rewind button
    rewindButton = new JButton("Rewind");
    rewindButton.setActionCommand("rewind button");
    rewindButton.addActionListener(this);
    buttonPanel.add(rewindButton);

    // looping button (starts as disable)
    loopBackButton = new JButton("Disable looping");
    loopBackButton.setActionCommand("loop button");
    loopBackButton.addActionListener(this);
    buttonPanel.add(loopBackButton);

    // speed up button
    speedUpButton = new JButton("Speed up");
    speedUpButton.setActionCommand("speed up button");
    speedUpButton.addActionListener(this);
    buttonPanel.add(speedUpButton);

    // slow down button
    slowDownButton = new JButton("Slow down");
    slowDownButton.setActionCommand("slow down button");
    slowDownButton.addActionListener(this);
    buttonPanel.add(slowDownButton);

    speedLabel = new JLabel(speed + " tick(s) per second");
    Border speedBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
    speedLabel.setBorder(speedBorder);
    buttonPanel.add(speedLabel);

    addKeyFrameLabel = new JLabel("Add keyFrame at tick:");
    buttonPanel.add(addKeyFrameLabel);

    keyFrameInput = new JTextField(5);
    buttonPanel.add(keyFrameInput);

    addKeyFrameButton = new JButton("Add keyFrame");
    addKeyFrameButton.setActionCommand("add keyFrame");
    buttonPanel.add(addKeyFrameButton);

    addShapeNameLabel = new JLabel("Add shape: name ");
    buttonPanel.add(addShapeNameLabel);

    addShapeNameInput = new JTextField(10);
    buttonPanel.add(addShapeNameInput);

    addShapeTypeLabel = new JLabel("type ");
    buttonPanel.add(addShapeTypeLabel);

    addShapeTypeInput = new JTextField(10);
    buttonPanel.add(addShapeTypeInput);

    addShapeButton = new JButton("Add Shape");
    addShapeButton.setActionCommand("add shape");
    buttonPanel.add(addShapeButton);

    buttonPanelScrollPane = new JScrollPane(buttonPanel);

    //panel that will have a combo box with all the shape names in them added to the top panel
    JPanel comboBoxPanel = new JPanel();

    comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.PAGE_AXIS));

    //this sets up the top combo box that has all the shape names
//    JComboBox<String> shapeNamesComboBox;
//    JComboBox<Integer> shapeKeyFramesComboBox;
    shapes = new DefaultComboBoxModel<>();

    for (String name : model.getAnimationLog().keySet()) {
      shapes.addElement(name);
    }
    shapeNamesComboBox = new JComboBox<>(shapes);
    shapeNamesComboBox.setActionCommand("shape options");
    JLabel shapeNamesCBLabel = new JLabel("Shape name: ");
    comboBoxPanel.add(shapeNamesCBLabel);
    comboBoxPanel.add(shapeNamesComboBox);

    //sets up the bottom combo box that has the selected shape names key frames
    keyFrames = new DefaultComboBoxModel<>();
    shapeKeyFramesComboBox = new JComboBox<>(keyFrames);
    shapeKeyFramesComboBox.setActionCommand("keyFrame options");
    JLabel shapeKeyFrameCBLabel = new JLabel("KeyFrame: ");
    comboBoxPanel.add(shapeKeyFrameCBLabel);
    comboBoxPanel.add(shapeKeyFramesComboBox);

    topPanel.add(comboBoxPanel);

    JPanel variablesPanel = new JPanel();
    variablesPanel.setLayout(new BoxLayout(variablesPanel, BoxLayout.PAGE_AXIS));

    xVariable = new JTextField(5);
    JLabel xLabel = new JLabel("X:");
    yVariable = new JTextField(5);
    JLabel yLabel = new JLabel("Y:");
    widthVariable = new JTextField(5);
    JLabel widthLabel = new JLabel("Width:");
    heightVariable = new JTextField(5);
    JLabel heightLabel = new JLabel("Height:");
    redVariable = new JTextField(5);
    JLabel redLabel = new JLabel("Red:");
    greenVariable = new JTextField(5);
    JLabel greenLabel = new JLabel("Green:");
    blueVariable = new JTextField(5);
    JLabel blueLabel = new JLabel("Blue:");

    variablesPanel.add(xLabel);
    variablesPanel.add(xVariable);
    variablesPanel.add(yLabel);
    variablesPanel.add(yVariable);
    variablesPanel.add(widthLabel);
    variablesPanel.add(widthVariable);
    variablesPanel.add(heightLabel);
    variablesPanel.add(heightVariable);
    variablesPanel.add(redLabel);
    variablesPanel.add(redVariable);
    variablesPanel.add(greenLabel);
    variablesPanel.add(greenVariable);
    variablesPanel.add(blueLabel);
    variablesPanel.add(blueVariable);
    topPanel.add(variablesPanel);

    modifyKeyFrameButton = new JButton("Modify");
    modifyKeyFrameButton.setActionCommand("modify keyframe");
    variablesPanel.add(modifyKeyFrameButton);

    deleteKeyFrameButton = new JButton("Delete Keyframe");
    deleteKeyFrameButton.setActionCommand("delete keyframe");
    variablesPanel.add(deleteKeyFrameButton);

    deleteShapeButton = new JButton("Delete shape");
    deleteShapeButton.setActionCommand("delete shape");
    comboBoxPanel.add(deleteShapeButton);

    mainPanel.add(buttonPanelScrollPane);
    this.add(mainScrollPane);

    //the panel where the animation occurs
    animationPanel = new AnimationPanel(model);
    animationPanel.setPreferredSize(new Dimension(model.getCanvasX() + model.getCanvasWidth(),
        model.getCanvasY() + model.getCanvasHeight()));

    JScrollPane scrollPane = new JScrollPane(animationPanel);

    timerTask = null;
    timer = null;
    // timer.schedule(timerTask, 0, convertToMs(speed));

    topPanel.add(scrollPane);
    // this.add(topPanelScrollPane);

    this.setVisible(true);

  }

  @Override
  public void addFeatures(Features features) {
    addShapeButton.addActionListener(evt ->
        features.addShape(addShapeNameInput.getText(), addShapeTypeInput.getText()));
    deleteShapeButton.addActionListener(evt ->
        features.deleteShape(shapes.getSelectedItem().toString()));

    shapeNamesComboBox.addActionListener(evt ->
        features.displayTicks(shapes.getSelectedItem().toString()));

    deleteKeyFrameButton.addActionListener(evt ->
        features.deleteKeyFrame(shapes.getSelectedItem().toString(),
            checkParsing(keyFrames.getSelectedItem().toString())));

    addKeyFrameButton
        .addActionListener(evt -> features.addKeyFrame(shapes.getSelectedItem().toString(),
            checkParsing(keyFrameInput.getText())));

    modifyKeyFrameButton
        .addActionListener(evt -> features.modifyKeyFrame(shapes.getSelectedItem().toString(),
            checkParsing(keyFrames.getSelectedItem().toString()), xVariable.getText(),
            yVariable.getText(), widthVariable.getText(), heightVariable.getText(),
            redVariable.getText(), greenVariable.getText(), blueVariable.getText()));

    try {
      shapeKeyFramesComboBox.addActionListener(evt ->
          features.displayVariables(shapes.getSelectedItem().toString(),
              checkParsing(keyFrames.getSelectedItem().toString())));
    } catch (NumberFormatException e) {
      e.getMessage();
    }

  }


  private Integer checkParsing(String item) {
    if (item == null) {
      return null;
    } else {
      return Integer.parseInt(item);
    }
  }

  @Override
  public void addToNamesComboBox(String shapeName) {
    shapeNamesComboBox.addItem(shapeName);
    addShapeNameInput.setText("");
    addShapeTypeInput.setText("");

  }

  @Override
  public void deleteFromNamesComboBox(String shapeName) {
    shapeNamesComboBox.removeItem(shapeName);

  }

  @Override
  public void addKeyFramesToComboBox(List<Integer> ticks) {
    if (ticks == null) {
      System.out.println("empty ticks");

    } else {
      if (shapeKeyFramesComboBox != null) {
        DefaultComboBoxModel<Integer> newModel = new DefaultComboBoxModel<>();
        for (Integer t : ticks) {
          newModel.addElement(t);
        }
        keyFrames = newModel;
        shapeKeyFramesComboBox.setModel(keyFrames);
      }

    }
  }

  @Override
  public void displayKeyFrameVariables(int x, int y, int w, int h, int r, int g, int b) {
    xVariable.setText(x + "");
    yVariable.setText(y + "");
    widthVariable.setText(w + "");
    heightVariable.setText(h + "");
    redVariable.setText(r + "");
    greenVariable.setText(g + "");
    blueVariable.setText(b + "");
  }

  @Override
  public void deleteFromKeyFramesComboBox(int t) {
    System.out.println(keyFrames.getSize());
    if(keyFrames.getSize() > 0) {
      if (shapeKeyFramesComboBox != null) {
        shapeKeyFramesComboBox.removeItem(t);
        //keyFrames.removeElement(t);

        //shapeKeyFramesComboBox.setModel(keyFrames);
      }
    }


  }

  @Override
  public boolean keyFramesComboBoxContains(int t) {
    return keyFrames.getIndexOf(t) != -1 ;
  }

  @Override
  public void addToKeyFramesComboBox(int t) {
    shapeKeyFramesComboBox.addItem(t);
    keyFrameInput.setText("");
  }


  /**
   * Converts the speed to milliseconds in a simple calculation.
   *
   * @param s the speed in ticks per second
   * @return the converted speed in milliseconds
   */
  private long convertToMs(int s) {
    return (1000 / (long) s);
  }


  @Override
  public void refresh() {
    this.repaint();
  }




  private void pause() {
    if (timer != null && timerTask != null) {
      this.timer.cancel();
      this.timer = null;
      this.timerTask = null;
    }

  }

  private void resume() {
    if (timer == null && timerTask == null) {
      this.timer = new Timer();
      this.timerTask = new EditorTimer(this);
      this.timer.schedule(timerTask, 0, convertToMs(speed));
    }

  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "pause button":
        this.pause();
        break;
      case "resume button":
      case "start button":
        this.resume();
        break;
      case "rewind button":
        animationPanel.setCurrentTick(1);
        this.refresh();
        this.pause();
        break;
      case "speed up button":
        speed++;
        speedLabel.setText(speed + " tick(s) per second");
        this.pause();
        this.resume();
        break;
      case "slow down button":
        if (speed > 1) {
          speed--;
          speedLabel.setText(speed + " tick(s) per second");
        }
        this.pause();
        this.resume();
        break;
      case "loop button":
        animationPanel.setLoop(!animationPanel.getLoop());
        if (loopBackButton.getText().equals("Enable looping")) {
          loopBackButton.setText("Disable looping");
        } else {
          loopBackButton.setText("Enable looping");
        }

    }
  }
}
