package info.cafenica.climatologia.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import info.cafenica.climatologia.R;
import info.cafenica.climatologia.db.adapters.ClimaAdapter;
import info.cafenica.climatologia.db.models.Clima;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.SlideInBottomAnimationAdapter;

/**
 * Created by Eder Xavier Rojas on 27/08/2015.
 */
public class FragmentClima extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    AlphaInAnimationAdapter alphaAdapter;
    ClimaAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clima, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id._recycler_clima);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.scrollToPosition(0);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
        mRecyclerView.getItemAnimator().setAddDuration(1000);

        Clima clima = new Clima(getActivity());
        ArrayList<Clima> variablesClima = clima.getClimaEntries();

        adapter = new ClimaAdapter(R.layout.row_clima, variablesClima);
        alphaAdapter = new AlphaInAnimationAdapter(adapter);
        alphaAdapter.setDuration(1000);
        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
//        mRecyclerView.setAdapter(adapter);
    }

    public void addClimaEntry(Clima entry){
        adapter.addItem(entry);
        alphaAdapter = new AlphaInAnimationAdapter(adapter);
        alphaAdapter.setDuration(1000);
        mRecyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
    }
}
