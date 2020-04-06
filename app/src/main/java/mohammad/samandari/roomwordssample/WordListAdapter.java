package mohammad.samandari.roomwordssample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private static final String TAG = "Lord";

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
    private OnWordListener mOnWordListener;


    public WordListAdapter (Context context, OnWordListener onWordListener) {
        this.mOnWordListener = onWordListener;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView,mOnWordListener);
    }


    @Override
    public void onBindViewHolder (WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    void setWords (List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount () {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private final TextView wordItemView;
        OnWordListener onWordListener;
        private WordViewHolder (View itemView, OnWordListener onWordListener) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            this.onWordListener = onWordListener;
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick (View v) {
            onWordListener.onWordItemLongClick(getAdapterPosition());
            return false;
        }
    }
    //we have this interface to send the click information to the activity.
    public interface OnWordListener{
        void onWordItemLongClick (int position);
    }
}