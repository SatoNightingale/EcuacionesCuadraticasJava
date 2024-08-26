package test.satoshi.ecuacionesCuadraticas;

import javafx.beans.property.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
    private final StringProperty paramA = new SimpleStringProperty();
    private final StringProperty paramB = new SimpleStringProperty();
    private final StringProperty paramC = new SimpleStringProperty();
    private final DoubleProperty Discriminante = new SimpleDoubleProperty();
    private final StringProperty x1 = new SimpleStringProperty();
    private final StringProperty x2 = new SimpleStringProperty();
    private final BooleanProperty x1Enabled = new SimpleBooleanProperty();
    private final BooleanProperty x2Enabled = new SimpleBooleanProperty();
    private final BooleanProperty okToCalculate = new SimpleBooleanProperty();

    // Getters y setters //

    public String getA(){ return paramA.get();}
    public StringProperty AProperty(){ return paramA;}
    public void setA(String A){ paramA.set(A);}

    public String getB(){ return paramB.get();}
    public StringProperty BProperty(){ return paramB;}
    public void setB(String B){ paramB.set(B);}

    public String getC(){ return paramC.get();}
    public StringProperty CProperty(){ return paramC;}
    public void setC(String C){ paramC.set(C);}

    public double getD(){ return Discriminante.get(); }
    public DoubleProperty DProperty(){ return Discriminante; }
    public void setD(double D){ Discriminante.set(D);}

    public String getx1(){ return x1.get();}
    public StringProperty x1Property(){ return x1;}
    public void setx1(String x_1){ x1.set(x_1);}

    public String getx2(){ return x2.get();}
    public StringProperty x2Property(){ return x2;}
    public void setx2(String x_2){ x2.set(x_2);}

    public BooleanProperty X1EnabledProperty(){ return x1Enabled; }
    public boolean getx1Enabled(){ return x1Enabled.get();}
	public void setx1Enabled(boolean enabled) { x1Enabled.set(enabled); }

    public BooleanProperty X2EnabledProperty(){ return x2Enabled; }
    public boolean getx2Enabled(){ return x2Enabled.get(); }
	public void setx2Enabled(boolean enabled) { x2Enabled.set(enabled); }

    public Boolean getOkToCalculate(){ return okToCalculate.get();}
    public BooleanProperty okToCalculateProperty(){return okToCalculate;}
}
