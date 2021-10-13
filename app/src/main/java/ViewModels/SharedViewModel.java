package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import Interfaces.TextViewData;

public class SharedViewModel extends ViewModel { //Not implemented
    private final MutableLiveData<TextViewData> selected = new MutableLiveData<>();


    public void select(TextViewData textViewData) {
        selected.setValue(textViewData);
    }

    public LiveData<TextViewData> getSelected() {
        return selected;
    }
}
