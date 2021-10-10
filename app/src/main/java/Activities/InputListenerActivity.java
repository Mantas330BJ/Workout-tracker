package Activities;

import com.example.workoutbasic.OnInputListener;

import Interfaces.TextViewInput;

abstract public class InputListenerActivity extends DatabaseActivity implements OnInputListener {
    private TextViewInput currentClicked;

    @Override
    public void sendInput(String input) {
        currentClicked.setText(input);
    }

    @Override
    public void setCurrentClicked(TextViewInput currentClicked) {
        this.currentClicked = currentClicked;
    }

}
