package de.unitrier.st.uap.w21.tram;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.Scanner;


public class AbstractMachine {

    static Logger logger = Logger.getLogger(AbstractMachine.class);

    public boolean verbose = false;

    final Instruction[] i;
    int pc = 0, pp = 0, fp = 0, top = -1;
    ArrayList<Integer> stack = new ArrayList<>();

    public AbstractMachine(Instruction[] i) {
        this.i = i;
        PropertyConfigurator.configure("src/log4j.properties");

        // optional for examples 1 and 2
//        if (top == 1) {
//            stack.add(0);
//            stack.add(0);
//        }
//        if (top == 0) {
//            stack.add(0);
//        }
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public String interpret() {
        while (pc >= 0) {
            switch (i[pc].getOpcode()) {
                case Instruction.CONST:
                    conzt(i[pc].getArg1());
                    break;
                case Instruction.LOAD:
                    if (i[pc].getArg2() == null) {
                        load(i[pc].getArg1());
                        break;
                    } else {
                        expandedLoad(i[pc].getArg1(), i[pc].getArg2());
                        break;
                    }
                case Instruction.STORE:
                    if (i[pc].getArg2() == null) {
                        store(i[pc].getArg1());
                        break;
                    } else {
                        expandedStore(i[pc].getArg1(), i[pc].getArg2());
                        break;
                    }
                case Instruction.ADD:
                    add();
                    break;
                case Instruction.SUB:
                    sub();
                    break;
                case Instruction.MUL:
                    mul();
                    break;
                case Instruction.DIV:
                    div();
                    break;
                case Instruction.LT:
                    lt();
                    break;
                case Instruction.GT:
                    gt();
                    break;
                case Instruction.EQ:
                    eq();
                    break;
                case Instruction.NEQ:
                    neq();
                    break;
                case Instruction.IFZERO:
                    ifzero(i[pc].getArg1());
                    break;
                case Instruction.GOTO:
                    gotou(i[pc].getArg1());
                    break;
                case Instruction.HALT:
                    halt();
                    break;
                case Instruction.NOP:
                    nop();
                    break;
                case Instruction.INVOKE:
                    invoke(i[pc].getArg1(), i[pc].getArg2(), i[pc].getArg3());
                    break;
                case Instruction.RETURN:
                    returnn();
                    break;
                case Instruction.POP:
                    pop();
                    break;
            }
        }
        if (verbose){
            System.out.println("All computing steps are written into a file");
        }
        return "result:"+stack.get(top);
    }

    private void conzt(int p1) {
        stack.add(top + 1, p1);
        top++;
        pc++;
        printout("conzt");
    }

    private void load(int p1) {
        if (stack.size() < top) {
            int toptemp = top;
            while (toptemp >= 0) {
                stack.add(0);
                toptemp--;
            }
        }
        while (stack.size() <= pp + p1 || stack.size() <= top + 1)
            stack.add(0);
        stack.set(top + 1, stack.get(pp + p1));
        top++;
        pc++;
        printout("load");
    }

    private void expandedLoad(int p1, int p2) {
        if (stack.size() < top) {
            int toptemp = top;
            while (toptemp >= 0) {
                stack.add(0);
                toptemp--;
            }
        }
        while (stack.size() <= pp + p1 || stack.size() <= top + 1)
            stack.add(0);
        stack.set(top + 1, stack.get(spp(p2, pp, fp) + p1));
        top++;
        pc++;
        printout("load");
    }

    private void store(int p1) {
        while (stack.size() <= pp + p1 || stack.size() <= top)
            stack.add(0);
        stack.set(pp + p1, stack.get(top));
        top--;
        pc++;
        printout("store");
    }

    private void expandedStore(int p1, int p2) {
        while (stack.size() <= pp + p1 || stack.size() <= top)
            stack.add(0);
        stack.set(spp(p2, pp, fp) + p1, stack.get(top));
        top--;
        pc++;
        printout("store");
    }

    private void add() {
        stack.set(top - 1, stack.get(top - 1) + stack.get(top));
        top--;
        pc++;
        printout("add");
    }

    private void sub() {
        stack.set(top - 1, stack.get(top - 1) - stack.get(top));
        top--;
        pc++;
        printout("sub");
    }

    private void mul() {
        stack.set(top - 1, stack.get(top - 1) * stack.get(top));
        top--;
        pc++;
        printout("mul");
    }

    public void printout(String op) {
        if (verbose) {
            String[] stackStrings = new String[top+1]; //optional output with full stack new String[stack.size()]
            for (int i = 0; i < stackStrings.length; i++) {
                if (i <= top) {
                   stackStrings[i] = stack.get(i).toString();
               }
                //optional output with full stack
/*                else {
                    stackStrings[i] = "(" + stack.get(i).toString() + ")";
                }*/
            }
            logger.info("top=" + top + ", " + "pc=" + pc + ", " + "pp=" + pp + ", " + "fp=" + fp + ", [" + String.join(", ", stackStrings) + "]" + (op == null ? "" : (" // " + op)));
        }
    }

    private void div() {
        stack.set(top - 1, stack.get(top - 1) / stack.get(top));
        top--;
        pc++;
        printout("div");
    }

    private void lt() {
        if (stack.get(top - 1) < stack.get(top)) {
            stack.set(top - 1, 1);
        } else {
            stack.set(top - 1, 0);
        }
        top--;
        pc++;
        printout("lt");
    }

    private void gt() {
        if (stack.get(top - 1) > stack.get(top)) {
            stack.set(top - 1, 1);
        } else {
            stack.set(top - 1, 0);
        }
        top--;
        pc++;
        printout("gt");
    }

    private void eq() {
        if (stack.get(top - 1) == stack.get(top)) {
            stack.set(top - 1, 1);
        } else {
            stack.set(top - 1, 0);
        }
        top--;
        pc++;
        printout("eq");
    }

    private void neq() {
        if (stack.get(top - 1) != stack.get(top)) {
            stack.set(top - 1, 1);
        } else {
            stack.set(top - 1, 0);
        }
        top--;
        pc++;
        printout("neq");
    }

    private void ifzero(int p1) {
        if (stack.get(top) == 0) {
            pc = p1;
        } else {
            pc++;
        }
        top--;
        printout("ifzero");
    }

    private void gotou(int p1) {
        pc = p1;
        printout("gotou");
    }

    private void halt() {
        pc = -1;
        printout("halt");
    }

    private void nop() {
        pc++;
        printout("nop");
    }

    private void invoke(int p1, int p2, int p3) {
        while (stack.size() <= top + 5)
            stack.add(0);
        stack.set(top + 1, pc + 1);
        stack.set(top + 2, pp);
        stack.set(top + 3, fp);
        stack.set(top + 4, spp(p3, pp, fp));
        stack.set(top + 5, sfp(p3, pp, fp));
        pp = top - p1 + 1;
        fp = top + 1;
        top = top + 5;
        pc = p2;
        printout("invoke");
    }

    private int spp(int p1, int p2, int p3) {
        if (p1 == 0)
            return p2;
        else
            return spp(p1 - 1, stack.get(p3 + 3), stack.get(p3 + 4));
    }

    private int sfp(int p1, int p2, int p3) {
        if (p1 == 0)
            return p3;
        else
            return sfp(p1 - 1, stack.get(p3 + 3), stack.get(p3 + 4));
    }

    private void returnn() {
        int res = stack.get(top);
        top = pp;
        pc = stack.get(fp);
        pp = stack.get(fp + 1);
        fp = stack.get(fp + 2);
        stack.set(top, res);
        printout("returnn");
    }

    private void pop() {
        top--;
        pc++;
        printout("pop");
    }

}
