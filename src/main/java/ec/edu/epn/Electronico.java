package ec.edu.epn;

public class Electronico extends Producto{
    private int mesesEnBodega;

    public Electronico(String codigo, String nombre, double precio, int stock, int mesesEnBodega) {
        super(codigo, nombre, precio, stock);
        setMesesEnBodega(mesesEnBodega);
    }

    public int getMesesEnBodega() {
        return mesesEnBodega;
    }
    //No acepta meses negativos
    public void setMesesEnBodega(int mesesEnBodega) {
        if(mesesEnBodega >=0){
            this.mesesEnBodega = mesesEnBodega;
        }
    }

    //Sobreescritura del metodo del pare
    //Vamos a partir del valor base que calcula el padre
    //Aumentamos logica adicional: si lleva mas de 6 meses le resta un 20%

    @Override
    public double calcularValor() {
        double valor = super.calcularValor();//valor base;
        if(mesesEnBodega > 6){
            valor = valor * 0.80;
        }
        return valor;
    }
    //Regla propia: se retira por obsoleto a partir de los 12 meses
    @Override
    public boolean debeRetirarse() {
        return mesesEnBodega>=12;
    }

    @Override
    public String describir() {
        return super.describir()+ " |"+mesesEnBodega +" meses en bodega";
    }
}
