module com.example.isy2zeeslagfx2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.isy2zeeslagfx2 to javafx.fxml;
    exports com.example.isy2zeeslagfx2;
}