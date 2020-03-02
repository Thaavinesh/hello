package ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcaresystemproject.R;

import Interface.ItemClickListener;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textProductName, textProductDescription, textProductPrice;
    public ImageView imageView;
    public ItemClickListener listener;

    public ProductViewHolder( View itemView) {
        super(itemView);

        textProductName = (TextView) itemView.findViewById(R.id.product_name);
        textProductPrice = (TextView) itemView.findViewById(R.id.product_price);
        textProductDescription = (TextView) itemView.findViewById(R.id.product_description);

    }

    public void setItemClickListener(ItemClickListener Listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(), false);
    }
}
