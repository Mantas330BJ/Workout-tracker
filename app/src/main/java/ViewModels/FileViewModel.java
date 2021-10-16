package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import Interfaces.TextViewData;
import Variables.UriPasser;

public class FileViewModel extends ViewModel {
    private final MutableLiveData<UriPasser> selected = new MutableLiveData<>();


    public void select(UriPasser uriPasser) {
        selected.setValue(uriPasser);
    }

    public LiveData<UriPasser> getSelected() {
        return selected;
    }
}
