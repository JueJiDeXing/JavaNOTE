package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.interpreter解释器;


public class Plus extends AbstractExpression {

    //+号左边的表达式
    private AbstractExpression left;
    //+号右边的表达式
    private AbstractExpression right;

    public Plus(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret(Context context) {
        //将左边表达式的结果和右边表达式的结果进行相加
        return left.interpret(context) + right.interpret(context);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }
}
