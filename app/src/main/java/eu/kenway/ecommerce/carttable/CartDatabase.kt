package eu.kenway.ecommerce.carttable

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CartEntity::class], version = 1, exportSchema = false)
abstract class CartDatabase:RoomDatabase() {

    abstract fun itemDao():CartDao

    companion object{
        @Volatile
        private var INSTANCE:CartDatabase?=null


        fun getDatabase(context: Context): CartDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }

}