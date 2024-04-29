class ThirdLevel extends Decorator
{
    public ThirdLevel(Level level) {
        super(level);
        this.mLevelName = "[3] -> " + super.getLevel();
    }

    @Override
    public String getLevel() {
        return this.mLevelName;
    }

    @Override
    public String buyBook(String name) {
        return "Вы добавили книгу [ " + name + " ] в корзину";
    }

    @Override
    public String addToFavourites(String name) {
        return "Вы добавили книгу [ " + name + " ] в Избранное";
    }

    @Override
    public String leaveMark(String name, String mark) {
        return "Вы поставили оценку [" + mark + "] для книги [ " + name + " ]";
    }
}