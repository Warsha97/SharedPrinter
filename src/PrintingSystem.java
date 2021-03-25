/** *********************************************************************
 * Author:    Warsha Kiringoda
 * UoW id:    w1697817
 * IIT id:    2017366
 ************************************************************************ */
import utils.TextFormat;
import utils.MethodService;

/*execution Class*/
public class PrintingSystem {
    public static void main(String[] args) {

        //create thread group for students
        ThreadGroup students = new ThreadGroup("students");
        System.out.println(MethodService.getTimeNow() + TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "Created Students Thread Group." + TextFormat.endColor());


        //create thread group for technicians
        ThreadGroup technicians = new ThreadGroup("technicians");
        System.out.println(MethodService.getTimeNow() + TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "Created Technicians Thread Group." + TextFormat.endColor());


        //create instance of the Laser printer shared object
        LaserPrinter printer = new LaserPrinter("LP1",001, students);
        System.out.println(MethodService.getTimeNow() + TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "Created instance of " + printer.toString() + TextFormat.endColor());


        //4 student threads
        Student student1 = new Student("Hannah",students,printer);
        Student student2 = new Student("Alex",students,printer);
        Student student3 = new Student("Mary",students,printer);
        Student student4 = new Student("Chris",students,printer);
        System.out.println(MethodService.getTimeNow() + TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "Created 4 instances of Student named "
                           +student1.getName()+","+student2.getName()+","+student3.getName()+","+student4.getName() + TextFormat.endColor());


        //1 paper technician
        PaperTechnician paperTechnician = new PaperTechnician("John",technicians,printer);
        System.out.println(MethodService.getTimeNow() +TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "Created instance of PaperTechnician-"+ paperTechnician.getName() + TextFormat.endColor());


        //1 Toner Technician
        TonerTechnician tonerTechnician = new TonerTechnician("Monica",technicians,printer);
        System.out.println(MethodService.getTimeNow() +TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "Created instance of TonerTechnician-" + tonerTechnician.getName() + TextFormat.endColor());




        //Start all threads
        student1.start();
        System.out.println(MethodService.getTimeNow() + TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + student1.getName() + " Started." + TextFormat.endColor());

        student2.start();
        System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + student2.getName() + " Started." + TextFormat.endColor());

        student3.start();
        System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + student3.getName() + " Started." + TextFormat.endColor());

        student4.start();
        System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + student4.getName() + " Started." + TextFormat.endColor());

        paperTechnician.start();
        System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + paperTechnician.getName() + " Started." + TextFormat.endColor());

        tonerTechnician.start();
        System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + tonerTechnician.getName() + " Started." + TextFormat.endColor());

        try {
            //this makes sure that each thread is terminated, before printing the terminated  message.
            student1.join();
            System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + student1.getName() + " Terminated." + TextFormat.endColor());

            student2.join();
            System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + student2.getName() + " Terminated." + TextFormat.endColor());

            student3.join();
            System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + student3.getName() + " Terminated." + TextFormat.endColor());

            student4.join();
            System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + student4.getName() + " Terminated." + TextFormat.endColor());

            tonerTechnician.join();
            System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + tonerTechnician.getName() + "-Paper Technician Terminated." + TextFormat.endColor());

            paperTechnician.join();
            System.out.println(MethodService.getTimeNow()+TextFormat.addColor(TextFormat.PRINTING_SYSTEM) + TextFormat.PRINTING_SYSTEM + "-" + paperTechnician.getName() + "-Toner Technician Terminated." + TextFormat.endColor());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
