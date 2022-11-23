module Nebrija.ProyectoPrimerT {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.base;

    opens Nebrija.ProyectoPrimerT to javafx.fxml;
    exports Nebrija.ProyectoPrimerT;
    exports Nebrija.ProyectoPrimerT.ConexionBD;
    exports Nebrija.ProyectoPrimerT.Usuarios;
    exports Nebrija.ProyectoPrimerT.Objetos;
}
