package eu.kenway.ecommerce.carttable

import androidx.lifecycle.LiveData


class cartRepository(private val dao: CartDao) {

    val readalldata:LiveData<List<CartEntity>> = dao.readallData()

   suspend fun additem(entity: CartEntity)
    {
        dao.additem(entity)
    }
    suspend fun deleteitem(entity: CartEntity)
    {
        dao.deleteitem(entity)
    }

    fun userDataUsername(userid:String): LiveData<List<CartEntity>> {
        return dao.UserdateUsername(userid)
    }


}