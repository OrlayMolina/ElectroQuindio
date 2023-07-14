module co.edu.uniquindio.electroquindio.main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens co.edu.uniquindio.electroquindio.main to javafx.fxml;
    exports co.edu.uniquindio.electroquindio.main;
    exports co.edu.uniquindio.electroquindio.controlador;
    opens co.edu.uniquindio.electroquindio.controlador to javafx.fxml;
    opens co.edu.uniquindio.electroquindio.modelo to javafx.fxml;
    exports co.edu.uniquindio.electroquindio.modelo;
    exports co.edu.uniquindio.electroquindio.enumm;
    opens co.edu.uniquindio.electroquindio.enumm to javafx.fxml;
}