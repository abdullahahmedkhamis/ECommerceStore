package commerce.adapter;

import android.content.Context;
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

import commerce.amazoncommerce.ecommercestore.R;
import commerce.modles.RecommendModel;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    Context context;
    List<RecommendModel> list;

    public RecommendAdapter(Context context, List<RecommendModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.recommend_item,parent,false ) );
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        Glide.with( context ).load( list.get( position ).getImg_url() ).into( holder.imageView);
        holder.name.setText( list.get( position ).getName() );
        holder.descroption.setText( list.get( position ).getDescription() );
        holder.rating.setText( list.get( position ).getRating() );
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

ImageView imageView;
TextView name,descroption,rating;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super( itemView );

            imageView = itemView.findViewById( R.id.rec_img );
            name = itemView.findViewById( R.id.rec_name);
            descroption = itemView.findViewById( R.id.rec_dec );
            rating = itemView.findViewById( R.id.rec_rating );
        }
    }

}
