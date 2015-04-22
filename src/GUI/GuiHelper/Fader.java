package GUI.GuiHelper;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.scene.Parent;
import javafx.util.Duration;

/**
 * Created by steinar on 22.04.2015.
 *
 * This class sets Fadetime between one panel and the next one.
 * example implementation:      private void loadParent(Parent scene)
 *                              {
 *                                  fade.setFading(scene);
 *                                  rootLayout.setCenter(scene); //set scene
 *                                  fade.setupFadeout(scene);
 *                              }
 * //todo: maybe make this as a class function?
 */
public class Fader
{
    private FadeTransition fader = new FadeTransition();
    private double FADETIME = 0.35;

    public Parent setFading(Parent scene)
    {
        if (fader != null)
            fader.play();
        fader = new FadeTransition(Duration.seconds(FADETIME), scene);
        fader.setInterpolator(Interpolator.EASE_IN);
        fader.setFromValue(0.1);
        fader.setToValue(1.0);
        fader.play();
        return scene;
    }

     public void setupFadeout(Parent scene)
    {
        fader = new FadeTransition(Duration.millis(FADETIME), scene);
        fader.setInterpolator(Interpolator.EASE_OUT);
        fader.setFromValue(1.0);
        fader.setToValue(0.1);
    }
}
