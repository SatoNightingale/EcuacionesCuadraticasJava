package test.satoshi.ecuacionesCuadraticas;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class Controller {

    private final Model model;
    private final Builder<Region> viewBuilder;

    public Controller(){
        this.model = new Model();;

        viewBuilder = new ViewBuilder(model, this::Calculate);

        model.okToCalculateProperty().bind(Bindings.createBooleanBinding(this::isDataValid, model.AProperty(), model.BProperty(), model.CProperty()));

        model.DProperty().bind(Bindings.createDoubleBinding(this::CalcDiscriminante, model.AProperty(), model.BProperty(), model.CProperty(), model.okToCalculateProperty()));

        model.x1Property().bind(Bindings.createStringBinding(this::CalcX1, model.DProperty(), model.okToCalculateProperty()));

        model.x2Property().bind(Bindings.createStringBinding(this::CalcX2, model.DProperty(), model.okToCalculateProperty()));

        model.X1EnabledProperty().bind(Bindings.createBooleanBinding(() -> model.getD() >= 0, model.DProperty()));

        model.X2EnabledProperty().bind(Bindings.createBooleanBinding(() -> model.getD() > 0, model.DProperty()));
    }

    private double CalcDiscriminante(){
        if(model.getOkToCalculate()){
            double A = Double.parseDouble(model.getA());
            double B = Double.parseDouble(model.getB());
            double C = Double.parseDouble(model.getC());
            return Math.pow(B, 2) - 4 * A * C;
        }
        return Double.NaN;
    }

    private String CalcX1(){
        if(model.getOkToCalculate() && model.getx1Enabled()){
            double A = Double.parseDouble(model.getA());
            double B = Double.parseDouble(model.getB());
            double D = model.getD();
            return String.valueOf((-B + Math.sqrt(D)) / (2 * A));
        }
        return "";
    }

    private String CalcX2(){
        if(model.getOkToCalculate() && model.getx2Enabled()){
            double A = Double.parseDouble(model.getA());
            double B = Double.parseDouble(model.getB());
            double D = model.getD();
            return String.valueOf((-B - Math.sqrt(D)) / (2 * A));
        }
        return "";
    }



    private void Calculate() {
        double A = Double.parseDouble(model.getA());
        double B = Double.parseDouble(model.getB());
        double C = Double.parseDouble(model.getC());

        double D = Math.pow(B, 2) - 4 * A * C;

        if(D > 0){
            double x1 = (-B + Math.sqrt(D)) / (2 * A);
            double x2 = (-B - Math.sqrt(D)) / (2 * A);

            model.setx1Enabled(true);
            model.setx1(String.valueOf(x1));
            model.setx2Enabled(true);
            model.setx2(String.valueOf(x2));
        }else if(D == 0){
            double x = -B / (2 * A);

            model.setx1Enabled(true);
            model.setx1(String.valueOf(x));
            model.setx2Enabled(false);
        } else {
            model.setx1Enabled(false);
            model.setx2Enabled(false);
        }
    }

    private boolean isDataValid(){
        try {
            double A = Double.parseDouble(model.getA());
            double B = Double.parseDouble(model.getB());
            double C = Double.parseDouble(model.getC());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Region getView(){
        return viewBuilder.build();
    }
}
