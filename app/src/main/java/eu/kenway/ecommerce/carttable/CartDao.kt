package eu.kenway.ecommerce.carttable

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun additem(cartEntity: CartEntity)

    @Delete
    suspend fun deleteitem(cartEntity: CartEntity)

   @Query("SELECT * FROM cartData")
   fun readallData():LiveData<List<CartEntity>>

    @Query("SELECT * FROM cartData WHERE userid =:userid")
    fun UserdateUsername(userid :String):LiveData<List<CartEntity>>
}