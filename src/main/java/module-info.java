module com.heinzelman.pdfrepoappjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;
    requires pdfbox;


    opens com.heinzelman.pdfrepoappjava to javafx.fxml;
    exports com.heinzelman.pdfrepoappjava;
}