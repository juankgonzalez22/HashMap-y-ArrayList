package ec.edu.epn;

public class Producto {

    private String codigo;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        setPrecio(precio);
        setStock(stock);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if(stock>0){
            this.stock = stock;
        }
    }

    //Valor base del producto en inventario
    //Las clases hijas partan de este calculo y apliquen sus propios ajustes.
    public double calcularValor(){
        return precio*stock;
    }
    //Por defecto un producto no se retira. Para controlar la dada de baja de productos
    //Cada clase hija sobreescribirá este metodo con su propia regla
    public boolean debeRetirarse(){
        return false;
    }

    public String describir(){
        return codigo+"| "+ nombre+" | $"+ precio + " x "+stock;
    }
}
