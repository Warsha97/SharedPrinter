/** *********************************************************************
 * Author:    Warsha Kiringoda
 * UoW id:    w1697817
 * IIT id:    2017366
 ************************************************************************ */
import utils.MethodService;
import utils.TextFormat;

/*Thread*/
public class PaperTechnician extends Thread{
    private ServicePrinter printer;

    public PaperTechnician(String name, ThreadGroup threadGroup, ServicePrinter printer){
        super(threadGroup, name);
        this.printer = printer;
    }

    @Override
    public void run() {
    for(int i = 0; i<3; i++){
        System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PAPER_TECHNICIAN) + TextFormat.PAPER_TECHNICIAN +" Requested to Refill (Attempt "+ (i+1) + ")" + TextFormat.endColor());
        printer.refillPaper();
       //sleep random milliseconds
       try{
           sleep(MethodService.generateRandomSleepDuration());
       }catch (InterruptedException e){}
    }

   //finish attempting to refill 3 times
        System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PAPER_TECHNICIAN) + TextFormat.PAPER_TECHNICIAN +" Finished 3 Attempts" + TextFormat.endColor());


    }
}
