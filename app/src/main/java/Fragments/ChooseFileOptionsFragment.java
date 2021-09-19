package Fragments;

import static android.os.Build.VERSION.SDK_INT;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import com.example.workoutbasic.BuildConfig;
import com.example.workoutbasic.R;

import java.io.File;

import Activities.MainActivity;
import Variables.wUri;

public class ChooseFileOptionsFragment extends DialogFragment {
    private ActivityResultLauncher<Intent> mediaPickerLauncher;
    private wUri uri;

    public ChooseFileOptionsFragment(wUri uri) {
        this.uri = uri;
        System.out.println(uri == null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        createLauncher();
        View view = inflater.inflate(R.layout.choose_file_options_fragment, container, false);

        Button selectMediaButton = view.findViewById(R.id.select_media_button);
        selectMediaButton.setOnClickListener(v -> {
            Intent pickIntent = new Intent(Intent.ACTION_PICK);
            pickIntent.setType("video/*");
            mediaPickerLauncher.launch(pickIntent);
        });

        Button playMediaButton = view.findViewById(R.id.play_media_button);
        playMediaButton.setOnClickListener(v -> {
            if (uri.getUri() != null) {
                Uri uriFile = Uri.parse(Uri.parse(uri.getUri()).getPath().substring(4));
                Intent intent = new Intent(Intent.ACTION_VIEW, uriFile);
                intent.setDataAndType(uriFile, "video/*");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "No media selected.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void createLauncher() {
        mediaPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        uri.setUri(data.getData().toString());
                    }
                });
    }
}
