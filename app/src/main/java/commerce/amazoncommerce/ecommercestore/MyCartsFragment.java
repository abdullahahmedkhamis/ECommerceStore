package commerce.amazoncommerce.ecommercestore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import commerce.activites.PlacedOrderActivity;
import commerce.adapter.MyCartAdapter;
import commerce.modles.MyCartModel;


public class MyCartsFragment extends Fragment {

FirebaseFirestore db;
FirebaseAuth auth;
FirebaseUser user;

TextView overTotalAmount;
RecyclerView recyclerView;
MyCartAdapter cartAdapter;
List<MyCartModel>cartModelList;
    ProgressBar progressBar;

    Button buyNow;
    int totalBill;


    public MyCartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate( R.layout.fragment_my_carts, container, false );

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        progressBar = root.findViewById( R.id.progressbar );
        progressBar.setVisibility( View.VISIBLE );

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // User is signed in
//        } else {
//            // No user is signed in
//        }

        recyclerView = root.findViewById( R.id.recyclerView );
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );

recyclerView.setVisibility( View.GONE );

        cartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter( getActivity(),cartModelList );
        recyclerView.setAdapter( cartAdapter );

        overTotalAmount = root.findViewById( R.id.textView6 );
        LocalBroadcastManager.getInstance( getActivity() )
                .registerReceiver( mMessageReceiver,new IntentFilter("MyTotalAmount") );

        buyNow = root.findViewById( R.id.buy_now );

        db.collection( "CurrentUser" ).document(  auth.getCurrentUser().getUid() )  // String.valueOf
                .collection( "AddToCart" ).get().addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        MyCartModel cartModel = documentSnapshot.toObject( MyCartModel.class );
                        cartModelList.add( cartModel );
                        cartAdapter.notifyDataSetChanged();
                        progressBar.setVisibility( View.GONE );
                        recyclerView.setVisibility( View.VISIBLE );
                    }
                }
            }
        } );


        buyNow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlacedOrderActivity.class );
                intent.putExtra( "itemList",(Serializable) cartModelList );
                startActivity( intent );
            }
        } );
        return root;
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill = intent.getIntExtra("totalAmount",0);
            overTotalAmount.setText( "Total Bill :" +totalBill+"$" );

        }
    };
}

//        https://www.youtube.com/watch?v=PJPDFaiClbk
//        https://www.youtube.com/playlist?list=PL9jCwTXYWjDIHNEGtsRdCTk79I9-95TbJ
//        https://www.nigeapptuts.com/android-studio-debugging-identifying-cause/
//        https://www.youtube.com/watch?v=BaTCxcL3A1o
//        https://console.firebase.google.com/u/7/project/ecommerce-store-15ea3/firestore/data/~2FCurrentUser~2F%28%20auth.getCurrentUser%28%29.getUid%28%29%20%29~2FAddToCart

