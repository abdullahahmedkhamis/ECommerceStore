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

import commerce.activites.DetailedActivity;
import commerce.amazoncommerce.ecommercestore.R;
import commerce.modles.ViewAllModles;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.viewHolder> {

    Context context;
    List<ViewAllModles> list;

    public ViewAllAdapter(Context context, List<ViewAllModles> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new viewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.view_all_item,parent,false ) );
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {

        Glide.with( context ).load( list.get( position ).getImg_url() ).into( holder.imageView );
        holder.name.setText( list.get( position ).getName() );
        holder.description.setText( list.get( position ).getDescription() );
        holder.rating.setText( list.get( position ).getRating() );
        holder.price.setText( list.get( position ).getPrice()+"/kg" );

        if(list.get( position ).getType().equals( "egg" )){
           holder.price.setText( list.get( position ) .getPrice()+"/dozen");
        }
        if(list.get( position ).getType().equals( "Milk" )){
            holder.price.setText( list.get( position ) .getPrice()+"/litre");
        }

        if(list.get( position ).getType().equals( "Apple" )){
            holder.price.setText( list.get( position ) .getPrice()+"/litre");
        }

        if(list.get( position ).getType().equals( "Carrot" )){
            holder.price.setText( list.get( position ) .getPrice()+"/litre");
        }
        if(list.get( position ).getType().equals( "Fresh Fruit" )){
            holder.price.setText( list.get( position ) .getPrice()+"/litre");
        }
        if(list.get( position ).getType().equals( "Fresh Vegetables" )){
            holder.price.setText( list.get( position ) .getPrice()+"/litre");
        }

        holder.imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class );
                intent.putExtra( "detail", list.get( position ) );
                context.startActivity( intent );
            }

        } );

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,description,price,rating;

        public viewHolder(@NonNull @NotNull View itemView) {
            super( itemView );

            imageView = itemView.findViewById( R.id.view_img );
            name = itemView.findViewById( R.id.view_name );
            description = itemView.findViewById( R.id.view_description );
            price = itemView.findViewById( R.id.view_price );
            rating = itemView.findViewById( R.id.view_rating );
        }
    }
}
