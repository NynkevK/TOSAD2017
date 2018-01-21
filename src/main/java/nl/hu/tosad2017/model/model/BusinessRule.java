package nl.hu.tosad2017.model.model;

public abstract class BusinessRule {
    protected int code;
    protected String name;
    protected String comment;
    protected int example;
    protected int isStatic;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getExample() {
        return example;
    }

    public void setExample(int example) {
        this.example = example;
    }

    public int getIsStatic() {
        return isStatic;
    }

    public void setIsStatic(int isStatic) {
        this.isStatic = isStatic;
    }
}
