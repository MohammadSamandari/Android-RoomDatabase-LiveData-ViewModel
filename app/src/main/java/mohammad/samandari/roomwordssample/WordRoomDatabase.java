package mohammad.samandari.roomwordssample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static WordRoomDatabase INSTANCE;

    public abstract WordDao wordDao ();

    static WordRoomDatabase getDatabase (final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new populateDbAsync(INSTANCE).execute();
        }
    };

    private static class populateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;
        //        String[] words = {"Dolphines", "corocodile", "cobra"};
        //we changed the code a bit so that starter data is not added, also i could delete this whole class
        String[] words = {};

        populateDbAsync (WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground (Void... voids) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
//            mDao.deleteAll();

            for (int i = 0; i <= words.length - 1; i++) {
                Word word = new Word(words[i]);
                mDao.insert(word);
            }
            return null;
        }
    }
}