import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;

public class MainGame extends GameApplication {

    private Entity background, ball1;

    @Override
        protected void initSettings(GameSettings settings) {
            settings.setTitle("What Goes UP");
            settings.setWidth(1480/2);
            settings.setHeight(800);
        }
    @Override
    protected void initGame() {
        background = FXGL.entityBuilder()
                .at(0, 0)
                .view("background.png")
                .buildAndAttach();
        ball1= FXGL.entityBuilder()
                .at(10,590+10+10+10+5+3)
                .view("Untitled-4.png")
                .buildAndAttach();
    }
    @Override
    protected void initInput(){
        Input io= FXGL.getInput();

    }
    public static void main(String[] args) {
        launch( args ) ;
    }
}
