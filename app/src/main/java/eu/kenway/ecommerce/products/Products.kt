  package eu.kenway.ecommerce.products


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import eu.kenway.ecommerce.MainActivity
import eu.kenway.ecommerce.R


import eu.kenway.ecommerce.databinding.ActivityProductsBinding
import eu.kenway.ecommerce.products.items.*
import eu.kenway.ecommerce.products.items.home.Home
import eu.kenway.ecommerce.products.items.orders.orders


  class Products : AppCompatActivity() {

    lateinit var binding: ActivityProductsBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var preferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences=getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)
        val email=preferences.getString("EMAIL","")


        binding.apply {
            toggle= ActionBarDrawerToggle(this@Products,drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)


            val navigationView : NavigationView  = findViewById(R.id.nav_view)
            val headerView : View = navigationView.getHeaderView(0)
            val tvname : TextView = headerView.findViewById(R.id.user_name)
            val navUserEmail : TextView = headerView.findViewById(R.id.lmail)

            navUserEmail.text = email.toString()

            // setting navigation drawer name and email



            var arr= email?.split("@")?.toTypedArray()
            if (arr != null) {
                tvname.text=arr.get(0)
            }

            //seetin g default fragment
            supportFragmentManager.beginTransaction().replace(R.id.framelayout, Home()).commit()
            // what happen if we click items of navigation drawer

            navView.setNavigationItemSelectedListener {

                it.isChecked=true
                when(it.itemId)
                {
                    R.id.Home -> {
                        supportFragmentManager.beginTransaction().replace(R.id.framelayout, Home())
                            .commit()
                        drawerLayout.closeDrawer(GravityCompat.START)

                    }
                    R.id.Viewcart -> {
                        supportFragmentManager.beginTransaction().replace(
                            R.id.framelayout,
                            Cart()
                        ).commit()
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.orders -> {
                        supportFragmentManager.beginTransaction().replace(
                            R.id.framelayout,
                            orders()
                        ).commit()
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.feedback -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.framelayout, Feedback()).commit()
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.help -> {
                        supportFragmentManager.beginTransaction().replace(R.id.framelayout, Help())
                            .commit()
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    R.id.Logout ->{
                        val editor:SharedPreferences.Editor=preferences.edit()
                        editor.clear()
                        editor.apply()
                        startActivity(Intent(this@Products,MainActivity::class.java))
                        finish()
                    }

                }
                true
            }

        }
    }




      override fun onOptionsItemSelected(item: MenuItem): Boolean {
          if (toggle.onOptionsItemSelected(item)){
              true
          }
          return super.onOptionsItemSelected(item)
      }

  }