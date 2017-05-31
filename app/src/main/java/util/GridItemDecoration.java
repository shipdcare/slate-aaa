package util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mark-42 on 3/5/17.
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {


    private int spanCount;
    private int spacing;
    private boolean includeEdge;
    private int headerNum;

    /**
     * Constructor
     *
     * @param spanCount   : spanCount
     * @param spacing     : spacing
     * @param includeEdge : includeEdge
     * @param headerNum   : headerNum
     */

    public GridItemDecoration(final int spanCount, final int spacing, final boolean includeEdge, final int headerNum) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        this.headerNum = headerNum;
    }

    @Override
    public void getItemOffsets(final Rect outRect, final View view, final RecyclerView parent,
                               final RecyclerView.State state) {
        /*
        item position
         */
        int position = parent.getChildAdapterPosition(view) - headerNum;

        if (position >= 0) {
            /*
             item column
             */
            int column = position % spanCount;

            if (includeEdge) {
                /*
                spacing - column * ((1f / spanCount) * spacing)
                 */
                outRect.left = spacing - column * spacing / spanCount;
                /*
                (column + 1) * ((1f / spanCount) * spacing)
                 */
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    /*
                    top edge
                     */
                    outRect.top = spacing;
                }
                /*
                item bottom
                 */
                outRect.bottom = spacing;
            } else {
                /*
                column * ((1f / spanCount) * spacing)
                 */
                outRect.left = column * spacing / spanCount;
                /*
                spacing - (column + 1) * ((1f /    spanCount) * spacing)
                 */
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    /*
                     item top
                     */
                    outRect.top = spacing;
                }
            }
        } else {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top = 0;
            outRect.bottom = 0;
        }
    }

}
