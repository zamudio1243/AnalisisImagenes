package analisisespacial;
/*
Hector marzo 2020 
*/

public  class Umbral {
    public static int metodoIterativo(double[] histograma){

        int ui = calcularUmbralInicial(histograma);
        int uNuevo=0;
        //System.out.println(ui);
        do{
            uNuevo = ui;
            ui = reajustarUmbral(ui,histograma);
            //System.out.println(ui);
        }while(uNuevo!=ui);

        return ui;

    }


    private static int calcularUmbralInicial(double[] histograma) {
        int numPixels = 0;
        int suma = 0;
        for(int x=0;x<histograma.length;x++){
            numPixels+=histograma[x];
            suma+=histograma[x]*x;
        }
        return (int)(suma/numPixels);
    }


    private static int reajustarUmbral(int ui, double[] histograma) {
        int u1,u2;
        int a1=0,a2=0,n1=0,n2=0;
        a1+=histograma[0];
        for(int x=1;x<ui;x++){
            a1+=histograma[x]*x;
            n1+=histograma[x];
        }

        for(int y=ui;y<=255;y++){
            a2+=histograma[y]*y;
            n2+=histograma[y];
        }
        if (n1==0 || n2==0) return 0;
        u1 = a1/n1;
        u2 = a2/n2;
        return (int)((u1+u2)/2);
    }

    public static double[] metodoOtsu(double[] h){

        //Vector de probabilidad acumulada
        double[] omega= new double[h.length];
        //vector de media acumulada
        double[] mean= new double[h.length];
        //Partiendo del histograma normalizado se calcula la probabilidad
        //acumulada(omega) y la media acumulada(mean)
        omega[0] = h[0];
        for (int i = 1; i < h.length; i++) {
            omega[i] = omega[i-1]+ h[i];
            mean[i] = mean[i-1] + (i*h[i]);
        }

        return  mean;
    }
}
