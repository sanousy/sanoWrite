/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanowrite;

import javax.swing.JFileChooser;

/**
 *
 * @author HowariS
 */
public class SanoWrite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        convert c = new convert();
        JFileChooser fc = new JFileChooser();
        //fc.showDialog(null, "Select rsml file");
        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String file = fc.getSelectedFile().toString();

            c.build(file);
        }
    }
}
