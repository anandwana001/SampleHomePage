/**
 * Created by anandwana001 on
 * 22, March, 2022
 **/

package com.akshay.homepage.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Bind data to view.
 */
abstract class RoomAccRecyclerViewViewHolder<T> internal constructor(
  view: View
) : RecyclerView.ViewHolder(view) {
  internal abstract fun bind(data: T)
}