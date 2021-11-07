package DataEdit.DataEditFragments.Numbers.Integers;

import Utils.Formatter;
import Utils.NumberSetter;

public class SetFragment extends IntegerFragment {
    @Override
    public void setProp() {
        setData.setSet(NumberSetter.getIntegerFromString(editText.getText().toString()));
    }

    @Override
    public String getText() {
        return Formatter.formatInteger(setData.getSet());
    }
}
