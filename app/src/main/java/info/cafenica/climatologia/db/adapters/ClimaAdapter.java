package info.cafenica.climatologia.db.adapters;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import info.cafenica.climatologia.R;
import info.cafenica.climatologia.db.Utilidades;
import info.cafenica.climatologia.db.models.Clima;

/**
 * Created by Eder Xavier Rojas on 27/08/2015.
 */
public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ViewHolder> {
    int view;
    ArrayList<Clima> variablesClima ;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtTempAct;
        TextView txtBrillo;
        TextView txtPrecipitacion;
        TextView txtHumedad;
        TextView txtFecha;
        ImageView image;
        Typeface roboto;

        public ViewHolder(View viewLayout) {
            super(viewLayout);
            roboto = Typeface.createFromAsset(viewLayout.getContext().getAssets(),"fonts/Roboto-Regular.ttf");

            txtTempAct = (TextView) viewLayout.findViewById(R.id.label_temp_actual);
            txtFecha = (TextView) viewLayout.findViewById(R.id.label_fecha);
            txtBrillo = (TextView) viewLayout.findViewById(R.id.label_brillo_solar);
            txtPrecipitacion = (TextView) viewLayout.findViewById(R.id.label_precipitacion);
            txtHumedad = (TextView) viewLayout.findViewById(R.id.label_humedad);
            image = (ImageView) viewLayout.findViewById(R.id.img_fecha);

            txtBrillo.setTypeface(roboto); txtFecha.setTypeface(roboto);
            txtTempAct.setTypeface(roboto) ;txtHumedad.setTypeface(roboto);
            txtPrecipitacion.setTypeface(roboto);

        }
    }

    public ClimaAdapter(int view, ArrayList<Clima> variablesClima) {
        this.view = view;
        this.variablesClima = variablesClima;
    }


    @Override
    public ClimaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClimaAdapter.ViewHolder holder, int position) {
        Clima entrada = variablesClima.get(position);
        holder.txtTempAct.setText(String.valueOf(entrada.getTemp_actual()));
        holder.txtPrecipitacion.setText(String.valueOf(entrada.getPrecipitacion()));
        holder.txtHumedad.setText(String.valueOf(entrada.getHum_actual()));
        holder.txtBrillo.setText(String.valueOf(entrada.getBrillo_solar()));

        Date fecha = Utilidades.dateFromInt(entrada.getFecha_productor());
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(fecha);
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String diaDelMes = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month_name = month_date.format(calendar.getTime());
        holder.txtFecha.setText(month_name+" de "+year);

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color1 = generator.getColor(diaDelMes);
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .useFont(holder.roboto)
                .fontSize(24) /* size in px */
                .endConfig()
                .buildRoundRect(diaDelMes, color1, 10);

        holder.image.setImageDrawable(drawable);

    }

    @Override
    public int getItemCount() {
        if(variablesClima!=null) {
            return variablesClima.size();
        }else {
            return 0;
        }
    }

    public void addItem(Clima entry){
        variablesClima.add(0,entry);
        this.notifyItemInserted(0);
        Log.d("REGISTRO", "En add item de adaptaer");
    }


}
