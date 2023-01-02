package com.example.wagba.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wagba.MainActivity;
import com.example.wagba.R;
import com.example.wagba.databinding.FragmentProfileBinding;
import com.example.wagba.model.User;
import com.example.wagba.viewModel.ProfileViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";
    FragmentProfileBinding _binding;
    ProfileViewModel _profileViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = _binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _profileViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
                requireActivity().getApplication()).create(ProfileViewModel.class);
        _profileViewModel.getCurrentUser().observe(requireActivity(), this::updateUI);
        _binding.btnSignOut.setOnClickListener(this::onSignOut);
    }

    private void updateUI(User user){
        if(user != null){
            _binding.tvName.setText(user.getUsername());
            _binding.tvPhone.setText(user.getPhoneNumber());
            _binding.tvEmail.setText(user.getEmail());

        }

    }

    private void onSignOut(View view){
        Intent intent = new Intent(requireContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        _profileViewModel.signOut();
        requireActivity().finish();
    }
}