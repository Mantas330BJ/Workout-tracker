package ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel<T> extends ViewModel {
    private final MutableLiveData<T> selected = new MutableLiveData<>();

    public void select(T data) {
        selected.setValue(data);
    }

    public LiveData<T> getSelected() {
        return selected;
    }
}
