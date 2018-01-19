package nl.hu.tosad2017.model.model;

public abstract class BusinessRule {
    protected String code;
    protected int name;
    protected int comment;
    protected int example;
    protected int isStatic;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
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
