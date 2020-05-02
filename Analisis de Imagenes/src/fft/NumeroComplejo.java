package fft;
/*
Hector abril 2020 
*/
public class NumeroComplejo {

    private  double real;
    private  double imaginaria;

    public NumeroComplejo(double real, double imaginaria) {
        this.real = real;
        this.imaginaria = imaginaria;
    }
    public NumeroComplejo(NumeroComplejo complejo){
        this(complejo.getParteReal(),complejo.getParteImaginaria());
    }

    public double getParteReal() {
        return real;
    }

    public double getParteImaginaria() {
        return imaginaria;
    }

    public NumeroComplejo sumar(NumeroComplejo segundo) {
        //obtiene la referencia al objeto
        NumeroComplejo primero = this;
        double real = primero.real + segundo.real;
        double imag = primero.imaginaria + segundo.imaginaria;
        return new NumeroComplejo(real, imag);
    }

    public NumeroComplejo producto(double multiplicador) {
        return new NumeroComplejo(real * multiplicador, imaginaria * multiplicador);
    }


}