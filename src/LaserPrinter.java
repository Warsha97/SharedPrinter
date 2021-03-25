/** *********************************************************************
 * Author:    Warsha Kiringoda
 * UoW id:    w1697817
 * IIT id:    2017366
 ************************************************************************ */
import utils.TextFormat;
import utils.MethodService;

/*shared printer implementation as a monitor*/
public class LaserPrinter implements ServicePrinter {

    //declaring private variables
    private int printerID;
    private int currentPaperLevel;
    private int currentTonerLevel;
    private int numberOfDocumentsPrinted;
    private ThreadGroup studentThreadGroup;

    //constructor
    public LaserPrinter(String name, int id, ThreadGroup studentThreadGroup){
     this.printerID = id;
     this.currentPaperLevel = Full_Paper_Tray;
     this.currentTonerLevel = Full_Toner_Level;
     this.numberOfDocumentsPrinted = 0;
     this.studentThreadGroup = studentThreadGroup;
    }


    //Returns the current state of the printer
    @Override
    public String toString() {
        return new String ("Laser Printer[ "  +
                "PrinterID: " + printerID        + ", " +
                "Paper Level: "   + currentPaperLevel  + ", " +
                "Toner Level: "  + currentTonerLevel + "," +
                "Documents Printed: "+ numberOfDocumentsPrinted +
                "]" );
    }



    //Method that allows Students to print documents
    @Override
    public synchronized void  printDocument(Document document) {
        //Critical section
      while(currentPaperLevel<document.getNumberOfPages() || currentTonerLevel<document.getNumberOfPages()){ //Obligatory-Guarded

         if(currentPaperLevel<document.getNumberOfPages()){
             System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Insufficient Paper level. Wait until paper refill" + TextFormat.endColor());
             System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Current State of Printer: "+ toString() + TextFormat.endColor());

         }
         else if(currentTonerLevel<document.getNumberOfPages()){
             System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Insufficient Toner Level. Wait until Toner Cartridge Replace" + TextFormat.endColor());
             System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Current State of Printer: "+ toString() + TextFormat.endColor());
         }
         else{
             System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Insufficient Paper&Toner level. Wait until paper&toner refill" + TextFormat.endColor());
             System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Current State of Printer: "+ toString() + TextFormat.endColor());
         }
         try{ //release monitor //current thread goes to waiting state
             wait();
             //after getting 'notify()', re-acquire monitor to check if paper/toner is now sufficient
         }catch (InterruptedException e){}

      }

      //Thread has delegated the Guard i.e: OK to print
      if(currentPaperLevel>=document.getNumberOfPages() && currentTonerLevel>=document.getNumberOfPages()){
          currentPaperLevel-=document.getNumberOfPages();
          currentTonerLevel-=document.getNumberOfPages();
          numberOfDocumentsPrinted++;
          System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Printed "+document.getUserID() + TextFormat.endColor());
          System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Current State of Printer: "+ toString() + TextFormat.endColor());
          notifyAll();
          //monitor released by student
      }
    }


   //Allow paper technician to refill the paper tray
    @Override
    public synchronized void refillPaper() { //monitor method type "Miscellaneous"
     //critical section
       while((currentPaperLevel+SheetsPerPack) > Full_Paper_Tray){//guard
           try{
               if(isTaskOver()){
                   System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "All Printing Over! No Need To Refill Papers. "+ TextFormat.endColor());
                   break;
               }
               else{
                   System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Cannot Refill Papers Now, Please Wait.. "+ TextFormat.endColor());
                   //release monitor and wait for 5seconds
                   wait(5000);
                   //try to re-acquire monitor
               }
           }catch (InterruptedException e){}
       }

       if((currentPaperLevel+SheetsPerPack)<=Full_Paper_Tray){
           System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER)+TextFormat.PRINTER + "Papers Refilled!"  + TextFormat.endColor());
           currentPaperLevel = currentPaperLevel + SheetsPerPack;
           System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Current State of Printer: "+ toString() + TextFormat.endColor());
           notifyAll();
           //Monitor released by paperTechnician
       }
    }


    //Allow the toner technician to replace the toner cartridge
    @Override
    public synchronized void replaceTonerCartridge() { //monitor method type "Miscellaneous"
       //critical section
        while((currentTonerLevel>=Minimum_Toner_Level) && !isTaskOver()){//Guard
          try{
              System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Cannot Replace Cartridge Now, Please Wait.. "+ TextFormat.endColor());
              //release monitor and wait for 5sec
              wait(5000);
          }catch (InterruptedException e){}
        }

        if(currentTonerLevel<Minimum_Toner_Level){
            System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Toner Cartridge Replaced!. "+ TextFormat.endColor());

            currentTonerLevel = Full_Toner_Level;
            System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "Current State of Printer: "+ toString() + TextFormat.endColor());
        }
        else if(isTaskOver()){
            System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTER) + TextFormat.PRINTER + "All Printing Over! No Need To Replace Toner. "+ TextFormat.endColor());
        }
    }

    /*method to check the state of the students thread group
    if all the threads in that group are terminated, isTaskOver will be set to True
    This is to avoid any dead-locks that the technician threads could face by
    staying in the waiting state after all documents are printed
    This method ensure program termination*/
    private boolean isTaskOver(){
        return this.studentThreadGroup.activeCount()==0;
    }
}