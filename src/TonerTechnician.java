/** *********************************************************************
 * Author:    Warsha Kiringoda
 * UoW id:    w1697817
 * IIT id:    2017366
 ************************************************************************ */
import utils.*;
import utils.TextFormat;

/*Thread*/
public class TonerTechnician extends Thread {
    private ServicePrinter printer;

    public TonerTechnician(String name, ThreadGroup threadGroup, ServicePrinter printer){
        super(threadGroup, name);
        this.printer = printer;
    }

    @Override
    public void run() {
        for(int i=0; i<3; i++){
            System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.TONER_TECHNICIAN) + TextFormat.TONER_TECHNICIAN +" Requested to Replace Cartridge (Attempt "+ (i+1) + ")" + TextFormat.endColor());
            printer.replaceTonerCartridge();
            //sleep random milliseconds
            try{
                sleep(MethodService.generateRandomSleepDuration());
            }catch (InterruptedException e){}
        }
        //finish attempting to Toner replace 3 times
        System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.TONER_TECHNICIAN) + TextFormat.TONER_TECHNICIAN +" Finished 3 Attempts" + TextFormat.endColor());
    }
}
