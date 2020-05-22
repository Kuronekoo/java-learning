package designMode.prototype;

/**
 * @description:
 * @author: shenchao
 * @create: 2020-05-12 17:13
 **/
public class CloneablePig {
    public static void main(String[] args) {
        CloneablePig pig = new CloneablePig("23kg", "40cm", "new Type");
        CloneablePig clonePig = pig.clone();
        System.out.println(pig == clonePig);

    }
    private String weight;
    private String hight;
    private String type;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CloneablePig(String weight, String hight, String type) {
        this.weight = weight;
        this.hight = hight;
        this.type = type;
    }

    @Override
    protected CloneablePig clone(){
        return new CloneablePig(this.getWeight(),this.getHight(),this.getType());
    }

}
