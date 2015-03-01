package jeffemanuel.org.duckduck;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;


public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private List<SearchItem> mDataset;


    private void applyPalette(View parent) {
       ImageView iv = (ImageView)parent.findViewById(R.id.niv_avatar);
        final TextView tv = (TextView)parent.findViewById(R.id.tv_info);
        Bitmap bitmap = ((BitmapDrawable)iv.getDrawable()).getBitmap();
        Palette.generateAsync(bitmap,
                new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch vibrant =
                                palette.getVibrantSwatch();
                        if (vibrant != null) {
                            // If we have a vibrant color
                            // update the title TextView
                            tv.setBackgroundColor(
                                    vibrant.getRgb());
                            tv.setTextColor(
                                    vibrant.getTitleTextColor());
                            Toast.makeText(tv.getContext(),"prominent color applied",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Provide a reference to the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,View.OnClickListener {
        // each data item is just a string in this case
        public TextView tv_info;
        public NetworkImageView niv;

        public ViewHolder(View root) {
            super(root);
            root.setOnLongClickListener(this);
            tv_info = (TextView) root.findViewById(R.id.tv_info);
            niv = (NetworkImageView) root.findViewById(R.id.niv_avatar);
        }

        //on long click remove the item
        @Override
        public boolean onLongClick(View view) {
            mDataset.remove(getPosition());
            //notifyDataSetChanged();
            //instead notify that a item is removed to do animation
            notifyItemRemoved(getPosition());
            return false;
        }
        @Override
        public void onClick(View v) {
            applyPalette(v);
        }
    }

    public RecyclerListAdapter(List<SearchItem> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(vh);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Log.d(TAG, "pos:" + position + ", holder:" + holder.tv_info + ",niv:" + holder.niv);

        // - get element from the dataset at this position
        // - replace the contents of the view with that element
        //imageloader uses lruCache and lazy Loading

        ImageLoader imageLoader = MainApplication.getInstance().getImageLoader();

        holder.tv_info.setText(mDataset.get(position).getHeadline());
        holder.niv.setImageUrl(mDataset.get(position).getImageURL(), imageLoader);

        holder.niv.setDefaultImageResId(R.drawable.default_image);
        holder.niv.setErrorImageResId(R.drawable.default_error);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}