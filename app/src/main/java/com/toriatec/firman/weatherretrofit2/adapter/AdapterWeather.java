package com.toriatec.firman.weatherretrofit2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toriatec.firman.weatherretrofit2.R;
import com.toriatec.firman.weatherretrofit2.model.weather.Forecast;

import java.util.List;

/**
 * Created by firmanmac on 2/4/17.
 */

public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.ViewHolder>  {

    private final OnItemClickListener listener;
    private List<Forecast> ques;
    private Context con;

    public AdapterWeather(Context context, List<Forecast> ques, OnItemClickListener listener) {
        this.ques = ques;
        this.listener = listener;
        this.con = context;
    }

    @Override
    public AdapterWeather.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterWeather.ViewHolder holder, int position) {
        holder.click(ques.get(position), listener);
        holder.tvDate.setText(ques.get(position).getDate());
        holder.tvHigh.setText(ques.get(position).getHigh());
        holder.tvLow.setText(ques.get(position).getLow());
        holder.tvText.setText(ques.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return ques.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvHigh, tvLow, tvText;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.txtDate);
            tvHigh = (TextView) itemView.findViewById(R.id.txtHigh);
            tvLow = (TextView) itemView.findViewById(R.id.txtLow);
            tvText = (TextView) itemView.findViewById(R.id.txtText);
        }


        public void click(final Forecast quesModel, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(quesModel);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(Forecast Item);
    }
}
