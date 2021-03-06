package commerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import commerce.activites.ViewAllActivity;
import commerce.amazoncommerce.ecommercestore.R;
import commerce.modles.HomeCategory;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    Context context;
    List<HomeCategory> categoryList;

    public HomeAdapter(Context context, List<HomeCategory> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.home_cart_item,parent,false ) );
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        Glide.with( context ).load( categoryList.get( position ).getImg_url() ).into( holder.catImg );
        holder.name.setText( categoryList.get( position ) .getName());

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class );
                intent.putExtra( "type",categoryList.get( position ) .getType());
                context.startActivity( intent );
            }
        } );
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView catImg;
        TextView name;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );
            catImg = itemView.findViewById( R.id.home_cat_img );
            name = itemView.findViewById( R.id.cat_home_name );

        }
    }
}
