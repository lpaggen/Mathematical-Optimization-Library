package AST.Nodes;

import Interpreter.Tokenizer.Token;
import Util.Environment;

public class Tangent extends MathExpression {
    private final MathExpression arg;

    public Tangent(MathExpression arg) {
        this.arg = arg;
    }

    @Override
    public MathExpression derive(String variable) {
        return new Multiply(new Sec(arg), arg.derive(variable));
    }

    @Override
    public MathExpression substitute(String... args) {
        return new Tangent(arg.substitute(args));
    }

    @Override
    public Object evaluate(Environment<String, Token> env) {
        return Math.tan((double) arg.evaluate(env));
    }

    @Override
    public String toString() {
        return "tan(" + arg.toString() + ")";
    }
}
