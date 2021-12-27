package com.SheyhMe01.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.SheyhMe01.myapplication.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var client: GoogleApiClient
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idToken = intent.getStringExtra("idToken")

        auth = FirebaseAuth.getInstance()
val credential = GoogleAuthProvider.getCredential(idToken, null)





        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webId))
            .requestEmail()
            .build()

        client = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, options)
            .build()
        binding.signBtn.setOnClickListener {
            val intent = Auth.GoogleSignInApi.getSignInIntent(client)
            startActivityForResult(intent, 300)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 300) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)

            result?.signInAccount?.let { account ->
              startActivity(Intent(this,MessagesActivity::class.java))
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this,"Connecting Error",Toast.LENGTH_LONG).show()

    }


}



