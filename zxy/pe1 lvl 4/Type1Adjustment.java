public class Type1Adjustment implements SalaryAdjust{

    public double adjust(double raise) {
        double rate = raise -1;
        
        if (rate > 0.1){
            return 1.1;
        } else if (rate < 0.0) {
            return 1.0;
        } else {
            return raise;
        }
    }
}
