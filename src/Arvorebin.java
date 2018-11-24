
import java.lang.reflect.Method;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kaian
 */
public class Arvorebin<X extends Comparable> {

    private class No {

        private No left,
                right;
        private X info;

        public No(No left, No right, X info) {
            this.left = left;
            this.right = right;
            this.info = info;
        }

        public No getLeft() {
            return left;
        }

        public void setLeft(No left) {
            this.left = left;
        }

        public No getRight() {
            return right;
        }

        public void setRight(No right) {
            this.right = right;
        }

        public X getInfo() {
            return info;
        }

        public void setInfo(X info) {
            this.info = info;
        }
    }
    private No root;

    public int getQtd() {
        return this.getQtd(this.root.getLeft()) + this.getQtd(root.getRight());
    }

    public int getQtd(No root) {
        if (root == null) {
            return 0;
        }
        return 1 + this.getQtd(root);
    }

    private X meuCloneDeX(X x) throws Exception {
        X ret = null;
        try {
            Class<?> classe = x.getClass();
            Class<?>[] tipoParametroFormal = null;
            Method metodo = classe.getMethod("clone", tipoParametroFormal);
            Object[] parametroreal = null;
            ret = (X) metodo.invoke(x, parametroreal);
        } catch (Exception erro) {
        }
        return ret;
    }
    public void insira(X i) throws Exception {
        if (i == null) {
            throw new Exception("Info is empty");
        }
        X info = i instanceof Cloneable? this.meuCloneDeX(i) : i;
        this.insira(this.root, info);
    }
    private void insira(No atual, X i) throws Exception {
        if (this.root != null) {
            int comp = i.compareTo(this.root.getInfo());
            if (comp < 0) {
                if (atual.getLeft() != null) {
                    insira(atual.getLeft(), i);
                } else {
                    atual.setLeft(new No(null, null, i));
                }
            }
            if (comp > 0) {
                if(atual.getRight()!=null){
                    insira(atual.getRight(),i);
                }
                else{
                    atual.setRight(new No (null,null,i));
                }
            }
            if (comp == 0) {
                throw new Exception("Info repetida");
            }
        } else {
            this.root = new No(null, null, i);
        }
    }
    public void remova(X i)throws Exception{
        if(i == null)
            throw new Exception("info vaiza");
        if(this.root == null)
            throw new Exception("Arvore vazia");
        try{
            remova(this.root,i);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void remova(No atual,X i){
        int comp = i.compareTo(this.root.getInfo());
        if(comp<0){
          if(!atual.getLeft().getInfo().equals(i))
              remova(atual.getLeft(),i);
          else
              atual.setLeft(null);
        }
        if(comp>0){
            if(!atual.getRight().getInfo().equals(i))
                remova(atual.getRight(),i);
            else
                atual.setRight(null);
        }
    }
    public void removaMenor() throws Exception {
        if(this.root==null)
            throw new Exception("info vazia");
        else{
            removaMenor(this.root);
        }
    }
    private void removaMenor(No atual){
        if(atual.getLeft()==null){
            atual.setLeft(null);
        }
        else{
            removaMenor(atual.getLeft());
        }
    }
    public void removaMaior()throws Exception{
        if(this.root == null)
            throw new Exception("Arvore vazia");
        else{
            removaMaior(this.root);
        }
    }
    private void removaMaior(No atual){
        if(atual.getRight().getRight()!=null){
            removaMaior(atual.getRight());
        }
        else{
            atual.setLeft(null);
        }
            
    }

    public String toString() {
        return toString(this.root);
    }

    private String toString(No i) {
        if (i != null) {
            return i.getInfo() + "," + toString(i.getLeft()) + toString(i.getRight());
        }
        return "";
    }
}
