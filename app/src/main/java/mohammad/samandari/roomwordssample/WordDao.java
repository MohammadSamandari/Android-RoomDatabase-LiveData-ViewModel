package mohammad.samandari.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {

    // The conflict strategy defines what happens,
    // if there is an existing entry.
    // The default action is ABORT.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Word word);

    // Simple query that does not take parameters and returns nothing.
    @Query("DELETE FROM word_table")
    void deleteAll ();

    // Simple query without parameters that returns values.
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords ();

    @Delete
    void delete (Word word);

    @Update
    void update (Word word);

    // Update multiple entries with one call.
    @Update
    void updateWords(Word... words);

    // Query with parameter that returns a specific word or words.
    @Query("SELECT * FROM word_table WHERE word LIKE :word ")
    List<Word> findWord(String word);

    //Learn More Here: https://developer.android.com/training/data-storage/room/accessing-data.html
}

