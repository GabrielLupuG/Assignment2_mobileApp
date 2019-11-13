//Gabriel Lupu c15712195 DT354/year4
package app.gabriel.assignment2_mobileApp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Friend> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public MyViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public MyAdapter(ArrayList<Friend> myDataset) {
        mDataset = myDataset;
    }

    public void updateFriends(ArrayList<Friend> newDataSet) {
        this.mDataset = newDataSet;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Friend f = mDataset.get(position);
        String toShow = "Name: " + f.getName() + "\nPhone: " + f.getPhoneNumber() + "\nEmail: " + f.getEmail() + "\n";
        holder.mTextView.setText(toShow);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}