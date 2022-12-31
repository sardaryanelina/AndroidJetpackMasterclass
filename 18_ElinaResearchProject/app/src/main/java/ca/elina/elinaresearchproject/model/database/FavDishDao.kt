package ca.elina.elinaresearchproject.model.database

import androidx.room.Dao
import androidx.room.Insert
import ca.elina.elinaresearchproject.model.entities.FavDish

// TODO Step 1: Create a new package name as database in the model package.

// TODO Step 2: Create an interface name as FavDishDao that we will use to specify SQL queries and associate them with method calls.
// START
@Dao
interface FavDishDao {

    // TODO Step 3: Create function for insert.
    // START

    /**
     * All queries must be executed on a separate thread. They cannot be executed from Main Thread or it will cause a crash.
     *
     * Room has Kotlin coroutines support.
     * This allows your queries to be annotated with the suspend modifier and then called from a coroutine
     * or from another suspension function.
     */

    /**
     * A function to insert favorite dish details to the local database using Room.
     *
     * @param favDish - Here we will pass the entity class that we have created.
     */
    @Insert
    suspend fun insertFavDishDetails(favDish: FavDish)
    // END
}
// END