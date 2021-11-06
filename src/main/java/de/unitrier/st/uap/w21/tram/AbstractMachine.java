package de.unitrier.st.uap.w21.tram;

import java.util.ArrayList;

public class AbstractMachine {

    final Instruction[] i;
    int pc = 0, pp = -1, fp = -1, top = -1;
    ArrayList<Integer> stack = new ArrayList<>();

    public AbstractMachine(Instruction[] i) {
        this.i = i;
    }

    public String interpret() {

        while(pc >= 0) {
            switch (i[pc].getOpcode()) {
                case Instruction.CONST:
                    conzt(i[pc].getArg1());
            }
        }




        return "GGG";
    }

    private void conzt(int p1) {
        //if(stack.size() < top)
        //    stack.add(p1);
        //else
        stack.add(0);
        stack.set(top+1, p1);
        top++;
        pc++;
    }
    private void load(int p1) {
        //if(stack.size() < top)
        //    stack.add(stack.get(pp+p1));
        //else
        stack.add(0);
        stack.set(top+1, stack.get(pp+p1));
        top++;
        pc++;
    }
    private void store(int p1) {
        stack.set(pp+p1, stack.get(top));
        top--;
        pc++;
    }
    private void add() {
        stack.set(top-1, stack.get(top-1)+stack.get(top));
        top--;
        pc++;
    }
    private void sub() {
        stack.set(top-1, stack.get(top-1)-stack.get(top));
        top--;
        pc++;
    }
    private void mul() {
        stack.set(top-1, stack.get(top-1)*stack.get(top));
        top--;
        pc++;
    }
    private void div() {
        stack.set(top-1, stack.get(top-1)/stack.get(top));
        top--;
        pc++;
    }
    private void lt() {
        if(stack.get(top-1) < stack.get(top)) {
           stack.set(top-1, 1);
        } else{
            stack.set(top-1, 0);
        }
        top--;
        pc++;
    }

    private void gt() {
        if(stack.get(top-1) > stack.get(top)) {
            stack.set(top-1, 1);
        } else{
            stack.set(top-1, 0);
        }
        top--;
        pc++;
    }

    private void eq() {
        if(stack.get(top-1) == stack.get(top)) {
            stack.set(top-1, 1);
        } else{
            stack.set(top-1, 0);
        }
        top--;
        pc++;
    }

    private void neq() {
        if(stack.get(top-1) != stack.get(top)) {
            stack.set(top-1, 1);
        } else{
            stack.set(top-1, 0);
        }
        top--;
        pc++;
    }

    private void ifzero(int p1) {
        if (stack.get(top)==0){
            pc = p1;
        } else{
            pc++;
        }
        top--;
    }

    private void gotou(int p1) {
        pc = p1;
    }

    private void halt() {
        pc = -1;
    }

    private void nop() {
        pc++;
    }

    private void invoke(int p1, int p2, int p3) {
        stack.add(0);
        stack.add(0);
        stack.add(0);
        stack.add(0);
        stack.add(0);
        stack.set(top+1, pc+1);
        stack.set(top+2, pp);
        stack.set(top+3, fp);
        stack.set(top+4, spp(p3, pp, fp));
        stack.set(top+5, sfp(p3, pp, fp));
        pp = top-p1+1;
        fp = top + 1;
        top = top + 5;
        pc = p2;
    }

    private int spp(int p1, int p2, int p3) {
        if(p1 == 0)
            return p2;
        else
            return spp(p1-1, stack.get(p3+3), stack.get(p3+4));
    }

    private int sfp(int p1, int p2, int p3) {
        if(p1 == 0)
            return p3;
        else
            return sfp(p1-1, stack.get(p3+3), stack.get(p3+4));
    }

    private void returnn() {
        int res = stack.get(top);
        top = pp;
        pc = stack.get(fp);
        pp = stack.get(fp+1);
        pp = stack.get(fp+2);
        stack.set(top, res);
    }

    private void pop() {
        top--;
        pc++;
    }

    /*
    public final static int CONST = 1;
    public final static int LOAD = 2;
    public final static int STORE = 3;
    public final static int ADD = 4;
    public final static int SUB = 5;
    public final static int MUL = 6;
    public final static int DIV = 7;
    public final static int LT = 8;
    public final static int GT = 9;
    public final static int EQ = 10;
    public final static int NEQ = 11;
    public final static int IFZERO = 12;
    public final static int GOTO = 13;
    public final static int HALT = 14;
    public final static int NOP = 15;
    public final static int INVOKE = 16;
    public final static int RETURN = 17;
    public final static int POP = 18;
    */
























}
