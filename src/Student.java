/** *********************************************************************
 * Author:    Warsha Kiringoda
 * UoW id:    w1697817
 * IIT id:    2017366
 ************************************************************************ */
import java.util.Random;
import utils.*;

/*Thread*/
public class Student extends Thread {
    private Printer printer; //printer interface

    public Student(String name, ThreadGroup threadGroup, Printer printer){
        super(threadGroup, name);
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            Document document = createRandomDocument(i);
            System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.STUDENT) + TextFormat.STUDENT +document.getUserID() + " Requested to Print (" + document.getNumberOfPages() + " pages)"  + TextFormat.endColor());
            printer.printDocument(document);
            //sleep random milliseconds
            try {
                sleep(MethodService.generateRandomSleepDuration());
            } catch (InterruptedException e) {
            }
        }

        //Finished printing all documents
        System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.STUDENT) + TextFormat.STUDENT + this.getName()+ " Printed All Docs." + TextFormat.endColor());
    }


        //create document with random length and name
    private Document createRandomDocument(int docNumber){
        Random rand = new Random();
        int randomNum = rand.nextInt((20 - 1) + 1) + 1;
        String randomName = "cwk"+docNumber;
        Document doc = new Document(this.getName()+"-"+randomName,randomName,randomNum);
        return doc;
    }
}

