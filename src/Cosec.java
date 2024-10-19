public class Cosec extends Expression {

    private final Expression arg;

    public Cosec(Expression arg) {
        this.arg = arg;
    }

    @Override
    public Expression diff(String variable) {
        return new Product(new Constant(-1), new Cosec(arg));
    }

    @Override
    public double eval(double... values) {
        return 1/Math.sin(arg.eval(values));
    }

    @Override
    public String toString() {
        return STR."csc(\{arg.toString()})";
    }
}
