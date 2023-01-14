package ca.elina.elinaresearchproject.model.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


// TODO Step 2: Annotate a class with @Parcelize and implement Parcelable.
// START
@Parcelize
// Create a new package name as entities in the model package. After creating the entities package
// create data class with the entities (Dish Items) that we want to insert in the database.
// Define the Table name
@Entity(tableName = "fav_dishes_table")
data class FavDish(
    @ColumnInfo val image: String,
    @ColumnInfo(name = "image_source") val imageSource: String, // Local or Online
    @ColumnInfo val title: String,
    @ColumnInfo val type: String,
    @ColumnInfo val category: String,
    @ColumnInfo val ingredients: String,
    // Specifies the name of the column in the table if you want it to be different from the name of the member variable.
    @ColumnInfo(name = "cooking_time") val cookingTime: String,
    @ColumnInfo(name = "instructions") val directionToCook: String,
    @ColumnInfo(name = "favorite_dish") var favoriteDish: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable
// Parcelable is similar to Serializable.
// It makes an object into a String that can easily pass from one screen to another.
// END