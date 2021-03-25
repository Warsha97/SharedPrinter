/** *********************************************************************
 * Author:    Warsha Kiringoda
 * UoW id:    w1697817
 * IIT id:    2017366
 ************************************************************************ */
package utils;

public class TextFormat {
    //ANSI color codes for output text colors
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    //defining output types
    public static final String STUDENT = "[STUDENT]";
    public static final String PAPER_TECHNICIAN = "[PAPER_TECHNICIAN]";
    public static final String TONER_TECHNICIAN = "[TONER_TECHNICIAN]";
    public static final String PRINTER = "[PRINTER]";
    public static final String PRINTING_SYSTEM = "[PRINTING_SYSTEM]";

//Add color to text based on output type
   public static String addColor(String user){
       switch (user){
           case STUDENT:
               return ANSI_PURPLE;
           case PAPER_TECHNICIAN:
               return ANSI_GREEN;
           case TONER_TECHNICIAN:
               return ANSI_YELLOW;
           case PRINTER:
               return ANSI_BLUE;
           case PRINTING_SYSTEM:
               return ANSI_RED;
               default:
                   return ANSI_RESET;
       }
   }

   //reset color
   public static String endColor(){
       return ANSI_RESET;
   }

}
