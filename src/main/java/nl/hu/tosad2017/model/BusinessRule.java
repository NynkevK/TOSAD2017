package nl.hu.tosad2017.model;

public abstract class BusinessRule {
    protected int code;
    protected int name;
    protected int comment;
    protected int example;
    protected int isStatic;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
