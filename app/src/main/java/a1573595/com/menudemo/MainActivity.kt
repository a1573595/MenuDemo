package a1573595.com.menudemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var adapter: ViewPagerAdapter
    private val fragments = arrayOf<Fragment>(
        FragmentA(),
        FragmentB(),
        FragmentC()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ViewPagerAdapter(fragments, supportFragmentManager)
        viewPager.adapter = adapter
    }

    fun setPage(position: Int){
        viewPager.setCurrentItem(position, false)
    }
}
