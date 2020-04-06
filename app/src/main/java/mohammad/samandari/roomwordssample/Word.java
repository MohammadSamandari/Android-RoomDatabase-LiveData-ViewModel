package mohammad.samandari.roomwordssample;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    @PrimaryKey(autoGenerate = true)
    public int mKey;

    public Word (@NonNull String word) {
        this.mWord = word;
    }

    public String getWord () {
        return this.mWord;
    }

    public void setWord (String word) {
        this.mWord = word;
    }

}
