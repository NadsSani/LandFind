package com.nads.landfind.views

import android.content.Context
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import android.util.AttributeSet
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView


/** This class if for intercepting item selections so that they can be saved and restored.  */
class CustomNavigationView(context: Context?, attrs: AttributeSet?) :
    NavigationView(context!!, attrs), NavigationView.OnNavigationItemSelectedListener {
    private var subclassListener: OnNavigationItemSelectedListener? = null
    private var checkedId = -1
    override fun setNavigationItemSelectedListener(
        listener: OnNavigationItemSelectedListener?
    ) {
        subclassListener = listener
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return if (subclassListener != null) {
            val shouldBeSelected = subclassListener!!.onNavigationItemSelected(item)
            if (shouldBeSelected) {
                onItemChecked(item)
            }
            shouldBeSelected
        } else {
            onItemChecked(item)
            true
        }
    }

    private fun onItemChecked(item: MenuItem) {
        checkedId = item.itemId
    }

    override fun setCheckedItem(item: MenuItem) {
        checkedId = item.itemId
        item.isChecked = true
    }

    fun deselectItems() {
        checkedId = -1
    }

    val selected: MenuItem?
        get() = if (checkedId == -1) null else menu.findItem(checkedId)

    public override fun onSaveInstanceState(): Parcelable? {
        if (isNavigationViewSavedStateMissing) {
            return super.onSaveInstanceState()
        }

        // begin boilerplate code that allows parent classes to save state
        val superState = super.onSaveInstanceState()
        val ss = SavedState(superState)
        // end
        ss.selectedId = checkedId
        return ss
    }

    public override fun onRestoreInstanceState(state: Parcelable) {
        if (isNavigationViewSavedStateMissing) {
            super.onRestoreInstanceState(state)
            return
        }

        // begin boilerplate code so parent classes can restore state
        if (state !is SavedState) {
            super.onRestoreInstanceState(state)
            return
        }
        val ss = state
        super.onRestoreInstanceState(ss.superState)
        // end
        checkedId = ss.selectedId
    }

    /**
     * This is a hack, when the SavedState class is unmarshalled a "ClassNotFoundException" will be
     * thrown (the actual class not found is
     * "android.support.design.widget.NavigationView$SavedState") and I seem to only be able to
     * replicate on Marshmallow (someone else replicated in N through O_MR1 see
     * https://github.com/TeamAmaze/AmazeFileManager/issues/1400#issuecomment-413086603). Trying to
     * find the class and returning false if Class.forName() throws "ClassNotFoundException" doesn't
     * work because the class seems to have been loaded with the current loader (not the one the
     * unmarshaller uses); of course I have no idea of what any of this means so I could be wrong. For
     * the crash see https://github.com/TeamAmaze/AmazeFileManager/issues/1101.
     */
    val isNavigationViewSavedStateMissing: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

    internal class SavedState : BaseSavedState {
        var selectedId = 0

        constructor(superState: Parcelable?) : super(superState) {}
        private constructor(`in`: Parcel) : super(`in`) {
            selectedId = `in`.readInt()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(selectedId)
        }

        companion object {
            // required field that makes Parcelables from a Parcel
            @JvmField
            val CREATOR: Creator<SavedState?> = object : Creator<SavedState?> {
                override fun createFromParcel(`in`: Parcel): SavedState? {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

    init {
        super.setNavigationItemSelectedListener(this)
    }
}
