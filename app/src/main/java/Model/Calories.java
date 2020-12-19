package Model;

public class Calories {
    private int id;
    private String fruitName;
    private String calorie;

    public Calories() {
    }

    public Calories(int id, String fruitName, String calorie) {
        this.id = id;
        this.fruitName = fruitName;
        this.calorie = calorie;
    }

    public Calories(String fruitName, String calorie) {
        this.fruitName = fruitName;
        this.calorie = calorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }
}
