package project.lonelywheeler.util.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import project.lonelywheeler.app.MyApplication

class ItemDecoratorSmallLinearLayoutHorizontal
constructor(
    private val horizontalMargin: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val width = MyApplication.application!!.resources.displayMetrics.widthPixels
        view.layoutParams.width = width / 2 - 30

        outRect.right = horizontalMargin; //
        /*outRect.bottom = verticalMargin;
        outRect.top = verticalMargin

        // Add top margin only for the first item to avoid double space between items
        when (parent.getChildLayoutPosition(view)) {
            0 -> {
                outRect.left = verticalMargin;
            }
        }*/
    }
}