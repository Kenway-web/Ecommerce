  package eu.kenway.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import eu.kenway.ecommerce.databinding.ActivityProductsBinding

class Products : AppCompatActivity() {

    lateinit var binding: ActivityProductsBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toggle= ActionBarDrawerToggle(this@Products,drawerLayout,R.string.open_drawer,R.string.close_drawer)
            drawerLayout.addDrawerListener(toggle)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {

                when(it.itemId)
                {
                    R.id.Viewcart-> Toast.makeText(applicationContext, "Clicked cart", Toast.LENGTH_SHORT).show()
                    R.id.orders-> Toast.makeText(applicationContext, "Clicked orders", Toast.LENGTH_SHORT).show()
                    R.id.feedback-> Toast.makeText(applicationContext, "Clicked feedback", Toast.LENGTH_SHORT).show()
                    R.id.help-> Toast.makeText(applicationContext, "Clicked Logout", Toast.LENGTH_SHORT).show()
                    R.id.Logout->Toast.makeText(applicationContext,"Clicked Logout",Toast.LENGTH_SHORT).show()

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