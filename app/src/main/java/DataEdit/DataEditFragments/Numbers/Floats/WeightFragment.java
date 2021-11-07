package DataEdit.DataEditFragments.Numbers.Floats;

import Utils.Formatter;
import Utils.NumberSetter;

public class WeightFragment extends FloatFragment {
    @Override
    public void setProp() {
        setData.setWeight(NumberSetter.getDoubleFromString(editText.getText().toString());
    }

    @Override
    public String getText() {
        return Formatter.formatInteger(setData.getSet());
    }
}
