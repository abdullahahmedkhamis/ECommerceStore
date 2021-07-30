package commerce.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import commerce.adapter.NavCategoryAdapter;
import commerce.adapter.NavecategoryDetaledAdapter;
import commerce.amazoncommerce.ecommercestore.R;
import commerce.modles.NavCategoryModle;
import commerce.modles.NavecategoryDetaledModel;
import commerce.modles.ViewAllModles;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavecategoryDetaledModel> list;   //   NavCategoryModle
    NavecategoryDetaledAdapter adapter;     // NavCategoryAdapter
    FirebaseFirestore db;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_nav_category );

        db = FirebaseFirestore.getInstance();
        progressBar = findViewById( R.id.progressbar );
        progressBar.setVisibility( View.VISIBLE );

        String type = getIntent().getStringExtra( "type" );

        recyclerView = findViewById( R.id.nav_cat_det_rec );
        recyclerView.setVisibility( View.GONE );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        list = new ArrayList<>();   //NavecategoryDetaledModel       NavCategoryModle
        adapter = new NavecategoryDetaledAdapter( this,list );
        recyclerView.setAdapter( adapter );

        if (type != null && type.equalsIgnoreCase( "drink" )) {
            db.collection( "NavCategoryDetailed" ).whereEqualTo( "type", "drink" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        NavecategoryDetaledModel viewAllModles = documentSnapshot.toObject( NavecategoryDetaledModel.class );
                        list.add( viewAllModles );
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );
        }
        db.collection( "NavCategoryDetailed" )
                .get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                      if(task.isSuccessful()) {
                          for(QueryDocumentSnapshot document :task.getResult()){

                              NavecategoryDetaledModel navecategoryDetaledAdapter = document.toObject( NavecategoryDetaledModel.class );
                              list.add( navecategoryDetaledAdapter );
                              adapter.notifyDataSetChanged();
                              progressBar.setVisibility( View.GONE );
                              recyclerView.setVisibility( View.VISIBLE );
                          }
                      }else {
                          Toast.makeText( NavCategoryActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT ).show();
                      }
                    }
                } );
    }
}