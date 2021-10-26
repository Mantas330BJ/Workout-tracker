 package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import Interfaces.Variables.TextViewData;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<TextViewData> selected = new MutableLiveData<>();

    public void select(TextViewData textViewData) {
        selected.setValue(textViewData);
    }

    public LiveData<TextViewData> getSelected() {
        return selected;
    }
}
