package a1573595.com.menudemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(private val fragmentArray: Array<Fragment>, fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int) = fragmentArray[position]

    override fun getCount() = fragmentArray.size
}