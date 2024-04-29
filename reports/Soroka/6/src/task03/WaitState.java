class WaitState extends State
{
    WaitState(Printer printer) {
        super(printer);
    }

    @Override
    public String onPrint(int numPaper) {
        this.mPrinter.changeState(new PrintState(this.mPrinter));
        return "Состояние ожидания --> Этап печати.\n";
    }

    @Override
    public String onLoadPaper(int numPaper) {
        return "Бумага загружена.\nСостояние ожидания.\n";
    }

    @Override
    public String onExtractClampingPaper() {
        return "Бумага извлечена.\nСостояние ожидания.\n";
    }

    @Override
    public String onRefilleCartridge() {
        this.mPrinter.setPaintVolume(100);
        return "Картридж был заправлен.\nСостояние ожидания.\n";
    }
}