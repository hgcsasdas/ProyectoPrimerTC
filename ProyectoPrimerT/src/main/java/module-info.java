module Nebrija.ProyectoPrimerT {
    requires javafx.controls;
    requires javafx.fxml;

    opens Nebrija.ProyectoPrimerT to javafx.fxml;
    exports Nebrija.ProyectoPrimerT;
}
