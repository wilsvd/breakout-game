/**
 * Specifies what the file requires.
 */
module com.example.dmscoursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.testng;
    requires junit;

    exports com.example.dmscoursework.Controller to junit;
    exports com.example.dmscoursework.Model to junit;

    exports com.example.dmscoursework.View.Ball to junit;

    exports com.example.dmscoursework.View.Brick.BlueBrick to junit;
    exports com.example.dmscoursework.View.Brick.OrangeBrick to junit;
    exports com.example.dmscoursework.View.Brick.PinkBrick to junit;
    exports com.example.dmscoursework.View.Brick.TributeBrick to junit;

    exports com.example.dmscoursework.View.Board to junit;
    exports com.example.dmscoursework.View.Level to junit;
    exports com.example.dmscoursework.View.Paddle to junit;


    opens com.example.dmscoursework to javafx.fxml;
    opens com.example.dmscoursework.Controller to javafx.fxml;
    opens com.example.dmscoursework.View.Board to javafx.fxml;
    opens com.example.dmscoursework.Controller.fxml to javafx.fxml;
    exports com.example.dmscoursework;
}