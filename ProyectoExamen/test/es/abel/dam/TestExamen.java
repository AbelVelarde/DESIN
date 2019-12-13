package es.abel.dam;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TableViewMatchers;

import javax.swing.text.TableView;

public class TestExamen extends ApplicationTest {

    @Before
    public void setup() throws Exception{
        ApplicationTest.launch(Launcher.class);
    }

    @Override
    public void start(Stage stage) throws Exception{
        stage.show();
    }

    @Test
    public void testAñadirVia(){
        clickOn("#btnAlta");
        clickOn("#tfNombre");
        write("Via prueba test");

        clickOn("#cbDificultad");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);

        clickOn("#btnAceptar");

        FxAssert.verifyThat("#tablaVias", TableViewMatchers.containsRow(new String[]{"Via prueba test", "6b"}));
    }

}
