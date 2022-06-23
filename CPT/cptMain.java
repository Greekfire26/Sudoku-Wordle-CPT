/*
cptMain.java
Name: Nicholas Saloufakos
Description: My CPT, arcade program with two different games to play. Sudoku and Wordle
Date Created: May 20th, 2022
Date Last Modified: June 21st, 2022
*/

package CPT;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class cptMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // sets the "look and feel", which are pre made templates for buttons, labels etc
            @Override
            public void run() {
                try {
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { // loop through all installed "look and feels"
                        if ("Nimbus".equals(info.getName())) { // if nimbus (Name of a specific look and feel) is found
                            UIManager.setLookAndFeel(info.getClassName()); // set it to be active
                            break;
                        }
                    }
                } catch (Exception e) { // if nimbus look and feel can't be found
                    System.out.println("Nimbus Look and Feel Not Installed!");
                }
                GUI gui = new GUI(); // create instance of GUI class
                gui.menu(); // call the menu method of the GUI class
            }
        });
    }
}