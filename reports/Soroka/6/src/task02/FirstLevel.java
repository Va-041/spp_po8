class FirstLevel implements Level
{
    private String mLevelName;

    public FirstLevel() {
        this.mLevelName = "[1]";
    }

    @Override
    public String getLevel() {
        return this.mLevelName;
    }

    @Override
    public String buyBook(String name) {
        return name;
    }

    @Override
    public String addToFavourites(String name) {
        return "Вы не можете добавить в Избранное на уровне [1]    :(";
    }

    @Override
    public String leaveMark(String name, String mark) {
        return "Вы не можете поставить оценку на уровне [1]        :(";
    }
}