package com.set.sazib.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;

import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationViewExtended extends BottomNavigationView {

  private ItemSelectedListener listener;
  private ItemIndexSelectedListener listenerByIndex;
  private int currentItem = 0;

  public BottomNavigationViewExtended(Context context) {
    super(context);
    init();
  }

  public BottomNavigationViewExtended(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public BottomNavigationViewExtended(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public int getCurrentItem() {
    return currentItem;
  }

  public void setCurrentItem(int itemIndex) {
    final boolean wasChecked = checkItem(itemIndex);
    if (listener != null) {
      listener.onItemSelected(getMenu().getItem(itemIndex), wasChecked);
    } else if (listenerByIndex != null) {
      listenerByIndex.onItemSelected(itemIndex, wasChecked);
    }
  }

  public MenuItem getItem(int itemIndex) {
    return getMenu().getItem(itemIndex);
  }

  public void setItemSelectedListener(@Nullable ItemSelectedListener listener) {
    this.listener = listener;
  }

  public void setItemSelectedListener(@Nullable ItemIndexSelectedListener listener) {
    this.listenerByIndex = listener;
  }

  private void init() {
    setOnNavigationItemSelectedListener(item -> {
      final boolean wasChecked = checkItem(item);
      if (listener != null) {
        return listener.onItemSelected(item, wasChecked);
      }
      return listenerByIndex != null && listenerByIndex.onItemSelected(getItemIndex(item),
          wasChecked);
    });
  }

  private boolean checkItem(int itemIndex) {
    if (itemIndex < 0) return true;
    if (currentItem == itemIndex) return true;
    for (int i = 0; i < getMenu().size(); i++) {
      getMenu().getItem(i).setChecked(i == itemIndex);
    }

    currentItem = itemIndex;
    return false;
  }

  private boolean checkItem(MenuItem item) {
    item.setChecked(true);
    return checkItem(getItemIndex(item));
  }

  /**
   * @return item's index in Menu or -1 if item was not found
   */

  private int getItemIndex(MenuItem item) {
    for (int i = 0; i < getMenu().size(); i++) {
      if (getMenu().getItem(i).equals(item)) {
        return i;
      }
    }
    return -1;
  }

  public interface ItemSelectedListener {
    /**
     * Called when an item in the bottom navigation menu is selected.
     *
     * @param item The selected item
     * @param wasSelected was this item selected before
     * @return true to display the item as the selected item
     */
    boolean onItemSelected(MenuItem item, boolean wasSelected);
  }

  public interface ItemIndexSelectedListener {
    /**
     * Called when an item in the bottom navigation menu is selected.
     *
     * @param itemIndex The selected item's index
     * @param wasSelected was this item selected before
     * @return true to display the item as the selected item
     */
    boolean onItemSelected(int itemIndex, boolean wasSelected);
  }
}