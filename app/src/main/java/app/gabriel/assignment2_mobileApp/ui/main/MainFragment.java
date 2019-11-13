//Gabriel Lupu c15712195 DT354/year4
package app.gabriel.assignment2_mobileApp.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import app.gabriel.assignment2_mobileApp.Friend;
import app.gabriel.assignment2_mobileApp.MyAdapter;
import app.gabriel.assignment2_mobileApp.R;
import app.gabriel.assignment2_mobileApp.Repository;


public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    public Repository repo;
    public EditText nameField, phoneField, emailField;
    public Button addUpdate, search;
    public MyAdapter mAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);

        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.list);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(container.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        nameField = (EditText) v.findViewById(R.id.nameEdit);
        phoneField = (EditText) v.findViewById(R.id.phoneEdit);
        emailField = (EditText) v.findViewById(R.id.emailEdit);
        addUpdate = (Button) v.findViewById(R.id.bAdd);
        search = (Button) v.findViewById(R.id.bSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameField.getText().toString();
                String phone = phoneField.getText().toString();
                if (name.length() == 0 && phone.length() == 0) {
                    Toast.makeText(getContext(), "Please check the input data. All fields are required.", Toast.LENGTH_LONG).show();
                } else {
                    if (name.length() != 0) {
                        Toast.makeText(getContext(), "Searching by name.", Toast.LENGTH_LONG).show();
                        ArrayList<Friend> toDisplay = new ArrayList<Friend>();
                        Friend f = repo.getFriend(name);
                        if (f != null) {
                            toDisplay.add(f);
                        }
                        mAdapter.updateFriends(toDisplay);
                        mAdapter.notifyDataSetChanged();
                    } else if (phone.length() != 0) {
                        Toast.makeText(getContext(), "Searching by phone.", Toast.LENGTH_LONG).show();
                        ArrayList<Friend> toDisplay = new ArrayList<Friend>();
                        Friend f = repo.getFriend(phone);
                        if (f != null) {
                            toDisplay.add(f);
                        }
                        mAdapter.updateFriends(toDisplay);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        addUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameField.getText().toString();
                String phone = phoneField.getText().toString();
                String email = emailField.getText().toString();

                Friend f = repo.getFriend(name);
                if (f == null) {
                    if (!(name.length() > 0 && phone.length() > 0 && email.length() > 0)) {
                        Toast.makeText(getContext(), "Please check the input data. All fields are required.", Toast.LENGTH_LONG).show();
                    } else {
                        f = new Friend();
                        f.setEmail(email);
                        f.setName(name);
                        f.setPhoneNumber(phone);
                        if (repo.insertFriend(f)) {
                            Toast.makeText(getContext(), "Friend added!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "Friend NOT added!", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    if (name.length() > 0) {
                        f.setName(name);
                    }

                    if (phone.length() > 0) {
                        f.setPhoneNumber(phone);
                    }

                    if (email.length() > 0) {
                        f.setEmail(email);
                    }
                    if (repo.updateFriend(name, f)) {
                        Toast.makeText(getContext(), "Friend updated!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Friend NOT updated!", Toast.LENGTH_LONG).show();

                    }
                }
                mAdapter.updateFriends(repo.getAllFriends());
                mAdapter.notifyDataSetChanged();
            }
        });

        repo = new Repository(container.getContext());
        mAdapter = new MyAdapter(repo.getAllFriends());
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}
