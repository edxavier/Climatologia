package info.cafenica.climatologia.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.rey.material.app.BottomSheetDialog;
import com.rey.material.app.Dialog;
import com.rey.material.drawable.ThemeDrawable;
import com.rey.material.util.ViewUtil;
import com.rey.material.widget.Button;

import java.util.ArrayList;

import info.cafenica.climatologia.R;
import info.cafenica.climatologia.db.adapters.ClimaAdapter;
import info.cafenica.climatologia.db.models.Clima;
import info.cafenica.climatologia.db.models.ClimaSync;
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
    ScaleInAnimationAdapter scaleInAnimationAdapter;
    ClimaAdapter adapter = null;
    ArrayList<Clima> variablesClima;
    private BottomSheetDialog mBottomSheetDialog;

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
        mRecyclerView.setItemAnimator(new FlipInTopXAnimator());
        mRecyclerView.getItemAnimator().setAddDuration(800);

        Clima clima = new Clima();
        //seleccionar sin ordenar
        //ArrayList<Clima> variablesClima =(ArrayList<Clima>) Clima.listAll(Clima.class);
        variablesClima = (ArrayList<Clima>) Select.from(Clima.class).orderBy("id DESC").list();

        adapter = new ClimaAdapter(R.layout.row_clima, variablesClima);
        alphaAdapter = new AlphaInAnimationAdapter(adapter);
        alphaAdapter.setDuration(1000);
        scaleInAnimationAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        mRecyclerView.setAdapter(scaleInAnimationAdapter );
        registerForContextMenu(mRecyclerView);
//        mRecyclerView.setAdapter(adapter);
    }

    public void addClimaEntry(Clima entry){
        adapter.addItem(entry);
        scaleInAnimationAdapter.notifyItemInserted(0);
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Clima entry;
        switch (item.getItemId()) {
            case 1:
                // do your stuff
                entry = adapter.getEntry();
                Log.d("CONTEXT", entry.getObservacion());
                entry.setObservacion("MODIFICADO");
                entry.save();
                Dialog mDialog = new Dialog(getActivity());
                mDialog.contentView(R.layout.row_clima)
                        .title("Dialog title")
                        .positiveAction("OK")
                        .negativeAction("CANCEL")
                        .cancelable(true)
                .show();

                adapter.updateEntry(entry);
                break;
            case 2:
                // do your stuff

                break;
        }
        return super.onContextItemSelected(item);
    }
}
