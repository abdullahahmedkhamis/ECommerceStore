package commerce.amazoncommerce.ecommercestore.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.PopupWindowCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import commerce.adapter.HomeAdapter;
import commerce.adapter.PopularAdapters;
import commerce.adapter.RecommendAdapter;
import commerce.amazoncommerce.ecommercestore.R;
import commerce.amazoncommerce.ecommercestore.databinding.FragmentHomeBinding;
import commerce.modles.HomeCategory;
import commerce.modles.PopularModel;
import commerce.modles.RecommendModel;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;

    RecyclerView popularRce,homeCatRec,recommendedRec;
    FirebaseFirestore db;

    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;

    List<HomeCategory>categoryList;
    HomeAdapter homeAdapter;

    List<RecommendModel> recommendModelList;
    RecommendAdapter recommendAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate( R.layout.fragment_home,container,false );

        db = FirebaseFirestore.getInstance();

        popularRce = root.findViewById( R.id.pop_rec );
        homeCatRec = root.findViewById( R.id.explore_rec );
        recommendedRec = root.findViewById( R.id.recommended_rec );
        scrollView = root.findViewById( R.id.scroll_view );
        progressBar = root.findViewById( R.id.progressbar );

        progressBar.setVisibility( View.VISIBLE );
        scrollView.setVisibility( View.GONE );

        popularRce.setLayoutManager( new LinearLayoutManager( getActivity(),RecyclerView.HORIZONTAL,false ) );
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters( getActivity(),popularModelList );
        popularRce.setAdapter( popularAdapters );

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            for(QueryDocumentSnapshot document : task.getResult()){

                                PopularModel popularModel = document.toObject( PopularModel.class );
                                popularModelList.add( popularModel );
                                popularAdapters.notifyDataSetChanged();

                                progressBar.setVisibility( View.GONE );
                                scrollView.setVisibility( View.VISIBLE );
                            }
                        }else {
                            Toast.makeText( getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );


        homeCatRec.setLayoutManager( new LinearLayoutManager( getActivity(),RecyclerView.HORIZONTAL,false ) );
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter( getActivity(),categoryList );
        homeCatRec.setAdapter( homeAdapter );

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            for(QueryDocumentSnapshot document : task.getResult()){

                                HomeCategory homeCategory = document.toObject( HomeCategory.class );
                                categoryList.add( homeCategory );
                                homeAdapter.notifyDataSetChanged();

                            }
                        }else {
                            Toast.makeText( getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );

        recommendedRec.setLayoutManager( new LinearLayoutManager( getActivity(),RecyclerView.HORIZONTAL,false ) );
        recommendModelList = new ArrayList<>();
        recommendAdapter = new RecommendAdapter( getActivity(),recommendModelList );
        recommendedRec.setAdapter( recommendAdapter );

        db.collection("Recommend")
                .get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            for(QueryDocumentSnapshot document : task.getResult()){

                                RecommendModel recommendModel = document.toObject( RecommendModel.class );
                                recommendModelList.add( recommendModel );
                                recommendAdapter.notifyDataSetChanged();

                            }
                        }else {
                            Toast.makeText( getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );


        return root;
    }


}