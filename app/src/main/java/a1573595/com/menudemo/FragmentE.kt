package a1573595.com.menudemo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_sample.*

class FragmentE: BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        mActivity.setActionBar(javaClass.simpleName, true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.e(javaClass.simpleName, "onOptionsItemSelected")
        when(item.itemId){
            R.id.menu_item1 -> {
                Log.e(javaClass.simpleName, "Item1")
                return true
            }
            R.id.menu_item2 -> {
                Log.e(javaClass.simpleName, "Item2")
                return true
            }
            R.id.menu_item3 -> {
                Log.e(javaClass.simpleName, "Item3")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cl_root.setBackgroundColor(Color.rgb((Math.random()*255).toInt()+1,
            (Math.random()*255).toInt()+1, (Math.random()*255).toInt()+1))
        tv_tag.text = javaClass.simpleName
    }
}