package eu.kenway.ecommerce.firestore


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import eu.kenway.ecommerce.auth.Login
import eu.kenway.ecommerce.auth.Register
import eu.kenway.ecommerce.firestore.models.User

class Firetoreclass {

    // Access a Cloud Firestore instance.
    private val mFireStore = FirebaseFirestore.getInstance()

    // TODO Step 7: Create a function to access the Cloud Firestore and create a collection.
    // START
    /**
     * A function to make an entry of the registered user in the FireStore database.
     */
    fun registerUser(fragment: Register, userInfo: User) {

        // The "users" is collection name. If the collection is already created then it will not create the same one again.
        mFireStore.collection(Constants.USERS)
            // Document ID for users fields. Here the document it is the User ID.
            .document(userInfo.id)
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                // Here call a function of base activity for transferring the result to it.
                fragment.userRegisterSuccess()
            }
            .addOnFailureListener { e ->

                Log.e(
                    fragment.javaClass.simpleName,
                    "Error while registering the user.",
                    e
                )
            }
    }
    // END


    fun getCurrentUserId():String{

        val currentUser=FirebaseAuth.getInstance().currentUser

        var currentUserId=""
        if(currentUser!=null)
        {
            currentUserId=currentUser.uid
        }
       return currentUserId
    }

    fun getUserDetails(fragment: Fragment) {

        // Here we pass the collection name from which we wants the data.
        mFireStore.collection(Constants.USERS)
            // The document id to get the Fields of user.
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->

                Log.i(fragment.javaClass.simpleName, document.toString())

                // Here we have received the document snapshot which is converted into the User Data model object.
                val user = document.toObject(User::class.java)!!

                // TODO Step 6: Pass the result to the Login Fragment.
                // START
                when (fragment) {
                    is Login -> {
                        // Call a function of base activity for transferring the result to it.
                        fragment.userLoggedInSuccess(user)
                    }
                }
                // END
            }
            .addOnFailureListener { e ->


                Log.e(
                    fragment.javaClass.simpleName,
                    "Error while getting user details.",
                    e
                )
            }
    }

}