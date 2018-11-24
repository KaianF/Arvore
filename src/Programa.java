public class Programa {
    public static void main(String[] args) {
   Arvorebin numeros = new Arvorebin();
   try{
       
   numeros.insira("1");
   numeros.insira("5");
   numeros.insira("8");
   numeros.insira("2");
   numeros.insira("12");
   numeros.insira("10");
       System.out.println(numeros.toString());
   }
   catch(Exception e){
       System.out.println(e.getMessage());
   }
}
}

