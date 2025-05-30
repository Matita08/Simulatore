package io.matita08.GUI.tests;

import javax.swing.SpringLayout;
import javax.swing.Spring;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Component;

public class SpringDemo {
   /**
    * Create the GUI and show it.  For thread safety,
    * this method should be invoked from the
    * event-dispatching thread.
    */
   private static void createAndShowGUI() {
      //Create and set up the window.
      JFrame frame = new JFrame("SpringDemo4");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //Set up the content pane.
      Container contentPane = frame.getContentPane();
      SpringLayout layout = new SpringLayout();
      contentPane.setLayout(layout);
      
      //Create and add the components.
      JLabel label = new JLabel("Label: ");
      JTextField textField = new JTextField("Text field", 15);
      contentPane.add(label);
      contentPane.add(textField);
      
      //Adjust constraints for the label so it's at (5,5).
      SpringLayout.Constraints  labelCons =
          layout.getConstraints(label);
      labelCons.setX(Spring.constant(5));
      labelCons.setY(Spring.constant(5));
      
      //Adjust constraints for the text field so it's at
      //(<label's right edge> + 5, 5).
      SpringLayout.Constraints textFieldCons =
          layout.getConstraints(textField);
      textFieldCons.setX(Spring.sum(Spring.constant(5),
          labelCons.getConstraint(SpringLayout.EAST)));
      textFieldCons.setY(Spring.constant(5));
      
      //Adjust constraints for the content pane.
      setContainerSize(contentPane, 5);
      
      //Display the window.
      frame.pack();
      frame.setVisible(true);
   }
   
   public static void main(String[] args) {
      //Schedule a job for the event-dispatching thread:
      //creating and showing this application's GUI.
      javax.swing.SwingUtilities.invokeLater(SpringDemo::createAndShowGUI);
   }
   
   public static void setContainerSize(Container parent, int pad) {
      SpringLayout layout = (SpringLayout) parent.getLayout();
      Component[] components = parent.getComponents();
      Spring maxHeightSpring = Spring.constant(0);
      SpringLayout.Constraints pCons = layout.getConstraints(parent);
      
      //Set the container's right edge to the right edge
      //of its rightmost component + padding.
      Component rightmost = components[components.length - 1];
      SpringLayout.Constraints rCons =
          layout.getConstraints(rightmost);
      pCons.setConstraint(
          SpringLayout.EAST,
          Spring.sum(Spring.constant(pad),
              rCons.getConstraint(SpringLayout.EAST)));
      
      //Set the container's bottom edge to the bottom edge
      //of its tallest component + padding.
      for (Component component: components) {
         SpringLayout.Constraints cons =
             layout.getConstraints(component);
         maxHeightSpring = Spring.max(maxHeightSpring,
             cons.getConstraint(
                 SpringLayout.SOUTH));
      }
      pCons.setConstraint(
          SpringLayout.SOUTH,
          Spring.sum(Spring.constant(pad), maxHeightSpring));
   }
}
