package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import Interfaces.TextViewData;

public class SharedViewModel extends ViewModel { //Not implemented
    private MutableLiveData<TextViewData> selected;

    public void select(TextViewData textViewData) {
        selected.setValue(textViewData);
    }

    public LiveData<TextViewData> getSelected() {
        if (selected == null) {
            selected = new MutableLiveData<>();
        }
        return selected;
    }
}
