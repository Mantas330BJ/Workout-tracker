package com.example.workoutbasic.pages.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.DialogFragment;

import com.example.workoutbasic.R;

public class ChooseFileOptionsFragment extends DialogFragment {
    private String fileUri;
    private final ActivityResultLauncher<Intent> mediaPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    assert intent != null;
                    fileUri = intent.getData().toString();
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_file_options_fragment, container, false);
//        FileViewModel viewModel = new ViewModelProvider(requireActivity()).get(FileViewModel.class);
//        viewModel.getSelected().observe(requireActivity(), data -> {
//            fileUri = data;
//            createView(view);
//        });

        return view;
    }

    public void createView(View view) {
        Button selectMediaButton = view.findViewById(R.id.select_media_button);
        selectMediaButton.setOnClickListener(v -> {
            Intent pickIntent = new Intent(Intent.ACTION_PICK);
            pickIntent.setType("video/*");
            mediaPickerLauncher.launch(pickIntent);
        });

        Button playMediaButton = view.findViewById(R.id.play_media_button);
        playMediaButton.setOnClickListener(v -> {
            if (fileUri != null) {
                Uri uriFile = Uri.parse(Uri.parse(fileUri).getPath().substring(4)); //TODO: do like a normal person
                Intent intent = new Intent(Intent.ACTION_VIEW, uriFile);
                intent.setDataAndType(uriFile, "video/*");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), "No media selected.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
