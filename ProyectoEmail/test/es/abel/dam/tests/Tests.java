package es.abel.dam.tests;

import es.abel.dam.Launcher;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TableViewMatchers;

import javax.swing.text.TableView;

public class Tests extends ApplicationTest {

    @Before
    public void setup() throws Exception{
        ApplicationTest.launch(Launcher.class);
    }

    @Override
    public void start(Stage stage) throws Exception{
        stage.show();
    }

    @Test
    public void testAñadirCuenta(){
        clickOn("#menuCuentas");
        clickOn("#menuAñadirCuenta");
        clickOn("#btnLoginAccept");

        clickOn("#treeViewMail");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        clickOn("#tablaMails");

        FxAssert.verifyThat("#tablaMails", TableViewMatchers.containsRow(new String[]{"Prueba", "bryangallegoclases@gmail.com", "28-11-2019"}));
    }

    @Test
    public void testResponder(){
        clickOn("#menuCuentas");
        clickOn("#menuAñadirCuenta");
        clickOn("#btnLoginAccept");

        clickOn("#treeViewMail");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        clickOn("#tablaMails");
        type(KeyCode.DOWN);
        clickOn("#btnMgResponder");

        clickOn("#cbAccounts");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        clickOn("#tfDestinatario");
        write("AbelVelardeProfesional@gmail.com");

        clickOn("#tfAsunto");
        write("Prueba Test Responder");

        clickOn("#tfAsunto");
        write("Prueba Test Responder");

        clickOn("#htmlEditor");
        write("Prueba test responder mensaje");

        clickOn("#btnEnviar");

        clickOn("#treeViewMail");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);

        FxAssert.verifyThat("#tablaMails", TableViewMatchers.containsRow(new String[]{"Prueba Responder Correo", "sandierparapromociones@gmail.com", "08-12-2019"}));
    }
}
