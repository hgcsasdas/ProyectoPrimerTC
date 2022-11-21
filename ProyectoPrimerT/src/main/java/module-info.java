module Nebrija.ProyectoPrimerT {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens Nebrija.ProyectoPrimerT to javafx.fxml;
    exports Nebrija.ProyectoPrimerT;
}
