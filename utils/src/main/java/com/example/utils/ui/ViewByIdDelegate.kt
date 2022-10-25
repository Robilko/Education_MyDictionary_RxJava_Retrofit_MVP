package com.example.utils.ui

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class ViewByIdDelegate<out T : View>(private val rootGetter: () -> View?, private val viewId: Int) {
    /** WeakReference (в переводе "мягкая сслыка") это некий объект-обертка из стандартной библиотеки
     * java который нужен, для того, чтобы сборщик мусора (GC) удалял ссылку на этот объект если он
     * не используется, при следующей сборке мусора.
     * Если GC видит что объект доступен только через цепочку weak-ссылок, то он удалит его из памяти.
     * */
    //Ссылка на root
    private var rootRef: WeakReference<View>? = null

    //Ссылка на View
    private var viewRef: T? = null
    //Метод вызывается при каждом обращении к переменной
    /**модификатор operator fun означает,что функция перегружена*/
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        var view = viewRef
        val cachedRoot = rootRef?.get()
        // Получаем root
        val currentRoot = rootGetter()

        if (currentRoot != cachedRoot || view == null) {
            if (currentRoot == null) {
                if (view != null) {
                    // Failsafe, возвращать хотя бы последнюю View
                    return view
                }
                throw IllegalStateException("Cannot get View, there is no root yet")
            }
            // Создаём View
            view = currentRoot.findViewById(viewId)
            // Сохраняем ссылку на View, чтобы не создавать её каждый раз заново
            viewRef = view
            // Сохраняем ссылку на root, чтобы не искать его каждый раз заново
            rootRef = WeakReference(currentRoot)
        }
        checkNotNull(view) { "View with id \"$viewId\" not found in root" }
        // Возвращаем View в момент обращения к ней
        return view
    }
}

fun <T : View> Activity.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    // Возвращаем корневую View
    return ViewByIdDelegate({ window.decorView.findViewById(android.R.id.content) }, viewId)
}

fun <T : View> Fragment.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ view }, viewId)
}