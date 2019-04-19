package in.codecorp.ssgcp.shop.LoadingListview;

import android.widget.BaseAdapter;

public interface RefreshListener {
    /**
     * @param pageNumber page number
     * @param sampleSize sample size defined on xml parameters
     * @param listView   the listview itself
     * @param adapter    the adapter that you need to repopulate
     */
    void refresh(int pageNumber, int sampleSize, LoadingListView listView, BaseAdapter adapter);
}