package test.satoshi.ecuacionesCuadraticas;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class Controller {

    private final Model model;
    private final Builder<Region> viewBuilder;

    public Controller(){
        this.model = new Model();;

        viewBuilder = new ViewBuilder(model);

        model.DProperty().bind(Bindings.createDoubleBinding(this::CalcDiscriminante, model.AProperty(), model.BProperty(), model.CProperty(), model.okToCalculateProperty()));

        model.x1Property().bind(Bindings.createStringBinding(this::CalcX1, model.DProperty(), model.okToCalculateProperty()));

        model.x2Property().bind(Bindings.createStringBinding(this::CalcX2, model.DProperty(), model.okToCalculateProperty()));

        model.X1EnabledProperty().bind(Bindings.createBooleanBinding(() -> model.getD() >= 0, model.DProperty()));

        model.X2EnabledProperty().bind(Bindings.createBooleanBinding(() -> model.getD() > 0, model.DProperty()));
    }

    private double CalcDiscriminante(){
        if(isDataValid()){
            double A = Double.parseDouble(model.getA());
            double B = Double.parseDouble(model.getB());
            double C = Double.parseDouble(model.getC());
            return Math.pow(B, 2) - 4 * A * C;
        }
        return Double.NaN;
    }

    private String CalcX1(){
        if(isDataValid() && model.getx1Enabled()){
            double A = Double.parseDouble(model.getA());
            double B = Double.parseDouble(model.getB());
            double D = model.getD();
            return String.valueOf((-B + Math.sqrt(D)) / (2 * A));
        }
        return "";
    }

    private String CalcX2(){
        if(isDataValid() && model.getx2Enabled()){
            double A = Double.parseDouble(model.getA());
            double B = Double.parseDouble(model.getB());
            double D = model.getD();
            return String.valueOf((-B - Math.sqrt(D)) / (2 * A));
        }
        return "";
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
