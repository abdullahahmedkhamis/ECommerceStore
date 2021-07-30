package commerce.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import commerce.adapter.ViewAllAdapter;
import commerce.amazoncommerce.ecommercestore.R;
import commerce.modles.ViewAllModles;

public class ViewAllActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ViewAllAdapter viewAllAdapter;
    List<ViewAllModles> viewAllModlesList;
    Toolbar toolbar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_all );

        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        progressBar = findViewById( R.id.progressbar );
        progressBar.setVisibility( View.VISIBLE );

        firestore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra( "type" );
        recyclerView = findViewById( R.id.view_all_rec );
        recyclerView.setVisibility( View.GONE );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        viewAllModlesList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter( this, viewAllModlesList );
        recyclerView.setAdapter( viewAllAdapter );

        if (type != null && type.equalsIgnoreCase( "fruits" )) {
            firestore.collection( "AllProducts" ).whereEqualTo( "type", "fruits" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );
        }

        if (type != null && type.equalsIgnoreCase( "fruits" )) {
            firestore.collection( "AllProducts" ).whereEqualTo( "type", "fruits" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );

        }

        if (type != null && type.equalsIgnoreCase( "fruits" )) {
            firestore.collection( "AllProducts" ).whereEqualTo( "type", "fruits" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );

        }


        if (type != null && type.equalsIgnoreCase( "fruits" )) {
            firestore.collection( "AllProducts" ).whereEqualTo( "type", "fruits" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );

        }


        if (type != null && type.equalsIgnoreCase( "Vegetables" )) {
            firestore.collection( "AllProducts" ).whereEqualTo( "type", "Vegetables" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );


        }


        if (type != null && type.equalsIgnoreCase( "fruit" )) {
            firestore.collection( "HomeCategory" ).whereEqualTo( "type", "Strawberry" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );


        }



        if (type != null && type.equalsIgnoreCase( "egg" )) {
            firestore.collection( "AllProducts" ).whereEqualTo( "type", "egg" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );


        }


        if (type != null && type.equalsIgnoreCase( "milk" )) {
            firestore.collection( "AllProducts" ).whereEqualTo( "type", "milk" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );


        }


        if (type != null && type.equalsIgnoreCase( "Vegetables" )) {
            firestore.collection( "AllProducts" ).whereEqualTo( "type", "Vegetables" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );


        }

        if (type != null && type.equalsIgnoreCase( "Vegetables" )) {
            firestore.collection( "AllProducts" ).whereEqualTo( "type", "Vegetables" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );


        }

        if (type != null && type.equalsIgnoreCase( "Vegetables" )) {
            firestore.collection( "AllProducts" ).whereEqualTo( "type", "Vegetables" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );


        }


        if (type != null && type.equalsIgnoreCase( "Milk" )) {
            firestore.collection( "HomeCategory" ).whereEqualTo( "type", "Milk" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        ViewAllModles viewAllModles = documentSnapshot.toObject( ViewAllModles.class );
                        viewAllModlesList.add( viewAllModles );
                        viewAllAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            } );


        }



    }
}

