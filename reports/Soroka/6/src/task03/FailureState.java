class FailureState extends State
{
    private int mNumUnprintedPapers;

    FailureState(Printer printer, int numUnprintedPapers) {
        super(printer);
        this.setNumUnprintedPapers(numUnprintedPapers);
    }

    public void setNumUnprintedPapers(int numUnprintedPapers) {
        this.mNumUnprintedPapers = numUnprintedPapers;
    }

    public int getNumUnprintedPapers() {
        return this.mNumUnprintedPapers;
    }

    @Override
    public String onPrint(int numPaper)
    {
        if(this.mPrinter.getPaintVolume() - 5 >= 0
                && this.mPrinter.getNumPapers() > 0) {
            this.mPrinter.changeState(new PrintState(this.mPrinter));
            this.mPrinter.print(this.mNumUnprintedPapers + numPaper);
            return "Состояние ожидания --> Этап печати\n";
        }
        else {
            if(this.mPrinter.getPaintVolume() - 5 < 0) {
                return "Остальная бумага не может быть распечатана. Недостаточно краски в картридже.\nСостояние сбоя.\n";
            }
            if(this.mPrinter.getNumPapers() <= 0) {
                return "Остальная бумага не может быть распечатана. Недостаточно бумаги.\nСостояние сбоя.\n";
            }
        }
        return "Состояние сбоя\n";
    }

    @Override
    public String onLoadPaper(int numPaper) {
        if(this.mPrinter.getPaintVolume() - 5 >= 0) {
            this.mPrinter.changeState(new WaitState(this.mPrinter));
            return "Бумага была загружена. Количество чернил достаточно.\n Состояние сбоя --> Этап ожидания.\n";
        }
        else {
            return "Бумага была загружена. Недостаточное количество чернил.\nСостояние сбоя.\n";
        }
    }

    @Override
    public String onExtractClampingPaper() {
        this.mPrinter.changeState(new WaitState(this.mPrinter));
        return "Бумага извлечена.\nСостояние сбоя.\n";
    }

    @Override
    public String onRefilleCartridge() {
        this.mPrinter.setPaintVolume(100);
        if(this.mPrinter.getNumPapers() > 0) {
            this.mPrinter.changeState(new WaitState(this.mPrinter));
            return "Картридж заправлен. Достаточное количество бумаги.\nСостояние сбоя --> Этап ожидания.\n";
        }
        else {
            return "Картридж заправлен. Недостаточное количество бумаги.\nСостояние сбоя.\n";
        }
    }
}