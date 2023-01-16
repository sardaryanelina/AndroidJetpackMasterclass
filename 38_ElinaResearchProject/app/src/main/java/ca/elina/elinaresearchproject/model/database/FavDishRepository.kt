package ca.elina.elinaresearchproject.model.database

import androidx.annotation.WorkerThread
import ca.elina.elinaresearchproject.model.entities.FavDish
import kotlinx.coroutines.flow.Flow


/**
 * A Repository manages queries and allows you to use multiple backend.
 *
 * The DAO is passed into the repository constructor as opposed to the whole database.
 * This is because it only needs access to the DAO, since the DAO contains all the read/write methods for the database.
 * There's no need to expose the entire database to the repository.
 *
 * @param favDishDao - Pass the FavDishDao as the parameter.
 */
class FavDishRepository(private val favDishDao: FavDishDao) {

    /**
     * By default Room runs suspend queries off the main thread, therefore, we don't need to
     * implement anything else to ensure we're not doing long running database work
     * off the main thread.
     */
    @WorkerThread
    suspend fun insertFavDishData(favDish: FavDish) {
        favDishDao.insertFavDishDetails(favDish)
    }

    // A variable for the dishes list to access it from ViewModel.
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allDishesList: Flow<List<FavDish>> = favDishDao.getAllDishesList()

    // A suspend function on workerThread to Update the details that can be called from the ViewModel class.
    @WorkerThread
    suspend fun updateFavDishData(favDish: FavDish) {
        favDishDao.updateFavDishDetails(favDish)
    }

    // Get the list of favorite dishes from the DAO and pass it to the ViewModel.
    val favoriteDishes: Flow<List<FavDish>> = favDishDao.getFavoriteDishesList()

    // A suspend function using WorkerThread to delete the dish details.
    @WorkerThread
    suspend fun deleteFavDishData(favDish: FavDish) {
        favDishDao.deleteFavDishDetails(favDish)
    }
}