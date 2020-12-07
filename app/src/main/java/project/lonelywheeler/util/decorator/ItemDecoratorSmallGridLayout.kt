package project.lonelywheeler.util.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import project.lonelywheeler.app.MyApplication

class ItemDecoratorSmallGridLayout
constructor(
    private val horizontalMargin: Int,
    private val verticalMargin: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val width = MyApplication.application!!.resources.displayMetrics.widthPixels
        view.layoutParams.width = width / 2 - 30

        outRect.left = horizontalMargin; //
        outRect.right = horizontalMargin;
        outRect.bottom = verticalMargin;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0 ||
            parent.getChildLayoutPosition(view) == 1
        ) {
            outRect.top = verticalMargin;
        } else {
            outRect.top = 0;
        }
    }
}