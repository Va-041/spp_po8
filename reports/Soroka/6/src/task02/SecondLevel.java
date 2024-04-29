class SecondLevel extends Decorator
{
    public SecondLevel(Level level) {
        super(level);
        this.mLevelName = "[2] -> " + super.getLevel();
    }

    @Override
    public String getLevel() {
        return this.mLevelName;
    }

    @Override
    public String buyBook(String name) {
        return this.mLevel.buyBook(name);
    }

    @Override
    public String addToFavourites(String name) {
        return "Вы добавили книгу [ " + name + " ] в Избранное";
    }

    @Override
    public String leaveMark(String name, String mark) {
        return "Вы не можете поставить оценку на уровне [2]        :(";
    }
}