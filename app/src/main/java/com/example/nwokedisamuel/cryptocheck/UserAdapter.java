package com.example.nwokedisamuel.cryptocheck;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by nwokedi samuel on 7/19/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{


    List<cryptoData> cryptoData;
   Context context;


    public UserAdapter(List<cryptoData> cryptoData, Context context) {
        this.cryptoData = cryptoData;
        this.context=context;
        this.cryptoData=cryptoData;

    }

    public void delete(int position){
        //cryptoDatabase database = null;
       // database.getDao().delete(position);
        cryptoData.remove( position);
        notifyDataSetChanged();
    }






    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent,false );
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        holder.textView.setText(cryptoData.get(position).currencySymbol+""+""+cryptoData.get(position).getCryptValue());
        holder.imageView.setImageResource(cryptoData.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return cryptoData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView textView;
        public ImageView imageView,image;

        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.value);
            imageView=itemView.findViewById(R.id.symbol);
            image=itemView.findViewById(R.id.popUp);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    //popup(v);
                    PopupMenu popupMenu=new PopupMenu(v.getContext(),v);
                    MenuInflater inflater=popupMenu.getMenuInflater();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.convert:
                                    //move to conversion screen
                                    Intent i = new Intent(v.getContext(),conversionActivity.class);
                                    //put the symbol and value of the card in the intent's putExtra method which will be passed to the conversion screen
                                    i.putExtra("logo",cryptoData.get(getAdapterPosition()).getIcon());
                                    i.putExtra("symbol",cryptoData.get(getAdapterPosition()).getCurrencySymbol());
                                    i.putExtra("value",cryptoData.get(getAdapterPosition()).getCryptValue());
                                    v.getContext().startActivity(i);
                                    return true;


                                case R.id.remove:
                                    //delete from recyclerview
                                    delete(getAdapterPosition());

                                    return true;

                                default:
                                    return false;
                            }
                        }
                    });

                    inflater.inflate(R.menu.main,popupMenu.getMenu());
                    popupMenu.show();
                }



            });




        }
    }
}


