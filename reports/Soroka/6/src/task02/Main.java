class Main
{
    public static void main(String[] args)
    {
        Client cl = new Client();
        System.out.println(cl.getLevel());
        System.out.println(cl.addToFavourites("Крёстный отец"));
        System.out.println(cl.leaveMark("Крёстный отец", "8"));
        System.out.println();

        cl.toSecondLevel();
        System.out.println(cl.getLevel());
        System.out.println(cl.addToFavourites("Иван Дурак и серый волк"));
        System.out.println(cl.leaveMark("Коллизиум мечтаний", "6"));
        System.out.println();

        cl.toThirdLevel();
        System.out.println(cl.getLevel());
        System.out.println(cl.addToFavourites("Благородство из степи"));
        System.out.println(cl.leaveMark("Война и мир. Том 3", "7"));
        System.out.println(cl.buyBook("Большая энциклопедия животных"));
        System.out.println();
    }
}