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
public class Html {

    static String firstLines = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "	<head>\n"
            + "		<meta charset=\"utf-8\">\n"
            + "		<title>%%%%</title>\n"
            + "	</head>\n";

    static String lastLines =
            "</html>";

    static String page = "<div  class=\"page\" contenteditable=\"true\">";
    static String endPage = "</div>";
    static String gt = "&gt;";
    static String lt = "&lt;";

    static String css = "<style> \n"
            + "body {\n"
            + "  background: rgb(204,204,204); \n"
            + "}\n"
            + "page {\n"
            + "  background: white;\n"
            + "  display: block;\n"
            + "  margin: 0 auto;\n"
            + "  margin-bottom: 0.5cm;\n"
            + "  box-shadow: 0 0 0.5cm rgba(0,0,0,0.5);\n"
            + "}\n"
            + "page[size=\"A4\"] {  \n"
            + "  width: 21cm;\n"
            + "  height: 29.7cm; \n"
            + " magrin-top: 2cm;"
            + " magrin-bottom: 2cm;"
            + " magrin-left: 2cm;"
            + " magrin-right: 2cm;"
            + "	padding-left: 2cm;\n"
            + "	padding-top: 2cm;\n"
            + "	padding-right: 2cm;\n"
            + "	padding-bottom: 2cm;"
            + "	overflow-y:hidden;"
            + "}\n"
            + "page[size=\"A4\"][layout=\"portrait\"] {\n"
            + "  width: 29.7cm;\n"
            + "  height: 21cm;  \n"
            + "}\n"
            + "page[size=\"A3\"] {\n"
            + "  width: 29.7cm;\n"
            + "  height: 42cm;\n"
            + "}\n"
            + "page[size=\"A3\"][layout=\"portrait\"] {\n"
            + "  width: 42cm;\n"
            + "  height: 29.7cm;  \n"
            + "}\n"
            + "page[size=\"A5\"] {\n"
            + "  width: 14.8cm;\n"
            + "  height: 21cm;\n"
            + "}\n"
            + "page[size=\"A5\"][layout=\"portrait\"] {\n"
            + "  width: 21cm;\n"
            + "  height: 14.8cm;  \n"
            + "}\n"
            + "@media print {\n"
            + "  body, page {\n"
            + "    margin: 0;\n"
            + "    box-shadow: 0;\n"
            + "  }\n"
            + "}";

    static String endCSS = "\n</style>";
}
