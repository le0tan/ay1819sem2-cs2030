public class Type1Adjustment implements SalaryAdjust {
    public double adjust(double raise) {
        if(raise>0.1) return 0.1;
        else if(raise<0.0) return 0.0;
        else return raise;
    }
}
