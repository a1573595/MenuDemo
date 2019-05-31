package a1573595.com.menudemo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class BaseActivity: AppCompatActivity() {
    private val fm = supportFragmentManager

    private lateinit var menuItem1: MenuItem
    private lateinit var menuItem2: MenuItem
    private lateinit var menuItem3: MenuItem

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_actionbar, menu)
        menuItem1 = menu.findItem(R.id.menu_item1)
        menuItem2 = menu.findItem(R.id.menu_item2)
        menuItem3 = menu.findItem(R.id.menu_item3)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.e(javaClass.simpleName, "onOptionsItemSelected")
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun setActionBar(title: String, showBack: Boolean = false){
        resetActionBar()
        setBack(showBack)
        setTitle(title)
    }

    fun resetActionBar(){
        menuItem1.setIcon(0)
        menuItem2.setIcon(0)
        menuItem3.setIcon(0)

        menuItem1.title = null
        menuItem2.title = null
        menuItem3.title = null

        menuItem1.isVisible = false
        menuItem2.isVisible = false
        menuItem3.isVisible = false
    }

    fun setBack(visibility: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(visibility)
    }

    fun setMenuButton(position: Int, resource: Int){
        val item = when(position){
            0 -> menuItem1
            1 -> menuItem1
            else -> menuItem3
        }

        item.setIcon(resource)
        item.isVisible = true
    }

    fun setMenuText(position: Int, text: String){
        val item = when(position){
            0 -> menuItem1
            1 -> menuItem1
            else -> menuItem3
        }

        item.title = text
        item.isVisible = true
    }

    fun switchTo(fragment : Fragment, bundle: Bundle? = null, broken: Boolean = false){
        if(broken || fm.findFragmentByTag(fragment.javaClass.simpleName)==null){
            if(broken && fm.backStackEntryCount>0)
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

            Log.e("switchTo", fragment.javaClass.simpleName)
            fragment.arguments = bundle
            val ft = fm.beginTransaction()
            ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out,
                R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_shrink_fade_out_from_bottom)
            ft.replace(R.id.fl_fragment, fragment, fragment.javaClass.simpleName)
            ft.addToBackStack(fragment.javaClass.simpleName)
            ft.commit()
        }
    }
    //解決java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
    fun switchToAllowingStateLoss(fragment : Fragment, bundle: Bundle? = null, broken: Boolean = false){
        if(broken && fm.backStackEntryCount>0)
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        if(fm.findFragmentByTag(fragment.javaClass.simpleName)==null || broken){
            Log.e("switchTo", fragment.javaClass.simpleName)
            fragment.arguments = bundle
            val ft = fm.beginTransaction()
            ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out,
                R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_shrink_fade_out_from_bottom)
            ft.replace(R.id.fl_fragment, fragment, fragment.javaClass.simpleName)
            ft.addToBackStack(fragment.javaClass.simpleName)
            ft.commitAllowingStateLoss()
        }
    }
}