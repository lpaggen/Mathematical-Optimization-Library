package AST.Nodes;

public class Log extends Expression {
    private final Expression arg, base;

    public Log(Expression arg, Expression base) {
        // here we do need the base to check if natural or not
        this.arg = arg;
        this.base = base;
    }

    @Override
    public Expression derive(String variable) {
        // need base 2 and any other base sorted
        // actually we only support base 2 and 10 atm, rarely need other values
        if (base.toString().equals("2")) {
            return new Quotient(new NumericNode(1), new Product(arg, new Log(arg, base)));
        } else return new Quotient(new NumericNode(1), new Log(arg, base));
    }

    @Override
    public double evaluate(double... values) {
        if (base.toString().equals("2")) {
            return Math.log(arg.evaluate(values));
        } else return Math.log10(arg.evaluate(values));
    }

    @Override
    public String toString() {
        if (base.toString().equals("2")) {
            return STR."log(\{arg.toString()})";
        } return STR."ln(\{arg.toString()})";
    }
}
