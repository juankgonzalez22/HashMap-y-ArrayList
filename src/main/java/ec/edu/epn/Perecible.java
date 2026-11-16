package ec.edu.epn;

public class Perecible extends Producto{

    private int diasParaVencer;


    //Recibir todos los parametros del padre mas uno nuevo que es propio de la hija

    public Perecible(String codigo, String nombre, double precio, int stock, int diasParaVencer) {
        super(codigo, nombre, precio, stock);
        this.diasParaVencer = diasParaVencer;
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }

    public void setDiasParaVencer(int diasParaVencer) {
        this.diasParaVencer = diasParaVencer;
    }
    //Sobrrescribir de acuerdo a su regla
    //parte del valor base y si faltan 3 dias o menos ,
    // lo reduce a la mitad
    @Override
    public double calcularValor() {
        double valor = super.calcularValor();
        if(diasParaVencer <=3){
            valor = valor *0.5;
        }
        return valor;
    }
    //Regla propia: se retira cuando ya se vencio(0 dias)
    @Override
    public boolean debeRetirarse() {
        return diasParaVencer == 0;
    }
    //Agrega los dias a la descripción del padre

    @Override
    public String describir() {
        return super.describir()+" | vence en: "+ diasParaVencer + " dias" ;
    }
}
