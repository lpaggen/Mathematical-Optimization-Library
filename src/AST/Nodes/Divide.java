package AST.Nodes;

import Interpreter.Tokenizer.Token;
import Util.LookupTable;

public class Divide extends MathExpression {
    private final MathExpression num, denom;

    public Divide(MathExpression num, MathExpression denom) {
        this.num = num;
        this.denom = denom;
    }

    @Override
    public Object evaluate(LookupTable<String, Token> env) {
        Object numResult = num.evaluate(env);
        Object denomResult = denom.evaluate(env);

        if (numResult instanceof Number && denomResult instanceof Number) {
            return ((Number) numResult).doubleValue() / ((Number) denomResult).doubleValue();
        }
        if (numResult instanceof MathExpression && denomResult instanceof MathExpression) {
            return new Multiply((MathExpression) numResult, (MathExpression) denomResult);
        }
        if (numResult instanceof MathExpression) {
            return new Multiply((MathExpression) numResult, denom);
        }
        if (denomResult instanceof MathExpression) {
            return new Multiply(num, (MathExpression) denomResult);
        }
        throw new RuntimeException("Operation not supported");
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public MathExpression derive(String var) {
        return new Divide(new Add(new Multiply(num.derive(var), denom), new Multiply(num, denom.derive(var))), new Power(denom, new Constant(2)));
    }

    @Override
    public MathExpression substitute(String... args) {
        return new Divide(num.substitute(args), denom.substitute(args));
    }
}
