public class Variavel{
    public static final int INTEIRO = 1;
    public static final int REAL    = 2;
    public static final int STRING  = 3;
    
    private String id;
    private int    tipo;
    
    public Variavel(String id, int tipo){
        this.id   = id;
        this.tipo = tipo;
    }
    
    public Variavel(){
        this("",-1);
    }
    
    public void setId(String id){
        this.id = id;
    }
    public void setTipo(int tipo){
        this.tipo = tipo;
    }
    public String getId(){
        return this.id;
    }
    public int getTipo(){
        return this.tipo;
    }
    
    public String toString(){
        return "VAR: "+id+" ("+tipo+")";
    }
}

int a(){
   int i = 0;
   b();
   print(i);
}

int b(){
    b = 1;
    print(i);
}
}