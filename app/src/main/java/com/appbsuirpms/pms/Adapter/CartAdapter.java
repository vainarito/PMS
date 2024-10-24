package com.appbsuirpms.pms.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appbsuirpms.pms.BackLogic.ChangeNumberItemsListener;
import com.appbsuirpms.pms.BackLogic.ManagmentCart;
import com.appbsuirpms.pms.databinding.ViewholderCartBinding;
import com.appbsuirpms.pms.domain.PopularDomain;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {
    ArrayList<PopularDomain> items;
    Context context;
    ViewholderCartBinding binding;
    ChangeNumberItemsListener changeNumberItemsListener;
    ManagmentCart managmentCart;

    public CartAdapter(ArrayList<PopularDomain> items, ChangeNumberItemsListener changeNumberItemsListener) {
        this.items = items;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    public ArrayList<PopularDomain> getItems() {
        return items;
    }

    public void setItems(ArrayList<PopularDomain> items) {
        this.items = items;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ChangeNumberItemsListener getChangeNumberItemsListener() {
        return changeNumberItemsListener;
    }

    public void setChangeNumberItemsListener(ChangeNumberItemsListener changeNumberItemsListener) {
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    public ManagmentCart getManagmentCart() {
        return managmentCart;
    }

    public void setManagmentCart(ManagmentCart managmentCart) {
        this.managmentCart = managmentCart;
    }

    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context = parent.getContext();
        managmentCart = new ManagmentCart(context);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, int position) {
        binding.titleTxt.setText(items.get(position).getTitle());
        binding.feeEachItem.setText("$" + items.get(position).getPrice());
        binding.totalEachItem.setText("$" + Math.round(items.get(position).getNumberInCart() * items.get(position).getPrice()));
        binding.numberItemTxt.setText(String.valueOf(items.get(position).getNumberInCart()));

        int drawableResourced = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl()
                , "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(binding.pic);


        binding.plusCartBtn.setOnClickListener(view -> managmentCart.plusNumberItem(items, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();

        }));

        binding.minusCartBtn.setOnClickListener(view -> managmentCart.minusNumberItem(items, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(ViewholderCartBinding binding) {
            super(binding.getRoot());
        }
    }
}
