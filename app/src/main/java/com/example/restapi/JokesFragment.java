package com.example.restapi;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.restapi.R;

public class JokesFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView jokeTextView;

    public JokesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jokes, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_quote);
        jokeTextView = view.findViewById(R.id.quote);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            fetchJokes(); // Fetch jokes when refreshing
        });

        fetchJokes(); // Fetch jokes initially
        return view;
    }

    private void fetchJokes() {
        new JokesTask(jokeTextView).execute(); // Execute AsyncTask to fetch jokes
    }
}
