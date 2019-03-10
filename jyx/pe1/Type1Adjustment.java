public class Type1Adjustment extends SalaryAdjust {
    public double adjust(double raise) {
        if(raise > 1.1) return 10;
        if(raise < 1) return 0;
        return (raise - 1) * 100;
    }
}
