import java.util.Random;

class PrintState extends State
{
    PrintState(Printer printer) {
        super(printer);
    }

    @Override
    public String onPrint(int numPaper)
    {
        Random r = new Random();
        double probality = 0 + (100 - 0) * r.nextDouble();

        if(probality <= this.mPrinter.getPinchingProbability() + 20 &&
                probality >= this.mPrinter.getPinchingProbability() - 20) {
            this.mPrinter.changeState(new ClampingState(this.mPrinter));
            return "Бумага не может быть распечатана. Подтверждено.\nЭтап печати --> Этап зажима бумаги.\n";
        }

        for(int i = 0; i < numPaper; ++i) {
            //5% = 1 page
            int numPapersInPrinter = this.mPrinter.getNumPapers();
            double paintVolume = this.mPrinter.getPaintVolume();
            if(paintVolume - 5 >= 0 && numPapersInPrinter > 0) {
                this.mPrinter.setPaintVolume(paintVolume - 5);
                this.mPrinter.setNumPapers(numPapersInPrinter - 1);
            }
            else {
                this.mPrinter.changeState(new FailureState(this.mPrinter, numPaper - i));
                if(paintVolume - 5 < 0) {
                    return "Остальная бумага не может быть распечатана. Недостаточно краски в картридже.\nЭтап печати --> Состояние сбоя.\n";
                }
                if(numPapersInPrinter <= 0) {
                    return "Остальная бумага не может быть распечатана. Недостаточное количество бумаги.\nЭтап печати --> Состояние сбоя.\n";
                }
            }
        }

        this.mPrinter.changeState(new WaitState(this.mPrinter));
        return "Бумага напечатана.\nЭтап печати --> Состояние ожижания.\n";
    }

    @Override
    public String onLoadPaper(int numPaper) {
        return "Бумага не может быть загружена.\nЭтап печати.\n";
    }

    @Override
    public String onExtractClampingPaper() {
        return "Бумагу невозможно зажать.\nЭтап печати.\n";
    }

    @Override
    public String onRefilleCartridge() {
        return "Картридж не может быть заправлен.\nЭтап печати.\n";
    }
}