package mohammad.samandari.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

   private WordDao mWordDao;
   private LiveData<List<Word>> mAllWords;

   WordRepository(Application application) {
       WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
       mWordDao = db.wordDao();
       mAllWords = mWordDao.getAllWords();
   }

    LiveData<List<Word>> getAllWords () {
        return mAllWords;
    }

    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    public void deleteAll () {
        new deleteAllAsyncTask(mWordDao).execute();
    }

    public void delete (Word word) {
        new deleteAsyncTask(mWordDao).execute(word);
    }

    public void update (Word word) {
        new updateAsyncTask(mWordDao).execute(word);
    }

    private static class deleteAllAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        deleteAllAsyncTask (WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground (final Word... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask (WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground (final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        deleteAsyncTask (WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground (final Word... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        updateAsyncTask (WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground (final Word... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}

