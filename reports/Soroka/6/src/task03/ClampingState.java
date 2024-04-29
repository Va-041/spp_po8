class ClampingState extends State
{
    ClampingState(Printer printer) {
        super(printer);
    }

    @Override
    public String onPrint(int numPaper) {
        return "Бумага не может быть распечатана.\nЭтап захвата бумаги.\n";
    }

    @Override
    public String onLoadPaper(int numPaper) {
        return "Бумага не может быть загружена.\nЭтап захвата бумаги.\n";
    }

    @Override
    public String onExtractClampingPaper() {
        this.mPrinter.changeState(new WaitState(this.mPrinter));
        return "Бумага извлечена.\nЭтап захвата бумаги --> Этап ожидания.\n";
    }

    @Override
    public String onRefilleCartridge() {
        this.mPrinter.setPaintVolume(100);
        return "Картридж заправлен.\nЭтап захвата бумаги.\n";
    }
}