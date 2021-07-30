package commerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import commerce.amazoncommerce.ecommercestore.R;
import commerce.modles.MyCartModel;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> cartModelList;
    int totalPrice = 0;

    public MyCartAdapter(Context context, List<MyCartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.my_cart_item,parent,false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyCartAdapter.ViewHolder holder, int position) {

        holder.name.setText( cartModelList.get( position ).getProductName() );
        holder.price.setText( cartModelList.get( position ).getProductPrice() );
        holder.date.setText( cartModelList.get( position ).getProductDate() );
        holder.time.setText( cartModelList.get( position ).getProductTime() );
        holder.quantity.setText( cartModelList.get( position ).getProductQuantity() );
        holder.totalPrice.setText( String.valueOf(  cartModelList.get( position ).getProductPrice()) );


        totalPrice = totalPrice +cartModelList.get( position ).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra( "totalAmount",totalPrice );
        LocalBroadcastManager.getInstance( context ).sendBroadcast( intent );
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,price,date,time,quantity,totalPrice;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );

            name = itemView.findViewById( R.id.product_name );
            price = itemView.findViewById( R.id.product_price);
            date = itemView.findViewById( R.id.product_date );
            time = itemView.findViewById( R.id.product_time);
            quantity = itemView.findViewById( R.id.total_quantity );
            totalPrice = itemView.findViewById( R.id.total_price );
        }
    }
}
