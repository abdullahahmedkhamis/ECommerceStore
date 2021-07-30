package commerce.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import commerce.amazoncommerce.ecommercestore.R;
import commerce.modles.ViewAllModles;

public class DetailedActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;
    ImageView detailedImg;
    TextView price, rating, description;
    Button addToCart;
    ImageView addItem, removeItem;
    Toolbar toolbar;

    ViewAllModles viewAllModles = null;

    FirebaseFirestore firestore;
    FirebaseAuth auth, userID;

    String currentUserId;
    FirebaseUser currentUser;

    String currentUserID;
    private FirebaseAuth mAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detailed );

        auth = FirebaseAuth.getInstance();
        // currentUserId = currentUser.getUid();


        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        firestore = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();


        final Object object = getIntent().getSerializableExtra( "detail" );
        if (object instanceof ViewAllModles) {
            viewAllModles = (ViewAllModles) object;

        }

        quantity = findViewById( R.id.quantity );


        detailedImg = findViewById( R.id.detailed_img );
        addItem = findViewById( R.id.add_item );
        removeItem = findViewById( R.id.remove_item );

        price = findViewById( R.id.detailed_price );

        rating = findViewById( R.id.detailed_rating );
        description = findViewById( R.id.detailed_dec );


        if (viewAllModles != null) {
            Glide.with( getApplicationContext() ).load( viewAllModles.getImg_url() ).into( detailedImg );
            rating.setText( viewAllModles.getRating() );
            description.setText( viewAllModles.getDescription() );
            price.setText( "Price :$" + viewAllModles.getPrice() + "/kg" );

            totalPrice = viewAllModles.getPrice() * totalQuantity;


            if (viewAllModles.getType().equals( "egg" )) {
                price.setText( "Price :$" + viewAllModles.getPrice() + "/dozen" );
                totalPrice = viewAllModles.getPrice() * totalQuantity;
            }

            if (viewAllModles.getType().equals( "mail" )) {
                price.setText( "Price :$" + viewAllModles.getPrice() + "/liter" );
                totalPrice = viewAllModles.getPrice() * totalQuantity;
            }

        }

        addToCart = findViewById( R.id.add_to_cart );
        addToCart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        } );
        addItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalQuantity < 100) {
                    totalQuantity++;
                    quantity.setText( String.valueOf( totalQuantity ) );
                    totalPrice = viewAllModles.getPrice() * totalQuantity;
                }
            }
        } );

        removeItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText( String.valueOf( totalQuantity ) );
                    totalPrice = viewAllModles.getPrice() * totalQuantity;
                }
            }
        } );

    }

    private void addedToCart() {
        String saveCurrentData, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat( "MM,dd,yyyy" );
        saveCurrentData = currentDate.format( calForDate.getTime() );

        SimpleDateFormat currentTime = new SimpleDateFormat( "HH:mm:ss a" );
        saveCurrentTime = currentTime.format( calForDate.getTime() );


        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put( "ProductName", viewAllModles.getName() );
        cartMap.put( "ProductPrice", price.getText().toString() );
        cartMap.put( "currentData", saveCurrentData );
        cartMap.put( "currentTime ", saveCurrentTime );
        cartMap.put( "totalQuantity", quantity.getText().toString() );
        cartMap.put( "totalPrice", totalPrice );


        if (user != null && user.getUid() != null) {
            firestore.collection( "CurrentUser" ).document( (auth.getCurrentUser().getUid()) )
                    .collection( "AddToCart" ).add( cartMap ).addOnCompleteListener( new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                    Toast.makeText( DetailedActivity.this, "Added To A Cart", Toast.LENGTH_SHORT ).show();
                    finish();
                }
            } );
        } else
            Toast.makeText( DetailedActivity.this, "User not found", Toast.LENGTH_SHORT ).show();


    }
}