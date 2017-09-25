/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanowrite;

import sano.RSML;
import sano.Attribute;
import sano.Node;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sano.TextFile;


/**
 *
 * @author HowariS
 */
public class convert {

    String inFile = "";
    String outFile = "";
    //String line;
    //StringBuilder content = null;
    //Node root = new Node("root", "root");
    List<Class> classes = new ArrayList<>();
    boolean debug = true;

    void build(String FileName) {
        inFile = FileName;
        outFile = inFile + ".html";

        try {
            RSML rsml = new RSML();
            rsml.read(inFile);

            if (debug) {
                display(rsml);
            }
            generate(rsml);
            String path = output.getPath();

            ApplicationFrame wv = new ApplicationFrame();
            wv.setVisible(true);
            wv.path = "file:///" + path;
            wv.loadJavaFXScene();

        } catch (IOException ex) {
            Logger.getLogger(convert.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    int i = 0;
   
    Class createClass(Node n) {
        Class c = null;
        Class p = null;
        String baseName = "";
        //search parent first
        for (int i = 0; i < n.attributes.size(); i++) {
            Attribute a = n.attributes.get(i);
            if (a.name.equals("base")) {
                baseName = a.value.toLowerCase();
                for (int j = 0; j < classes.size(); j++) {
                    if (classes.get(j).name.equals(baseName)) {
                        p = classes.get(j);
                    }
                }
                break;
            }
        }
// create class with parent p (could be null)
        c = new Class(p);
        c.name = n.value;
        for (int i = 0;
                i < n.attributes.size();
                i++) {
            Attribute a = n.attributes.get(i);
            switch (a.name) {

                case "numbering":
                    c.numbering = a.value;
                    break;
                case "restartNumbering":
                    c.restartNumbering = true;// for now
                    break;
                case "font":
                    c.font = a.value;
                    break;
                case "indents":
                    c.indents = a.value;
                    break;
                case "color":
                    c.color = a.value;
                    break;
                case "bgcolor":
                    c.bgcolor = a.value;
                    break;
                case "spacing":
                    c.spacing = a.value;
                    break;
                case "size":
                    c.size = a.value;
                    break;
                case "face":
                    c.face = a.value;
                    break;
                case "name":
                    c.name = a.value;
                    break;
                case "align":
                    c.align = a.value;
                    break;
                default:
                    break;
            }
        }
        return c;
    }

    void display(RSML rsml) {
        System.out.println(rsml.root.children.size());
        for (int ix = 0; ix < rsml.root.children.size(); ix++) {
            display_details(rsml.root.children.get(ix));
        }
    }

    void display_details(Node n) {
        System.out.println("Item:" + n.name + " value:" + n.value);
        // if (n.attributes.size() >= 0) {
        System.out.println("has:" + n.attributes.size() + " Attibutes {");
        for (int ix = 0; ix < n.attributes.size(); ix++) {
            Attribute a = n.attributes.get(ix);
            System.out.println("Attribute:" + a.name + " value:" + a.value);
        }
        System.out.println("}");
        // }
        //  if (n.children.size() >= 0) {
        System.out.println("the children of " + n.name + "{");

        for (int ix = 0; ix < n.children.size(); ix++) {
            Node b = n.children.get(ix);
            display_details(b);
        }
        System.out.println("}");
        //}

    }

    TextFile output = null;

    void generate(RSML rsml) {
        try {
            output = new TextFile(outFile, TextFile.Type.WRITE);
            output.writeLine(Html.firstLines);
            output.writeLine(Html.css);
            for (int i = 0; i < rsml.root.children.size(); i++) {
                Node n = rsml.root.children.get(i);
                if (n.name.compareToIgnoreCase("styles") == 0) {
                    for (int j = 0; j < n.children.size(); j++) {
                        Node styl = n.children.get(j);
                        classes.add(createClass(styl));
                    }
                }
            }
            for (int i = 0; i < classes.size(); i++) {
                Class c = classes.get(i);
                output.writeLine(c.toCSS());
            }
            output.writeLine(Html.endCSS);

            //output.writeLine(Html.page);
            for (int ix = 0; ix < rsml.root.children.size(); ix++) {
                generate_details(rsml.root.children.get(ix));
            }
            output.writeLine(Html.endPage);
            output.writeLine(Html.lastLines);
            output.close();
        } catch (IOException e) {
        }

    }

    void generate_details(Node n) {
        String endTag = "";
        try {
            String att = "";
            Attribute a = null;
            for (int jj = 0; jj < n.attributes.size(); jj++) {
                a = n.attributes.get(jj);
                String name = a.name;
                if (name.trim().toLowerCase().compareTo("style") == 0) {
                    name = "class";
                }
                att += " " + name + "='" + a.value + "';";
            }
            if (n.name.equals("document")) {
                output.write("<body class='document'; " + att + ">" + n.value);
                endTag = "</body>";
            } else if (n.name.equals("p")) {
                if (n.parent.name.equals("l")) {
                    output.write("<li" + att + ">" + n.value);
                    endTag = "</li>";
                } else {
                    output.write("<p" + att + ">" + n.value);
                    endTag = "</p>";
                }
            } else if (n.name.equals("t")) {
                output.write("<span" + att + ">" + n.value);
                endTag = "</span>";
            } else if (n.name.equals("tbl")) {
                output.write("<table border=1; cellspacing=0; cellpadding=0" + att + ">");
                endTag = "</table>";
            } else if (n.name.equals("r")) {
                output.write("<row" + att + ">");
                endTag = "</row>";
            } else if (n.name.equals("c")) {
                output.write("<td" + att + ">" + n.value);
                endTag = "</td>";
            } else if (n.name.equals("hd")) {
                output.write("<div class='header'" + att + ">" + n.value);
                endTag = "</div>";
            } else if (n.name.equals("ft")) {
                output.write("<div class='footer'" + att + ">" + n.value);
                endTag = "</div>";
            } else if (n.name.equals("sec")) {
                output.write("<page size=\"A4\">");
                endTag = "</page>";
            } else if (n.name.equals("l")) {
                int type = 0;
                if (n.attributes.size() > 0) {
                    if (n.attributes.get(0).value.equals("*")) {
                        type = 1;
                    }
                }
                if (type == 1) {
                    output.write("<ul>" + n.value);
                    endTag = "</ul>";
                } else {
                    output.write("<ol>" + n.value);
                    endTag = "</ol>";
                }

            } else {
                System.out.println("Unknown type " + n.name);

            }
        } catch (IOException ex) {
            Logger.getLogger(convert.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        for (int ix = 0;
                ix < n.children.size();
                ix++) {
            Node b = n.children.get(ix);
            generate_details(b);
        }

        try {
            output.writeLine(endTag);

        } catch (IOException ex) {
            Logger.getLogger(convert.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
