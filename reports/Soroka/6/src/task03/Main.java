import java.util.Random;

class Main
{
    public static void main(String[] args)
    {
        Printer printer = new Printer("Сanon 257B", 30, 100, 0.5);

        int numPaper;
        String result;
        Random r = new Random();

        for(int i = 0; i < 10; ++i)
        {
            numPaper = 0 + (10 - 0) * r.nextInt();
            printer.print(numPaper);

            numPaper = 0 + (10 - 0) * r.nextInt();
            result = printer.print(numPaper);

            if(result.lastIndexOf("Этап захвата бумаги.") != -1) {
                printer.extractClampingPaper();
            }

            if(result.lastIndexOf("Недостаточное количество бумаги") != -1) {
                numPaper = 0 + (10 - 0) * r.nextInt();
                printer.loadPaper(numPaper);
            }

            if(result.lastIndexOf("Недостаточное количество чернил") != -1) {
                printer.refilleCartridge();
            }
        }
    }
}
