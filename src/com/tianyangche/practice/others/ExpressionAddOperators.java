package com.tianyangche.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyangche on 4/20/16.
 */
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.equals("")) {
            return res;
        }
        addOperatorsHelper(res, num, 0, "", 0, 0, target);
        return res;
    }

    private void addOperatorsHelper(List<String> res, String num, int pos, String expression, int eval, int prevEval, int target) {
        if (pos == num.length()) {
            if (eval == target) {
                res.add(expression);
            }
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') {
                break;
            }
            int currNum = Integer.parseInt(num.substring(pos, i + 1));
            if (pos == 0) {
                addOperatorsHelper(res, num, i + 1, expression + currNum, eval + currNum, currNum, target);
            } else {
                addOperatorsHelper(res, num, i + 1, expression + "+" + currNum, eval + currNum, currNum, target);
                addOperatorsHelper(res, num, i + 1, expression + "-" + currNum, eval - currNum, -currNum, target);
                addOperatorsHelper(res, num, i + 1, expression + "*" + currNum, eval - prevEval + currNum * prevEval, prevEval * currNum, target);

            }
        }
    }

    public static void main(String[] args) {
        ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();
        String num = "123";
        int target = 6;
        System.out.println(expressionAddOperators.addOperators(num, target));
    }
}
