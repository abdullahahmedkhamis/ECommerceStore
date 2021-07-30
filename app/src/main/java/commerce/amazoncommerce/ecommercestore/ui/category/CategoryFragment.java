package commerce.amazoncommerce.ecommercestore.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import commerce.adapter.NavCategoryAdapter;
import commerce.adapter.PopularAdapters;
import commerce.amazoncommerce.ecommercestore.R;
import commerce.modles.NavCategoryModle;
import commerce.modles.PopularModel;


public class CategoryFragment extends Fragment {

    FirebaseFirestore db;
    List<NavCategoryModle> categoryModleList;
    NavCategoryAdapter navCategoryAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate( R.layout.fragment_category,container,false);
        db = FirebaseFirestore.getInstance();
        recyclerView = root.findViewById( R.id.cat_res );
        progressBar = root.findViewById( R.id.progressbar );
        progressBar.setVisibility( View.VISIBLE );
        recyclerView.setVisibility( View.GONE );


        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity(),RecyclerView.VERTICAL,false ) );
        categoryModleList = new ArrayList<>();
        navCategoryAdapter = new NavCategoryAdapter( getActivity(),categoryModleList );
        recyclerView.setAdapter( navCategoryAdapter );

        db.collection("NavCategory")
                .get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            for(QueryDocumentSnapshot document : task.getResult()){

                                NavCategoryModle navCategoryModle = document.toObject( NavCategoryModle.class );
                                categoryModleList.add( navCategoryModle );
                                navCategoryAdapter.notifyDataSetChanged();
                                progressBar.setVisibility( View.GONE );
                                recyclerView.setVisibility( View.VISIBLE );

                            }
                        }else {
                            Toast.makeText( getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT ).show();
                            progressBar.setVisibility( View.GONE );
                            recyclerView.setVisibility( View.VISIBLE );
                        }
                    }
                } );
        return root;
    }


}