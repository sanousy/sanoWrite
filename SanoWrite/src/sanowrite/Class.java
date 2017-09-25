/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanowrite;

/**
 *
 * @author HowariS
 */
public class Class {

    public Class parent = null;
    public String name = "";
    public String numbering = "";
    public boolean restartNumbering = false;
    public String font = "";
    public String indents = "0,0,0";
    public String color = "black";
    public String bgcolor = "white";
    public String spacing = "1,1,1";
    public String size = "12";
    public String face = "";
    public String align = "left";
    public String direction = "ltr";

    Class(Class parent) {
        if (parent != null) {
            numbering = parent.numbering;
            restartNumbering = parent.restartNumbering;
            font = parent.font;
            indents = parent.indents;
            color = parent.color;
            bgcolor = parent.bgcolor;
            spacing = parent.spacing;
            size = parent.size;
            face = parent.face;
            align = parent.align;
            direction = parent.direction;
        }
    }

    public String toCSS() {
        StringBuilder sb = new StringBuilder();
        sb.append("." + this.name + " {\n");
        sb.append("\t font-family:" + this.font + ";\n");
        String[] xx = this.indents.split(",");
        try {
            sb.append("\t margin-left:" + xx[0] + "em;\n");
        } catch (Exception e) {
        }
        try {
            sb.append("\t margin-right:" + xx[2] + "em;\n");
        } catch (Exception e) {
        }
        try {
            sb.append("\t text-indent:" + xx[1] + "em;\n");
        } catch (Exception e) {
        }
        String[] yy = this.spacing.split(",");
        try {
            sb.append("\t line-height:" + yy[1] + "em;\n");
        } catch (Exception e) {
        }
        try {
            sb.append("\t margin-top:" + yy[0] + "em;\n");
        } catch (Exception e) {
        }
        try {
            sb.append("\t margin-bottom:" + yy[2] + "em;\n");
        } catch (Exception e) {
        }

        sb.append("\t color:" + this.color + ";\n");
        sb.append("\t background-color:" + this.bgcolor + ";\n");
        sb.append("\t font-size:" + this.size + "px;\n");
        sb.append("\t font-style:" + this.face + ";\n");
        sb.append("\t text-align:" + this.align + ";\n");
        sb.append("\t direction:" + this.direction + ";\n}");

        return sb.toString();

    }
}
